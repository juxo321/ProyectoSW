var btnRegistrarse = document.getElementById("btnRegistrarse");
btnRegistrarse.addEventListener("click", () => {
    var tipoValor = document.getElementById("tipo");
    var tipo = tipoValor.options[tipoValor.selectedIndex].value;
    alert(tipo);
    axios.post("http://localhost:4567/registrarUsuario", {
        usuario: document.getElementById("usuarioNuevo").value,
        pass: document.getElementById("passNuevo").value,
        tipo : tipoValor.options[tipoValor.selectedIndex].value
    })
        .then(function (res) {
            alert("Usuario:" + res.data.status);
        })
        .catch(function (error) {
            console.log(error)
        })
});