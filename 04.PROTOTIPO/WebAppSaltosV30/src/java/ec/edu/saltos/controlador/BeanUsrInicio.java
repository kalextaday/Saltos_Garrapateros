package ec.edu.saltos.controlador;

import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.dao.DAOPermiso;
import ec.edu.saltos.dao.DAORecurso;
import ec.edu.saltos.filter.FiltroAcceso;
import ec.edu.saltos.modelo.Permiso;
import ec.edu.saltos.modelo.Recurso;
import ec.edu.saltos.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author guffenix
 */
@ManagedBean
@ViewScoped
public class BeanUsrInicio implements Serializable {

    static final Logger LOG = Logger.getLogger(BeanUsrInicio.class.getName());

    private List<Recurso> listaPantallas;
    private Boolean mostrarPanel = false;
    private String mensajeBienvenida;
    private String tituloBienvenida;
    
    private Usuario usuario;
    private List<Permiso> listaPermisos;
    private List<Recurso> listaRecursos;

    public BeanUsrInicio() {
        tituloBienvenida = EstadosConfig.AMBIENTE.getDescripcion();
        listaRecursos=new ArrayList<>();
        usuario=new Usuario();
        usuario=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    }

    @PostConstruct
    public void init() {
        listaPantallas = obtenerPantallas();
    }

    public List<Recurso> obtenerPantallas() {
        try {
            
            DAORecurso daorecurso=new DAORecurso();
            DAOPermiso daopermiso=new DAOPermiso();

            listaPermisos=daopermiso.permisosPerfil(usuario.getPerfil().getIdPerfil());

            listaPermisos.forEach((p) -> {
                listaRecursos.add(daorecurso.obtenerPorId(p.getRecurso().getIdRecurso()));
            });
            
            if (listaRecursos != null) {
                if (listaRecursos != null && !listaRecursos.isEmpty()) {
                    mostrarPanel = true;
                    mensajeBienvenida = "Para iniciar seleccione un item del menú.";
                    return listaRecursos;
                } else {
                    mostrarPanel = true;
                    mensajeBienvenida = "No tiene aplicaciones comuníquese con el Administrador del Sistema.";
                    
                    System.out.println("No tiene aplicaciones comuniquese con el administrador");
                    return listaRecursos;

                }
            } else {
                System.out.println("Sistema sin aplicaciones");
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return listaRecursos;
    }

    public void cargandoPantalla(Integer _idPantalla) {
        System.out.println("cargando pantalla.. " + _idPantalla);
    }

    public List<Recurso> getListaPantallas() {
        return listaPantallas;
    }

    public void setListaPantallas(List<Recurso> listaPantallas) {
        this.listaPantallas = listaPantallas;
    }

    public Boolean getMostrarPanel() {
        return mostrarPanel;
    }

    public void setMostrarPanel(Boolean mostrarPanel) {
        this.mostrarPanel = mostrarPanel;
    }

    public String getMensajeBienvenida() {
        return mensajeBienvenida;
    }

    public void setMensajeBienvenida(String mensajeBienvenida) {
        this.mensajeBienvenida = mensajeBienvenida;
    }

    public String getTituloBienvenida() {
        return tituloBienvenida;
    }

    public void setTituloBienvenida(String tituloBienvenida) {
        this.tituloBienvenida = tituloBienvenida;
    }

}
