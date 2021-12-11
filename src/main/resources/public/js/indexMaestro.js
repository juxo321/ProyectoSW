//Delete card

document.querySelectorAll(".click").forEach(el => {
    el.addEventListener("click", e => {
        var id = e.target.getAttribute("id");
        console.log("Se ha clickeado el id "+id);

        var btnBorrarExamen = document.getElementById(id);
        btnBorrarExamen.addEventListener("click", () => {
        axios.post("http://localhost:4567/borrarExamen", {
            noExamen: id,
        })
        .then(function (res) {
            alert("Status:" + res.data.status);
            var x = document.getElementById(id);
            x.parentNode.removeChild(x);
        })
        .catch(function (error) {
            console.log(error)
        })
    
        });
    });
});


//Open and close popup

function openForm() {
    document.getElementById("myForm").style.display = "block";
  }
  
  function closeForm() {
    document.getElementById("myForm").style.display = "none";
  }


//Popup form 
  var btnCrear = document.getElementById("btnCrear");
  btnCrear.addEventListener("click", () => {
    axios.post("http://localhost:4567/crearExamen", {
        nombreExamen : document.getElementById("nombreExamen").value,
        materia: document.getElementById("materia").value
    })
        .then(function (res) {
            alert("Examen:" + res.data.status);
            closeForm();
            window.location.replace("http://localhost:4567/crearPreguntas");
        })
        .catch(function (error) {
            console.log(error)
        })
});






