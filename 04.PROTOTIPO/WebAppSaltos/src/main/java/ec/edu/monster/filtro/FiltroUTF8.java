/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.filtro;

import ec.edu.monster.config.HttpOptions;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class FiltroUTF8 implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            encoding = filterConfig.getInitParameter("requestEncoding");
            if (encoding == null) {
                encoding = "UTF-8";
            }
        } catch (Exception e) {
            Logger.getLogger(FiltroUTF8.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            if (null == request.getCharacterEncoding()) {
                request.setCharacterEncoding(encoding);
            }

            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setContentType(HttpOptions.ENCABEZADO_UTF8.getOption1());
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader(HttpOptions.ENCABEZADO_HTPP_1_1.getOption1(), HttpOptions.ENCABEZADO_HTPP_1_1.getOption2()); // HTTP 1.1.
            resp.setHeader(HttpOptions.ENCABEZADO_HTPP_1_0.getOption1(), HttpOptions.ENCABEZADO_HTPP_1_0.getOption2()); // HTTP 1.0.
            resp.setDateHeader("Expires", 0); // Proxies.
            chain.doFilter(request, response);

        } catch (IOException | ServletException e) {
            Logger.getLogger(FiltroUTF8.class.getName()).log(Level.INFO, "Se terminó la sesión de la presentación");
        }
    }

    @Override
    public void destroy() {
    }
}
