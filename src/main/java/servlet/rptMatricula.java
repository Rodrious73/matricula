package servlet;

import dao.MySQL;
import jakarta.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class rptMatricula extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();

        String resultado = "";
        String codiMatr = request.getParameter("codiMatr");

        InputStream report = this.getServletConfig().getServletContext().getResourceAsStream("rptMatricular.jasper");
        Map<String, Object> parameters = new HashMap();
        parameters.put("pCodiMatr", Integer.parseInt(codiMatr));

        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "inline; filename=matricula.pdf");

        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, MySQL.getInstance().getConnection());
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

        } catch (Exception ex) {
            String mensaje = "";
            mensaje = ex.getMessage();
            mensaje += mensaje + "\n";

        } finally {

        }
        out.flush();
        out.close();

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
