package ec.edu.saltos.config;

import java.io.Serializable;
import java.util.Enumeration;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guffenix
 */
public class ControlSesion implements Serializable {

    private HttpSession sesion;

    public ControlSesion() {
        sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sesion.setMaxInactiveInterval(600);
    }

    public ControlSesion(HttpSession _sesion) {
        sesion = _sesion;
    }

    public HttpSession getSesion() {
        return sesion;
    }

    public void setSesion(HttpSession _sesion) {
        this.sesion = _sesion;
    }

    private String getIdPerfil() {
        return sesion.getAttribute("idPerfil").toString();
    }

    private void setIdPerfil(String _idPerfil) {
        sesion.setAttribute("idPerfil", _idPerfil);
    }

    private String getIdPersona() {
        return sesion.getAttribute("idPersona").toString();
    }

    private void setIdPersona(String _idPersona) {
        sesion.setAttribute("idPersona", _idPersona);
    }

    private String getNombreUsuario() {
        return sesion.getAttribute("nombreUsuario").toString();
    }

    private void setNombreUsuario(String _nombreUsuario) {
        sesion.setAttribute("nombreUsuario", _nombreUsuario);
    }
    
    private String getUsuarioAcceso() {
        return sesion.getAttribute("idUsuarioAcceso").toString();
    }

    private void setUsuarioAcceso(String _idUsuarioAcceso) {
        sesion.setAttribute("idUsuarioAcceso", _idUsuarioAcceso);
    }

    /**
     * Método que recibe los parámetros del usuario que inicia sesión.
     *
     * @param _idPerfil
     * @param _idPersona
     * @param _nombreUsuario
     * @param _idUsuarioAcceso
     */
    public void iniciarSesion(String _idPersona, String _idPerfil,  String _nombreUsuario, String _idUsuarioAcceso) {
        setIdPersona(_idPersona);
        setIdPerfil(_idPerfil);
        setNombreUsuario(_nombreUsuario);
        setUsuarioAcceso(_idUsuarioAcceso);
//        System.out.println("valores: " + _idPerfil + " " + _rucEntidad + " " + _idUsuario + " " + _nombreUsuario + " " + _identificacionUsuario + " " + _token);
    }

    /**
     * Metodo que elimina los atributos de la sesion http
     */
    public void cerrarSesion() {
        Enumeration<String> enume = sesion.getAttributeNames();
        while (enume.hasMoreElements()) {
            sesion.removeAttribute(enume.nextElement());
        }
    }

    /**
     * Obtiene el IdPerfil de usuario en sesion
     *
     * @return el IdPerfil de usuario
     */
    public String obtenerIdPerfilSesionActiva() {
        if (obtenerEstadoSesionUsuario()) {
            return getIdPerfil();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el nombre de usuario en sesion
     *
     * @return el nombre de usuario
     */
    public String obtenerNombreUsuarioSesionActiva() {
        if (obtenerEstadoSesionUsuario()) {
            return getNombreUsuario();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el id de la persona en sesion
     *
     * @return obtenerIdUsuarioSesionActiva en sesion
     */
    public String obtenerIdPersonaSesionActiva() {
        if (obtenerEstadoSesionUsuario()) {
            return getIdPersona();
        } else {
            return null;
        }
    }
    
    /**
     * Obtiene el id del usuario acceso en sesion
     *
     * @return obtenerIdUsuarioSesionActiva en sesion
     */
    public String obtenerIdUsuarioAccesoSesionActiva() {
        if (obtenerEstadoSesionUsuario()) {
            return getUsuarioAcceso();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el estado de la sesión.
     *
     * @return true si existe sesión, de lo contrario devuelve false.
     */
    public boolean obtenerEstadoSesionUsuario() {
        boolean success = false;
        if (sesion != null) {
            try {
                if (getIdPersona() != null && !getIdPersona().equals("")) {
                    success = true;
                }
            } catch (Exception e) {

            }
        }
        return success;
    }

}
