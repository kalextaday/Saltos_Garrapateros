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
    
    private String getIdEmpleado() {
        return sesion.getAttribute("idEmpleado").toString();
    }

    private void setIdEmpleado(String _idEmpleado) {
        sesion.setAttribute("idEmpleado", _idEmpleado);
    }


    /**
     * Método que recibe los parámetros del usuario que inicia sesión.
     *
     * @param _idPerfil
     * @param _idUsuario
     * @param _nombreUsuario
     * @param _idEmpleado
     *
     */
    public void iniciarSesion(String _idPerfil, String _idUsuario, String _nombreUsuario, String _idEmpleado) {
        setIdPerfil(_idPerfil);
        setIdUsuario(_idUsuario);
        setNombreUsuario(_nombreUsuario);
        setIdEmpleado(_idEmpleado);
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

}
