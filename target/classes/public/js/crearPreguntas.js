var contadorAbierta = 0;
var contadorCerrada = 0;
var contadorIdRespuesta = 1;
var contadorNoPregunta = 1;


var name = "opcion"+contadorNoPregunta;

var a = "a";
var b = "b";
var c = "c";
var d = "d";

var div = document.createElement('div');
var label = document.createElement('b');

function submitAnswers(){
	var total = 5;
	var score = 0;

	// Get user input
	var q1 = document.forms["quizzForm"]["q1"].value;
	var q2 = document.forms["quizzForm"]["q2"].value;
	var q3 = document.forms["quizzForm"]["q3"].value;
	var q4 = document.forms["quizzForm"]["q4"].value;
	var q5 = document.forms["quizzForm"]["q5"].value;

	// Validation
	for(i = 1; i <= total; i++){
		if(eval('q'+i) == null || eval('q'+i) == ''){
			alert('You missed question' +i);
			return false;
		}
	}

	// Set correct answers
	var answers = ["b","a","d","b","d"];

	// Check answers
	for(i = 1; i <= total; i++){	
		if (eval('q'+1) == answers[i - 1]){
			score++;
		}
	}

	// Display results
	var results = document.getElementById('results');
	results.innerHTML = '<h3>You scored <span>'+score+'</span> out of <span>'+total+'</span></h3>';
	alert('You scored '+score+' out of ' +total);

	return false;
}


//Open and close popup

function openForm() {
    document.getElementById("popup").style.display = "block";
  }
  
function closeForm() {
    document.getElementById("popup").style.display = "none";
  }


  //Add open and close questions

function tipoAbierta(){

    while (div.firstChild) {
        div.removeChild(div.firstChild);
    }
    label.parentNode.removeChild(label);
    contadorCerrada = 0;

    document.getElementById("radioAbierta").checked = true;
    if(contadorAbierta <1){
        /*
        var popup = document.getElementById("agregarPregunta");
        var input = document.createElement('input');
        input.setAttribute("type", "text");
        input.setAttribute("placeholder", "Introduce la respuesta correcta");
        input.setAttribute("id", "respuesta"+contadorIdRespuesta)
        popup.appendChild(input)
        */ 

        contadorAbierta++;
    }else{
    }
    //contadorIdRespuesta++;
  }

function tipoCerrada(){
    if(contadorCerrada <1){
        var popup = document.getElementById("agregarPregunta");
        
        //div.setAttribute("onClick", "this.contentEditable='true';");
    
        var br1 = document.createElement('br');
        var br2 = document.createElement('br');
        var br3 = document.createElement('br');
        var br4 = document.createElement('br');
    
        var input1 = document.createElement('input');
        input1.setAttribute("placeholder", "Introduce la opción 1");
        input1.setAttribute("id", "input"+ name + a);
        
        var input2 = document.createElement('input');
        input2.setAttribute("placeholder", "Introduce la opción 2");
        input2.setAttribute("id", "input"+ name + b);
    
        var input3 = document.createElement('input');
        input3.setAttribute("placeholder", "Introduce la opción 3");
        input3.setAttribute("id", "input"+ name + c);
    
        var input4 = document.createElement('input');
        input4.setAttribute("placeholder", "Introduce la opción 4");
        input4.setAttribute("id", "input"+ name + d);
    
    
    
    
        var radiobtn1 = document.createElement('input');
        radiobtn1.setAttribute("type", "radio");
        radiobtn1.setAttribute("name", name);
        radiobtn1.setAttribute("value",a);
        radiobtn1.setAttribute("id", name + a);
        
    
        var radiobtn2 = document.createElement('input');
        radiobtn2.setAttribute("type", "radio");
        radiobtn2.setAttribute("name", name);
        radiobtn2.setAttribute("value",b);
        radiobtn2.setAttribute("id", name + b);
        
    
        var radiobtn3 = document.createElement('input');
        radiobtn3.setAttribute("type", "radio");
        radiobtn3.setAttribute("name", name);
        radiobtn3.setAttribute("value",c);
        radiobtn3.setAttribute("id", name + c);
        
    
        var radiobtn4 = document.createElement('input');
        radiobtn4.setAttribute("type", "radio");
        radiobtn4.setAttribute("name", name);
        radiobtn4.setAttribute("value",d);
        radiobtn4.setAttribute("id", name + d);
        
    
        
        label.innerHTML = "Introduce las opciones y selecciona la correcta"
        
    
        div.appendChild(radiobtn1);
        div.appendChild(input1);
        div.appendChild(br1);
        div.appendChild(radiobtn2);
        div.appendChild(input2);
        div.appendChild(br2);
        div.appendChild(radiobtn3);
        div.appendChild(input3);
        div.appendChild(br3);
        div.appendChild(radiobtn4);
        div.appendChild(input4);
        div.appendChild(br4);
    
    
        popup.appendChild(label);
        popup.appendChild(div);

        contadorCerrada++;

        
    }
    
}

var btnAgregar = document.getElementById("btnAgregar")
function agregarPregunta(){
    var interrogante; 
    var respuesta;
    var opcion1;
    var opcion2;
    var opcion3;
    var opcion4;
    var tipo;
    if (document.getElementById("radioAbierta").checked) {
        interrogante = document.getElementById("interrogante").value;
        respuesta = ""; //document.getElementById("respuesta"+contadorIdRespuesta).value;
        opcion1 = "";
        opcion2 = "";
        opcion3 = "";
        opcion4 = "";
        tipo = "Abierta";
        axios.post("http://localhost:4567/agregarPregunta", {
            noPregunta : contadorNoPregunta,
            interrogante : interrogante,
            opcion1 : opcion1,
            opcion2 : opcion2,
            opcion3 : opcion3,
            opcion4 : opcion4,
            respuesta: respuesta,
            tipo : tipo
        })
            .then(function (res) {
                alert("Pregunta:" + res.data.status);
                closeForm();
            })
            .catch(function (error) {
                console.log(error)
            })

        var formulario = document.getElementById("divPreguntas");
        var pregunta = document.createElement('h3');
        pregunta.innerHTML = contadorNoPregunta + ". " + interrogante;

        var br = document.createElement('br');
        var br2 = document.createElement('br');
        var input = document.createElement('input');
        input.setAttribute("type", "text");
        input.setAttribute("placeholder", "Introduce la respuesta correcta");
        input.setAttribute("id", "respuesta"+contadorIdRespuesta);
        formulario.appendChild(pregunta);
        formulario.appendChild(br);
        formulario.appendChild(input);
        formulario.appendChild(br2);

    }else if(document.getElementById("radioCerrada").checked){
        interrogante = document.getElementById("interrogante").value;
        if(document.getElementById(name + a).checked){
            respuesta = "a";
        }else if (document.getElementById(name + b).checked){
            respuesta = "b";
        }else if (document.getElementById(name + c).checked){
            respuesta = "c";
        }else if (document.getElementById(name + d).checked){
            respuesta = "d";
        }
        opcion1 = document.getElementById("input"+name+a).value;
        opcion2 = document.getElementById("input"+name+b).value;
        opcion3 = document.getElementById("input"+name+c).value;
        opcion4 = document.getElementById("input"+name+d).value;
        tipo = "Cerrada";
        axios.post("http://localhost:4567/agregarPregunta", {
            noPregunta : contadorNoPregunta,
            interrogante : interrogante,
            respuesta: respuesta,
            opcion1 : opcion1,
            opcion2 : opcion2,
            opcion3 : opcion3,
            opcion4 : opcion4,
            tipo : tipo
        })
            .then(function (res) {
                alert("Pregunta:" + res.data.status);
                closeForm();
                while (div.firstChild) {
                    div.removeChild(div.firstChild);
                }
                label.parentNode.removeChild(label);
                contadorCerrada = 0;
            })
            .catch(function (error) {
                console.log(error)
            })

    
        var formulario = document.getElementById("divPreguntas");
        var pregunta = document.createElement('h3');
        pregunta.innerHTML = contadorNoPregunta + ". " + interrogante;

        var br1 = document.createElement('br');
        var br2 = document.createElement('br');
        var br3 = document.createElement('br');
        var br4 = document.createElement('br');
        var br5 = document.createElement('br');

        var label1 = document.createElement('label');
        label1.innerHTML = a + ". " + opcion1;
        var label2 = document.createElement('label');
        label2.innerHTML = b + ". " + opcion2;
        var label3 = document.createElement('label');
        label3.innerHTML = c + ". " + opcion3;
        var label4 = document.createElement('label');
        label4.innerHTML = d + ". " + opcion4;

        formulario.appendChild(pregunta);
        formulario.appendChild(br1);
        formulario.appendChild(label1);
        formulario.appendChild(br2);
        formulario.appendChild(label2);
        formulario.appendChild(br3);
        formulario.appendChild(label3);
        formulario.appendChild(br4);
        formulario.appendChild(label4);
        formulario.appendChild(br5);
        
    }
    contadorNoPregunta++;
    document.getElementById('formularioCrear').reset();
     
}

var btnGuardarFormulario = document.getElementById("btnGuardarFormulario")
btnGuardarFormulario.addEventListener("click", () => {
     window.location.replace("http://localhost:4567/maestro");
});



