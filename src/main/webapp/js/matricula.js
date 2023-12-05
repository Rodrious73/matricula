$(document).ready(function () {
    $.getJSON("alumnocrud", {tipo: 1}, function (data) {
        for (var item of data.data) {
            $("#cmbAlumno").append("<option value=" + item.codiAlum + ">" + item.codiAlum + " - " + item.nombAlum + "</option>");
            console.log(item.codiAlum);
        }
        /*
         data.data.forEach(function (item) {
         console.log(item.appaAlum);
         });
         */
    });

    $.getJSON("aulacrud", {tipo: 1}, function (data) {
        for (var item of data.data) {
            $("#cmbAula").append("<option value=" + item.codiAula + ">" + item.nombAula + "</option>");
            console.log(item.nombAula);
        }
    });

    $("#btnMatricular").click(function () {
        let codiAlum = $("#cmbAlumno").val();
        let codiAula = $("#cmbAula").val();

        let parametro = {
            tipo: 1, codiAlum: codiAlum, codiAula: codiAula
        };

        $.getJSON("matricula", parametro, function (data) {
            if (data.resultado === "ok") {
                obtenerUltimaMatricula(function (matricula) {
                    $('#txtCodMatr').val(matricula.codiMatr);
                    $('#txtCodAlum').val(matricula.codiAlum);
                    $('#txtCodAula').val(matricula.codiAula);
                });
                $('#modalMatriculaFinalizada').modal('show');
            } else {
                alert("ERROR");
            }
        });
    });

    function obtenerUltimaMatricula(callback) {
        $.getJSON("matricula?tipo=2", function (data) {
            if (data.resultado === "error") {
                alert("No se encontró el código");
            } else {
                callback({
                    codiMatr: data.codiMatr,
                    codiAlum: data.codiAlum,
                    codiAula: data.codiAula
                });
            }
        });
    }

    $("#generarReporteMatricula").click(function () {
        let codiMatr = $('#txtCodMatr').val();
        let url = "http://localhost:8080/Matricula/rptmatricula";
        
        $('#modalMatriculaFinalizada').modal('hide');
        
        window.open(url+"?codiMatr=" + codiMatr);
    });
});