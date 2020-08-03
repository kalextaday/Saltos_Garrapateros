package ec.edu.monster.controlador;

import ec.edu.monster.filtro.ControlSesion;
import ec.edu.monster.config.PaginaConfig;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author guffenix
 */
@ManagedBean
@ViewScoped
public class BeanDireccionamiento implements Serializable {

    private static final Logger LOG = Logger.getLogger(BeanDireccionamiento.class.getName());
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletRequest request = (HttpServletRequest) context.getRequest();

    public void redireccionarDespuesError() {
        ControlSesion cs = new ControlSesion();
        StringBuilder pagina = new StringBuilder();
        if (cs.obtenerEstadoSesionUsuario()) {
            pagina.append(PaginaConfig.PAGINA_INICIO.getUrl());
        } else {
            pagina.append(PaginaConfig.PAGINA_LOGIN.getUrl());
        }
        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + pagina.toString());
//            System.out.println("despues del error: " + request.getContextPath() + pagina.toString());
            context.redirect(request.getContextPath() + pagina.toString());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void direccionarLogin() {
        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/login.xhtml");
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_LOGIN.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void direccionVistaCaducada() {
        ControlSesion ms = new ControlSesion();
        ms.cerrarSesion();
        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/vista-caducada.xhtml");
//            System.out.println("vista caducada: " + request.getContextPath() + PaginaConfig.PAGINA_VISTA_CADUCADA.getUrl());
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_VISTA_CADUCADA.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void direccionarRecuperacionClave() {
        ControlSesion ms = new ControlSesion();
        ms.cerrarSesion();
        try {
//            TO-DO
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_RECUPERAR_CLAVE.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public BeanDireccionamiento() {
    }
}
