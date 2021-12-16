let tiempoInicio, mediaRecorder, idIntervalo;
let videos = new Array();
function comenzarAGrabar(id) {
  const $video = document.querySelector("#camaraPregunta" + id);
  if (mediaRecorder) return alert("Ya se está grabando");

  navigator.mediaDevices.getUserMedia({
    audio: false, video: true
  })
    .then(stream => {
      // Poner stream en vídeo
      $video.srcObject = stream;
      $video.play();
      // Comenzar a grabar con el stream
      mediaRecorder = new MediaRecorder(stream);
      mediaRecorder.start();
      // En el arreglo pondremos los datos que traiga el evento dataavailable
      const fragmentosDeAudio = [];
      // Escuchar cuando haya datos disponibles
      mediaRecorder.addEventListener("dataavailable", evento => {
        // Y agregarlos a los fragmentos
        fragmentosDeAudio.push(evento.data);
      });
      // Cuando se detenga (haciendo click en el botón) se ejecuta esto
      mediaRecorder.addEventListener("stop", () => {
        // Pausar vídeo
        $video.pause();
        // Detener el stream
        stream.getTracks().forEach(track => track.stop());
        // Detener la cuenta regresiva
        // Convertir los fragmentos a un objeto binario
        const blobVideo = new Blob(fragmentosDeAudio, { 'type': 'video/.mp4; codecs=vp8' });

        // Crear una URL o enlace para descargar
        const urlParaDescargar = URL.createObjectURL(blobVideo);
        // Crear un elemento <a> invisible para descargar el audio
        let a = document.createElement("a");
        document.body.appendChild(a);

        a.id = id;
        a.href = urlParaDescargar;
        a.value = blobVideo;
        a.download = document.getElementById("camaraPregunta"+id + ".mp4");
        // Hacer click en el enlace
        const myFile = new File(
          [blobVideo],
          "Pregunta" + id,
          { type: 'video/mp4' }
        );

        let nuevo = videos.push(myFile);
        console.log(videos[id]);
        // Y remover el objeto
        window.URL.revokeObjectURL(urlParaDescargar);
      });
    })
    .catch(error => {
      // Aquí maneja el error, tal vez no dieron permiso
      console.log(error)
    });
};


const detenerGrabacion = () => {
  if (!mediaRecorder) return alert("No se está grabando");
  mediaRecorder.stop();
  mediaRecorder = null;
};

function GuardarRespuestas(nombre) {
    var NoPreguntas = document.getElementById("totalPreguntas").value;
    //var NombreExamen = document.getElementById("NombreExamen").value;
    //var NombreAlumno = document.getElementById("NombreAlumno").value;
    var formData = new FormData();
    formData.append("NoPreguntas", NoPreguntas);
    //formData.append("NombreExamen", NombreExamen);
    //formData.append("NombreAlumno", NombreAlumno);
    alert(NoPreguntas);
    
    for(var i=1; i<NoPreguntas+1;i++){
        var noRespuesta;
        var noPregunta;
        var noExamen;
        var respuesta;
        var tipo = document.getElementById("tipo"+i).value;
        if(tipo  == "Abierta"){
            noRespuesta = document.getElementById(i).value;
            noPregunta = document.getElementById(i).value;
            noExamen = document.getElementById("noExamen").value;
            respuesta = document.getElementById("respuesta"+i).value;
            axios.post("http://localhost:4567/agregarRespuesta", {
                noRespuesta : noRespuesta,
                noPregunta : noPregunta,
                noExamen : noExamen,
                respuesta: respuesta
            })
            .then(function (res) {
                formData.append("videoRespuestaPregunta"+i, videos[i]);
            })
            .catch(function (error) {
                console.log(error)
            })
            
        }else if(tipo == "Cerrada"){
            noRespuesta = document.getElementById(i).value;
            noPregunta = document.getElementById(i).value;
            noExamen = document.getElementById("noExamen").value;
            if(document.getElementById("opcion"+i+" a").checked){
                respuesta = "a";
            }else if (document.getElementById("opcion"+i+" b").checked){
                respuesta = "b";
            }else if (document.getElementById("opcion"+i+" c").checked){
                respuesta = "c";
            }else if (document.getElementById("opcion"+i+" d").checked){
                respuesta = "d";
            }
            axios.post("http://localhost:4567/agregarRespuesta", {
                noRespuesta : noRespuesta,
                noPregunta : noPregunta,
                noExamen : noExamen,
                respuesta: respuesta
            })
            .then(function (res) {
            })
            .catch(function (error) {
                console.log(error)
            })
        }
    }

    axios.post("http://localhost:4567/videoRespuesta", formData,{
        headers : {
            "Content-Type" : "multipart/form-data"
        }
    }).then(function (response) {
        console.log(response);
    })
    .catch(function (error) {
        console.log(error);
    });
    window.location.replace("http://localhost:4567/estudiante");
  }