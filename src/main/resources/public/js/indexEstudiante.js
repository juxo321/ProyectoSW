document.querySelectorAll(".click").forEach(el => {
    el.addEventListener("click", e => {
        var id = e.target.getAttribute("id");
        console.log("Se ha clickeado el id "+id);

        var btnContestar = document.getElementById(id);
        btnContestar.addEventListener("click", () => {
        axios.post("http://localhost:4567/contestarExamen", {
            noExamen: id,
        })
        .then(function (res) {
            window.location.replace("http://localhost:4567/formularioExamen");
        })
        .catch(function (error) {
            console.log(error)
        })
    
        });
    });
});