package servlet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.AlumnoJpaController;
import dto.Alumno;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class GenerarPdf extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=lista_estudiantes.pdf");

            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            document.add(new Paragraph("Lista de Estudiantes - 2023"));
            document.add(new Paragraph(""));

            AlumnoJpaController aluDAO = new AlumnoJpaController();
            List<Alumno> listaAlumnos = aluDAO.findAlumnoEntities();

            PdfPTable table = new PdfPTable(5);

            table.addCell("Nº");
            table.addCell("Ap. Paterno");
            table.addCell("Ap. Materno");
            table.addCell("Nombres");

            if (listaAlumnos != null && !listaAlumnos.isEmpty()) {
                int contador = 1;
                for (Alumno alumno : listaAlumnos) {
                    table.addCell(String.valueOf(contador));
                    table.addCell(String.valueOf(alumno.getCodiAlum()));
                    table.addCell(alumno.getAppaAlum());
                    table.addCell(alumno.getApmaAlum());
                    table.addCell(alumno.getNombAlum());

                    contador++;
                }
            } else {
                PdfPCell cell = new PdfPCell(new Paragraph("No hay registros de alumnos"));
                cell.setColspan(5);
                table.addCell(cell);
            }
            document.add(table);
            document.close();
            pdfWriter.close(); 
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DocumentException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
