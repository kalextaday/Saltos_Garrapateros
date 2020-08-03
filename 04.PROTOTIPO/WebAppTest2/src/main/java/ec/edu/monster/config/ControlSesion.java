package ec.edu.monster.config;

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

    private String getRucEntidad() {
        return sesion.getAttribute("RucEntidad").toString();
    }

    private void setRucEntidad(String _rucEntidad) {
        sesion.setAttribute("RucEntidad", _rucEntidad);
    }

    private String getIdUsuario() {
        return sesion.getAttribute("idUsuario").toString();
    }

    private void setIdUsuario(String _idUsuario) {
        sesion.setAttribute("idUsuario", _idUsuario);
    }

    private String getNombreUsuario() {
        return sesion.getAttribute("nombreUsuario").toString();
    }

    private void setNombreUsuario(String _nombreUsuario) {
        sesion.setAttribute("nombreUsuario", _nombreUsuario);
    }

    private String getIdentificacionUsuarioInterno() {
        return sesion.getAttribute("identificacionUsuario").toString();
    }

    public void setIdentificacionUsuarioInterno(String _identificacionUsuario) {
        sesion.setAttribute("identificacionUsuario", _identificacionUsuario);
    }

    public void setToken(String _token) {
        sesion.setAttribute("token", _token);
    }

    private String getTokenSesion() {
        return sesion.getAttribute("token").toString();
    }

    /**
     * Método que recibe los parámetros del usuario que inicia sesión.
     *
     * @param _idPerfil
     * @param _rucEntidad
     * @param _idUsuario
     * @param _nombreUsuario
     * @param _identificacionUsuario
     * @param _token
     *
     */
    public void iniciarSesion(String _idPerfil, String _rucEntidad, String _idUsuario, String _nombreUsuario, String _identificacionUsuario, String _token) {
        setIdPerfil(_idPerfil);
        setRucEntidad(_rucEntidad);
        setIdUsuario(_idUsuario);
        setNombreUsuario(_nombreUsuario);
        setIdentificacionUsuarioInterno(_identificacionUsuario);
        setToken(_token);
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
     * Obtiene el RucEntidad de usuario en sesion
     *
     * @return el RucEntidad de usuario
     */
    public String obtenerRucEntidadSesionActiva() {
        if (obtenerEstadoSesionUsuario()) {
            return getRucEntidad();
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
     * Obtiene el id del usuario en sesion
     *
     * @return obtenerIdUsuarioSesionActiva en sesion
     */
    public String obtenerIdUsuarioSesionActiva() {
        if (obtenerEstadoSesionUsuario()) {
            return getIdUsuario();
        } else {
            return null;
        }
    }

    /**
     * Obtiene el identificacion del usuario en sesion
     *
     * @return obtenerIdUsuarioSesionActiva en sesion
     */
    public String obtenerIdentificacionUsuario() {
        if (obtenerEstadoSesionUsuario()) {
            return getIdentificacionUsuarioInterno();
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
                if (getIdUsuario() != null && !getIdUsuario().equals("")) {
                    success = true;
                }
            } catch (Exception e) {

            }
        }
        return success;
    }

    public String obtenerTokenSesion() {
        if (obtenerEstadoSesionUsuario()) {
            return getTokenSesion();
        } else {
            return null;
        }
    }

}
