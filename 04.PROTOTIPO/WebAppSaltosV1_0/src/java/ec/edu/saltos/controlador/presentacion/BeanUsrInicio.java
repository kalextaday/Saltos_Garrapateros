package ec.edu.saltos.controlador.presentacion;

import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.OpcionPerfil;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
import java.io.Serializable;
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
public class BeanUsrInicio extends FiltroAcceso implements Serializable {

    static final Logger LOG = Logger.getLogger(BeanUsrInicio.class.getName());

    private String homePage;
    private List<Opcion> listaPantallas;
    private Boolean mostrarPanel = false;
    private String mensajeBienvenida;
    private String tituloBienvenida;
    
    private List<OpcionPerfil> listaOpcionPerfil;
    private List<Opcion> listaOpcions;

    public BeanUsrInicio() {
        super(FacesContext.getCurrentInstance().getExternalContext());
        homePage = "";
        tituloBienvenida = EstadosConfig.AMBIENTE_DEV.getDescripcion();
    }

    @PostConstruct
    public void init() {
        listaPantallas = obtenerPantallas();
    }

    public List<Opcion> obtenerPantallas() {
        try {
            ControlSesion sesion = new ControlSesion();
            
            DAOOpcion daorecurso=new DAOOpcion();
            DAOOpcionPerfil daoOpcPer=new DAOOpcionPerfil();

            listaOpcionPerfil=daoOpcPer.opcionesPorPerfil(Integer.parseInt(sesion.obtenerIdPerfilSesionActiva()));
            
            
            if (listaOpcionPerfil != null) {
                if (listaOpcionPerfil != null && !listaOpcionPerfil.isEmpty()) {
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

        } catch (NumberFormatException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return listaOpcions;
    }

    public void cargandoPantalla(Integer _idPantalla) {
        System.out.println("cargando pantalla.. " + _idPantalla);
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
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
