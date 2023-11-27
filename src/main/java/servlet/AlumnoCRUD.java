package servlet;

import com.google.gson.Gson;
import dao.AlumnoJpaController;
import dto.Alumno;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class AlumnoCRUD extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String tipo = request.getParameter("tipo");
            AlumnoJpaController aluDAO = new AlumnoJpaController();
            String resultado = "";
            String resulBusqueda = "";
            switch (tipo) {
                case "1": //Listar Alumnos
                    List<Alumno> lista = aluDAO.findAlumnoEntities();
                    Gson g = new Gson();
                    resultado = g.toJson(lista);
                    resultado = "{\"data\":" + resultado + "}";
                    break;
                case "2": //Buscar Alumno por codigo
                    String codigo = request.getParameter("codigo");
                    Alumno alu = aluDAO.findAlumno(Integer.parseInt(codigo));
                    if (alu != null) {
                        Gson gson = new Gson();
                        resulBusqueda = gson.toJson(alu);
                        out.print(resulBusqueda);
                    } else {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                case "3": //editar Alumno
                    String codiAlum = request.getParameter("codigo");
                    String nombAlum = request.getParameter("nombres");
                    String appaAlum = request.getParameter("appa");
                    String apmaAlum = request.getParameter("apma");
                    
                    Alumno editarAlumno = new Alumno();
                    editarAlumno.setCodiAlum(Integer.parseInt(codiAlum));
                    editarAlumno.setNombAlum(nombAlum);
                    editarAlumno.setAppaAlum(appaAlum);
                    editarAlumno.setApmaAlum(apmaAlum);
                    
                    try {
                        aluDAO.edit(editarAlumno);
                        out.print("{\"resultado\":\"ok\"}");
                    } catch (Exception e) {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                case "4": //Eliminar Alumno
                    String codiAlumEliminar = request.getParameter("codigo");
                    
                    try {
                        aluDAO.destroy(Integer.parseInt(codiAlumEliminar));
                        out.print("{\"resultado\":\"ok\"}");
                    } catch (Exception e) {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                case "5": //Agregar Alumno
                    String codiAlumAgregar = request.getParameter("codigo");
                    String nombAlumAgregar = request.getParameter("nombres");
                    String appaAlumAgregar = request.getParameter("appa");
                    String apmaAlumAgregar = request.getParameter("apma");
                    
                    Alumno agregarAlumno = new Alumno();
                    agregarAlumno.setCodiAlum(Integer.parseInt(codiAlumAgregar));
                    agregarAlumno.setNombAlum(nombAlumAgregar);
                    agregarAlumno.setAppaAlum(appaAlumAgregar);
                    agregarAlumno.setApmaAlum(apmaAlumAgregar);
                    
                    try {
                        aluDAO.create(agregarAlumno);
                        out.print("{\"resultado\":\"ok\"}");
                    } catch (Exception e) {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                default:
                    throw new AssertionError();
            }

            out.print(resultado);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
