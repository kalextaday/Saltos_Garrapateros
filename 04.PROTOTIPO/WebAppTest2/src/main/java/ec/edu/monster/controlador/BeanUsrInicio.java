package ec.edu.monster.controlador;

import ec.edu.monster.config.ControlSesion;
import ec.edu.monster.config.EstadosConfig;
import ec.edu.monster.filter.FiltroAcceso;
//import ec.edu.monster.modelo.Recurso;
//import ec.edu.monster.dao.DAORecursoPantalla;
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
public class BeanUsrInicio extends FiltroAcceso implements Serializable {

    static final Logger LOG = Logger.getLogger(BeanUsrInicio.class.getName());

    private String homePage;
    //private List<Recurso> listaPantallas;
    private Boolean mostrarPanel = false;
    private String mensajeBienvenida;
    private String tituloBienvenida;

    public BeanUsrInicio() {

        super(FacesContext.getCurrentInstance().getExternalContext());
        ControlSesion ms = new ControlSesion();
        homePage = "";
        tituloBienvenida = "SUPER ADMINISTRACION " + EstadosConfig.AMBIENTE.getDescripcion();
        if (ms.obtenerEstadoSesionUsuario() == true) {
            if (ms.obtenerRucEntidadSesionActiva().equals(EstadosConfig.INFO_EMAGIC.getId().toString())) {
                homePage = "ROOT";

            } else {
                homePage = "FANTASMA";

            }
        }
    }

    @PostConstruct
    public void init() {
        //listaPantallas = obtenerPantallas();
    }

    /*
    public List<Recurso> obtenerPantallas() {
        List<Recurso> list = null;
        try {
            ControlSesion cs = new ControlSesion();

            DAORecursoPantalla daorp = new DAORecursoPantalla();
            Recurso yggdrasil = daorp.obtenerYggdrasil();
            if (yggdrasil != null) {
                list = daorp.obtenerPantallasAppPorPerfil(yggdrasil.getIdRecurso(), Integer.parseInt(cs.obtenerIdPerfilSesionActiva()), cs.obtenerRucEntidadSesionActiva());
                if (list != null && !list.isEmpty()) {
                    mostrarPanel = true;
                    mensajeBienvenida = "Para iniciar seleccione un item del menú.";
                    return list;
                } else {
                    mostrarPanel = true;
                    list = new ArrayList<>();
                    mensajeBienvenida = "No tiene aplicaciones comuníquese con el Administrador del Sistema.";// <i class=\"fa fa-bars fa-3x\" aria-hidden=\"true\"></i> ";
                    System.out.println("No tiene aplicaciones comuniquese con el administrador");
                    return list;

                }
            } else {
                System.out.println("Sistema sin aplicaciones");
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return list;
    }*/

    public void cargandoPantalla(Integer _idPantalla) {
        System.out.println("cargando pantalla.. " + _idPantalla);
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /*
    public List<Recurso> getListaPantallas() {
        return listaPantallas;
    }

    public void setListaPantallas(List<Recurso> listaPantallas) {
        this.listaPantallas = listaPantallas;
    }*/

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
