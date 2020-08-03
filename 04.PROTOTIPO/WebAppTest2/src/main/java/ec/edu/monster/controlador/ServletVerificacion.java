package ec.edu.monster.controlador;

import ec.edu.monster.config.HttpOptions;
import ec.edu.monster.config.PaginaConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guffenix
 */
@WebServlet(name = "ServletVerificacion", urlPatterns = {"/ServletVerificacion"})
public class ServletVerificacion extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ServletVerificacion.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(HttpOptions.ENCABEZADO_UTF8.getOption1());
        PrintWriter out = response.getWriter();

        try {
            String id_usuario;

            if (request.getParameter("cli") != null) {
                id_usuario = request.getParameter("cli");
                if (id_usuario != null && !id_usuario.equals("")) {
                    String url = request.getRequestURL().toString();
                    String[] partes = url.split("\\/");
                    String finals = "";
                    for (int i = 0; i < (partes.length - 1); i++) {
                        finals += partes[i] + "/";
                    }

                    String dominio = finals;
                    response.sendRedirect(dominio + PaginaConfig.DIRECCION_CAMBIO_CLAVE.getUrl() + id_usuario + "");
                }
            } else {
                response.sendRedirect(PaginaConfig.PAGINA_LOGIN.getUrl());
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, null, e);

        } finally {
            out.close();
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
