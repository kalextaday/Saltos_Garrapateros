package ec.edu.monster.controlador;
/*
import ec.fin.emagic.dao.DAOEntidad;
import ec.fin.emagic.dao.DAOPerfil;
import ec.fin.emagic.modelo.Entidad;
import ec.fin.emagic.modelo.Perfil;
*/
import ec.edu.monster.filtro.ControlSesion;
import ec.edu.monster.config.PaginaConfig;
import ec.edu.monster.config.EstadosConfig;
import ec.edu.monster.utilidades.SOUtiles;
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
public class BeanSessionUsuario implements Serializable {

    static final Logger LOG = Logger.getLogger(BeanSessionUsuario.class.getName());

    private String nombreUsuario;
    private String rucEntidad;
    //private Perfil perfil;
    //private Entidad miEntidad;
    private String tituloBienvenida;

    private String ipUsuario;

    public BeanSessionUsuario() {
        ipUsuario = SOUtiles.obtenerIP();
        tituloBienvenida = EstadosConfig.AMBIENTE.getDescripcion();
        /*
        try {
            ControlSesion cs = new ControlSesion();
            if (cs.obtenerEstadoSesionUsuario()) {
                nombreUsuario = cs.obtenerNombreUsuarioSesionActiva();
                rucEntidad = cs.obtenerRucEntidadSesionActiva();
                miEntidad = new DAOEntidad().obtenerEntidadPorId(rucEntidad);
                perfil = new DAOPerfil().obtenerPerfilPorId(Integer.parseInt(cs.obtenerIdPerfilSesionActiva()));

            }
        } catch (NumberFormatException e) {

        }*/

    }

    //funcionalidad
    public void recargar() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getRequestURL().toString());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Recargando {0}", request.getRequestURL().toString());
        }
    }

    public void cerrarSesion() {
        /*
        ControlSesion ms = new ControlSesion();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        ms.cerrarSesion();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + PaginaConfig.PAGINA_LOGIN.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Terminando sesión desde ip:{0}", ipUsuario);
        }*/
    }
//    fin-funcionalidad

    public void irPaginaInicio() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + PaginaConfig.PAGINA_INICIO.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Accediendo a {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_INICIO.getUrl(), ipUsuario});
        }

    }

    public void irPaginaSoporte() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_SOPORTE.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso área de soporte {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_SOPORTE.getUrl(), ipUsuario});
        }
    }

    public void irPaginaConfiguracionCuenta() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_CONFIGURACION_CUENTA.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Configuración Cuenta {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_CONFIGURACION_CUENTA.getUrl(), ipUsuario});
        }
    }

    public void irPaginaConfiguracionCorreo() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_CONFIGURACION_CORREO.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Configuración Correo {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_CONFIGURACION_CORREO.getUrl(), ipUsuario});
        }
    }

    public void irPaginaTerminosPrivacidad() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_TERMINOS_PRIVACIDAD.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Página de Términos de Privacidad {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_TERMINOS_PRIVACIDAD.getUrl(), ipUsuario});
        }
    }

    public void irPaginaRegistroActividad() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_REGISTRO_ACTIVIDAD.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Registro de Actividad {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_REGISTRO_ACTIVIDAD.getUrl(), ipUsuario});
        }
    }

    public void irPaginaInformacionUsuario() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_INFORMACION_USUARIO.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Página de Información de Usuario {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_INFORMACION_USUARIO.getUrl(), ipUsuario});
        }
    }
    
    public void irPaginaConfiguracionClave() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_CONFIGURACION_CLAVE.getUrl());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Página de Información de Usuario {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_INFORMACION_USUARIO.getUrl(), ipUsuario});
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRucEntidad() {
        return rucEntidad;
    }

    public void setRucEntidad(String rucEntidad) {
        this.rucEntidad = rucEntidad;
    }

    /*
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Entidad getMiEntidad() {
        return miEntidad;
    }

    public void setMiEntidad(Entidad miEntidad) {
        this.miEntidad = miEntidad;
    }*/

    public String getTituloBienvenida() {
        return tituloBienvenida;
    }

    public void setTituloBienvenida(String tituloBienvenida) {
        this.tituloBienvenida = tituloBienvenida;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

}
