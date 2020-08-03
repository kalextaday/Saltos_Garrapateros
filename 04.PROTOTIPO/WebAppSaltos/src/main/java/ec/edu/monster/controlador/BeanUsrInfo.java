package ec.edu.monster.controlador;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author guffenix
 */
@ManagedBean
@SessionScoped
public class BeanUsrInfo implements Serializable {

//    private String session;
//
//    public String getSession() {
//        ControlSesion cs = new ControlSesion();
//        String _session = cs.obtenerIdentificacionUsuario();
//        if (!_session.equals("")) {
//            return _session;
//        } else {
//            return session;
//        }
//    }
//
//    public void setSession(String session) {
//        this.session = session;
//    }
//
//    public void cerrarSesion() {
//        ControlSesion ms = new ControlSesion();
//        ms.cerrarSesion();
//        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, PaginaConfig.PAGINA_LOGIN.getUrl());
//    }
}
