package ec.edu.saltos.controlador.presentacion;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.modelo.AsignarPerfil;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.OpcionPerfil;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
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

    private List<Opcion> listaPantallas;
    private Boolean mostrarPanel = false;
    private String mensajeBienvenida;
    private String tituloBienvenida;
    
    private AsignarPerfil perfilAccesado;
    
    private UsuarioAcceso usuarioAcceso;
    private List<OpcionPerfil> listaOpcionPerfil;
    private List<Opcion> listaOpcions;

    public BeanUsrInicio() {
        tituloBienvenida = EstadosConfig.AMBIENTE.getDescripcion();
        listaOpcions=new ArrayList<>();
        perfilAccesado=new AsignarPerfil();
        perfilAccesado=(AsignarPerfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("asignarPerfil");

    }

    @PostConstruct
    public void init() {
        listaPantallas = obtenerPantallas();
    }

    public List<Opcion> obtenerPantallas() {
        try {
            
            DAOOpcion daorecurso=new DAOOpcion();
            DAOOpcionPerfil daopermiso=new DAOOpcionPerfil();

            listaOpcionPerfil=daopermiso.opcionesPerfil(perfilAccesado.getPerfil().getIdPerfil());

            listaOpcionPerfil.forEach((p) -> {
                listaOpcions.add(daorecurso.obtenerPorId(p.getOpcion().getIdOpcion()));
            });
            
            if (listaOpcions != null) {
                if (listaOpcions != null && !listaOpcions.isEmpty()) {
                    mostrarPanel = true;
                    mensajeBienvenida = "Para iniciar seleccione un item del menú.";
                    return listaOpcions;
                } else {
                    mostrarPanel = true;
                    mensajeBienvenida = "No tiene aplicaciones comuníquese con el Administrador del Sistema.";
                    
                    System.out.println("No tiene aplicaciones comuniquese con el administrador");
                    return listaOpcions;

                }
            } else {
                System.out.println("Sistema sin aplicaciones");
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return listaOpcions;
    }

    public void cargandoPantalla(Integer _idPantalla) {
        System.out.println("cargando pantalla.. " + _idPantalla);
    }

    public List<Opcion> getListaPantallas() {
        return listaPantallas;
    }

    public void setListaPantallas(List<Opcion> listaPantallas) {
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
