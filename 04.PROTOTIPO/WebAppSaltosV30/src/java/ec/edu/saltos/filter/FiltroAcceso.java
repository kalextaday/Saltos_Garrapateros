package ec.edu.saltos.filter;

import ec.edu.saltos.config.PaginaConfig;
import ec.edu.saltos.dao.DAOPermiso;
import ec.edu.saltos.modelo.Permiso;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guffenix
 */
public class FiltroAcceso {

    private static final Logger LOG = Logger.getLogger(FiltroAcceso.class.getName());

    public FiltroAcceso(ExternalContext context) {
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpSession ses = request.getSession(false);

        boolean tieneAcceso = false;
        boolean tieneSesion = false;
        if (request.getServletPath().equals(PaginaConfig.PAGINA_LOGIN.getUrl())
                || request.getServletPath().equals(PaginaConfig.PAGINA_RECUPERAR_CLAVE.getUrl())) {
            tieneAcceso = true;

        }
        if (ses != null && ses.getAttribute("idUsuario") != null) {
            tieneSesion = true;
            //Incluir páginas privadas pero sin permiso.
            if (request.getServletPath().equals(PaginaConfig.PAGINA_SOPORTE.getUrl())
                    || request.getServletPath().equals(PaginaConfig.PAGINA_REGISTRO_ACTIVIDAD.getUrl())
                    || request.getServletPath().equals(PaginaConfig.PAGINA_INFORMACION_USUARIO.getUrl())
                    || request.getServletPath().equals(PaginaConfig.PAGINA_CONFIGURACION_CUENTA.getUrl())
                    || request.getServletPath().equals(PaginaConfig.PAGINA_INICIO.getUrl())) {
                tieneAcceso = true;
            } else {

                
                List<Permiso> permisos = new DAOPermiso().permisosPerfil(Integer.parseInt(ses.getAttribute("idPerfil").toString()));

                tieneAcceso = true;
                /*
                if (permisos != null && !permisos.isEmpty()) {

                    Permiso permisoEncontrado;
                    permisoEncontrado = permisos.stream()
                            .filter(per -> per.getRecurso().getPaginarec().concat(".xhtml")
                                    .equals(request.getServletPath()))
                            .findAny()
                            .orElse(null);
                    
                    tieneAcceso = permisoEncontrado != null;
                }*/
            }
        }

        if (tieneSesion) {
            try {
                if (request.getServletPath().equals(PaginaConfig.PAGINA_LOGIN.getUrl())) {
                    context.redirect(request.getContextPath() + PaginaConfig.PAGINA_INICIO.getUrl());
                }
                if (!tieneAcceso) {
                    context.redirect(request.getContextPath() + PaginaConfig.PAGINA_ACCESO_DENEGADO.getUrl());

                }
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        } else if (!tieneAcceso) {
            try {
//                System.out.println("Sin sesion en: " + request.getContextPath() + "/login.xhtml");
                context.redirect(request.getContextPath() + PaginaConfig.PAGINA_LOGIN.getUrl());

            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
}
