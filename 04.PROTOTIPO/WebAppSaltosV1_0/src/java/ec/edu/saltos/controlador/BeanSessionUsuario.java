package ec.edu.saltos.controlador;



import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.config.PaginaConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.util.SOUtiles;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;
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

    private Persona persona;
    private String nombrePersona;
    private Perfil perfil;
    private String tituloBienvenida;
    
    private AsignarPerfil perfilAccesado;

    private String ipUsuario;

    public BeanSessionUsuario() {
        ipUsuario = SOUtiles.obtenerIP();
        tituloBienvenida = EstadosConfig.AMBIENTE_DEV.getDescripcion();
        /*
        perfilAccesado=new AsignarPerfil();
        perfilAccesado=(AsignarPerfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("asignarPerfil");
        nombrePersona=perfilAccesado.getUsuarioAcceso().getUsrAccesoNombre();
        perfil=perfilAccesado.getPerfil();
        */
        try{
            ControlSesion cs = new ControlSesion();
            if (cs.obtenerEstadoSesionUsuario()) {
                nombrePersona = cs.obtenerNombreUsuarioSesionActiva();
                perfil = new DAOPerfil().obtenerPorId(Integer.parseInt(cs.obtenerIdPerfilSesionActiva()));
            }
        }catch(NumberFormatException e){
            
        }
        
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
            LOG.log(Level.INFO, "Recargando {0} desde ip {1}", new Object[]{request.getRequestURL().toString(), ipUsuario});
        }
    }

    public void cerrarSesion() {
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
        }
    }
//    fin-funcionalidad

    public void irPaginaInicio(){
        /*
        try{
            FacesContext context=FacesContext.getCurrentInstance();
        
            context.getExternalContext().redirect(context.getExternalContext()
                        .getRequestContextPath()+PaginaConfig.PAGINA_INICIO.getUrl());
        }catch(IOException ex){
            System.out.println("ex:"+ex);
        }
        */
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
            System.out.println("e:"+ex);
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
            System.out.println("e:"+ex);
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Configuración Cuenta {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_CONFIGURACION_CUENTA.getUrl(), ipUsuario});
        }
    }

    public void irPaginaTerminosPrivacidad() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_TERMINOS_PRIVACIDAD.getUrl());
        } catch (IOException ex) {
            System.out.println("e:"+ex);
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Página de Términos de Privacidad {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_TERMINOS_PRIVACIDAD.getUrl(),ipUsuario});
        }
    }

    public void irPaginaRegistroActividad() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_REGISTRO_ACTIVIDAD.getUrl());
        } catch (IOException ex) {
            System.out.println("e:"+ex);
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Registro de Actividad {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_REGISTRO_ACTIVIDAD.getUrl(), ipUsuario});
        }
    }

    public void irPaginaInformacionPersona() {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        try {
            context.redirect(request.getContextPath() + PaginaConfig.PAGINA_INFORMACION_USUARIO.getUrl());
        } catch (IOException ex) {
            System.out.println("e:"+ex);
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            LOG.log(Level.INFO, "Ingreso a Página de Información de Persona {0} desde ip:{1}", new Object[]{PaginaConfig.PAGINA_INFORMACION_USUARIO.getUrl(),ipUsuario});
        }
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    public String getTituloBienvenida() {
        return tituloBienvenida;
    }

    public void setTituloBienvenida(String tituloBienvenida) {
        this.tituloBienvenida = tituloBienvenida;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public AsignarPerfil getPerfilAccesado() {
        return perfilAccesado;
    }

    public void setPerfilAccesado(AsignarPerfil perfilAccesado) {
        this.perfilAccesado = perfilAccesado;
    }

    
}
