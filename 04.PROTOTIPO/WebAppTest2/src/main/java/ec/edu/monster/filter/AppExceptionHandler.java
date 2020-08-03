package ec.edu.monster.filter;

import ec.edu.monster.config.PaginaConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DavidLeonardo
 */
@WebServlet("/AppExceptionHandler")
public class AppExceptionHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(AppExceptionHandler.class.getName());

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        String dominio = url.substring(0, url.lastIndexOf('/'));

        // Analyze the servlet exception
        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        if (requestUri.contains("/super/")
                || requestUri.contains("/app")) {
            ((HttpServletResponse) response).sendRedirect(dominio + PaginaConfig.PAGINA_LOGIN.getUrl());
        } else if (statusCode.equals(PaginaConfig.PAGINA_ERROR_404.getValor())) {
            LOG.log(Level.SEVERE, "Error[404]: Intent\u00f3 ingresar a:{0}", requestUri);
//            System.out.println("Intentó ingresar a:" + requestUri);
//            System.out.println("404>" + dominio + PaginaConfig.PAGINA_ERROR_404.getUrl());
            ((HttpServletResponse) response).sendRedirect(dominio + PaginaConfig.PAGINA_ERROR_404.getUrl());
        } else if (statusCode.equals(PaginaConfig.PAGINA_ERROR_500.getValor())) {
            LOG.log(Level.SEVERE, "Error[500]: Intent\u00f3 ingresar a:{0}", requestUri);
//            System.out.println("Intentó ingresar a:" + requestUri);
//            System.out.println("500>" + dominio + PaginaConfig.PAGINA_ERROR_500.getUrl());

            ((HttpServletResponse) response).sendRedirect(dominio + PaginaConfig.PAGINA_ERROR_500.getUrl());
        }

    }
}
