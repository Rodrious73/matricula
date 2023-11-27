$(document).ready(function () {
    $("#btnIniciar").click(function () {
        let logi = $("#txtLogin").val();
        let pass = $("#txtClave").val();

        let parametro = {
            logi: logi, pass: pass
        };

        $.getJSON("validar", parametro, function (data) {
            if (data.resultado === "ok") {
                $(location).attr('href', 'principal.html');
            } else {
                alert("ERROR");
            }
        });
    });
});