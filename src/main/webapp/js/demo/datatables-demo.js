$(document).ready(function () {
    let datatable = $("#dataTable").DataTable({
        ajax: 'alumnocrud?tipo=1',
        columns: [
            {data: 'codiAlum'},
            {data: 'appaAlum'},
            {data: 'apmaAlum'},
            {data: 'nombAlum'},
            {
                data: null,
                render: function (data, type, row) {
                    return '<button class="btn btn-warning btn-sm" onclick="abrirModalEditar(' + row.codiAlum + ')"><i class="fa-regular fa-pen-to-square"></i></button>&nbsp;' +
                            '<button class="btn btn-danger btn-sm" onclick="abrirModalEliminar(' + row.codiAlum + ')"><i class="fa-solid fa-trash-can"></i></button>';
                }
            }
        ],
        columnDefs: [
            {orderable: false, targets: [4]}
        ],
        pageLength: 5,
        lengthMenu: [5, 10, 25, 50, 100],
        language: {
            url: "https://cdn.datatables.net/plug-ins/1.10.25/i18n/Spanish.json"
        }
    });
});

function abrirModalEditar(codigoAlumno) {
    obtenerAlumnoPorCodigo(codigoAlumno, function (alumno) {
        $('#idAlumno').val(alumno.codiAlum);
        $('#txtNombres').val(alumno.nombAlum);
        $('#txtApPaterno').val(alumno.appaAlum);
        $('#txtApMaterno').val(alumno.apmaAlum);

        $('#modalEditar').modal('show');
    });
}

function abrirModalEliminar(codigoAlumno) {
    obtenerAlumnoPorCodigo(codigoAlumno, function (alumno) {
        $('#idAlumnoEliminar').val(codigoAlumno);
        $('#txtNombresEliminar').val(alumno.nombAlum);
        $('#txtApPaternoEliminar').val(alumno.appaAlum);
        $('#txtApMaternoEliminar').val(alumno.apmaAlum);

        $('#modalEliminar').modal('show');
    });
}

function obtenerAlumnoPorCodigo(codigoAlumno, callback) {
    $.getJSON("alumnocrud?tipo=2&codigo=" + codigoAlumno, function (data) {
        if (data.resultado === "error") {
            alert("No se encontró el código");
        } else {
            callback({
                codiAlum: codigoAlumno,
                nombAlum: data.nombAlum,
                appaAlum: data.appaAlum,
                apmaAlum: data.apmaAlum
            });
        }
    });
}

function mostrarMensaje(mensaje, tipo) {
    $('#mensajeModal').text(mensaje);

    let modalContent = $('#modalContent');

    modalContent.removeClass('editar-color eliminar-color agregar-color error-color');

    if (tipo === 'editar') {
        modalContent.addClass('editar-color');
    } else if (tipo === 'eliminar') {
        modalContent.addClass('eliminar-color');
    } else if (tipo === 'agregar') {
        modalContent.addClass('agregar-color');
    } else if (tipo === 'error') {
        modalContent.addClass('error-color');
    }

    $('#mensajeEditado').modal('show');
}

$("#btnEditar").click(function () {
    let codigo = $('#idAlumno').val();
    let nombres = $('#txtNombres').val();
    let apPaterno = $('#txtApPaterno').val();
    let apMaterno = $('#txtApMaterno').val();

    $.ajax({
        url: 'alumnocrud',
        type: 'POST',
        data: {
            tipo: '3',
            codigo: codigo,
            nombres: nombres,
            appa: apPaterno,
            apma: apMaterno
        },
        dataType: 'json',
        success: function (response) {
            if (response.resultado === 'ok') {
                $('#dataTable').DataTable().ajax.reload();
                mostrarMensaje('Alumno editado correctamente...', 'editar');
            } else {
                mostrarMensaje('Error al editar...', 'error');
            }
        }
    });

    $('#modalEditar').modal('hide');
});

$('#btnEliminar').click(function () {
    let codigo = $('#idAlumnoEliminar').val();

    $.ajax({
        url: 'alumnocrud',
        type: 'POST',
        data: {
            tipo: '4',
            codigo: codigo
        },
        dataType: 'json',
        success: function (response) {
            if (response.resultado === 'ok') {
                $('#dataTable').DataTable().ajax.reload();
                mostrarMensaje('Alumno eliminado correctamente...', 'eliminar');
            } else {
                mostrarMensaje('Error al eliminar...', 'error');
            }
        }
    });

    $('#modalEliminar').modal('hide');
});

$('#btnAgregar').click(function () {
    let codigo = $('#idAlumnoAgregar').val();
    let nombres = $('#txtNombresAgregar').val();
    let apPaterno = $('#txtApPaternoAgregar').val();
    let apMaterno = $('#txtApMaternoAgregar').val();

    if (codigo === '' || nombres === '' || apPaterno === '' || apMaterno === '') {
        mostrarMensaje('Todos los campos son obligatorios. Por favor, complete todos los campos.', 'error');
        return;
    }

    $.ajax({
        url: 'alumnocrud',
        type: 'GET',
        data: {
            tipo: '2',
            codigo: codigo
        },
        dataType: 'json',
        success: function (response) {
            if (response.codiAlum && response.codiAlum.toString() === codigo) {
                mostrarMensaje('Ya existe un alumno con este código. Ingrese un código único.', 'error');
            } else {
                agregarAlumno(codigo, nombres, apPaterno, apMaterno);
            }
        }
    });
});

function agregarAlumno(codigo, nombres, apPaterno, apMaterno) {
    // Agregar el alumno
    $.ajax({
        url: 'alumnocrud',
        type: 'POST',
        data: {
            tipo: '5',
            codigo: codigo,
            nombres: nombres,
            appa: apPaterno,
            apma: apMaterno
        },
        dataType: 'json',
        success: function (response) {
            if (response.resultado === 'ok') {
                $('#dataTable').DataTable().ajax.reload();
                mostrarMensaje('Alumno agregado correctamente...', 'agregar');
                $('#idAlumnoAgregar').val('');
                $('#txtNombresAgregar').val('');
                $('#txtApPaternoAgregar').val('');
                $('#txtApMaternoAgregar').val('');
            } else {
                mostrarMensaje('Error al agregar...', 'agregar');
            }
        }
    });

    $('#modalAgregar').modal('hide');
}


$("#btnGenerarPdf").click(function () {
    $.ajax({
        url: 'generarpdf',
        type: 'GET',
        error: function (xhr, status, error) {
            console.error('Error al generar el PDF:', error);
            console.log('Estado de la solicitud:', status);
            console.log('Respuesta del servidor:', xhr.responseText);
        }
    });
});
