package ec.edu.monster.controlador;

/*
import ec.fin.emagic.config.ControlSesion;
import ec.fin.emagic.config.DirectorioConfig;
import ec.fin.emagic.config.DocumentoConfig;
import ec.fin.emagic.config.EstadosConfig;
import ec.fin.emagic.config.util.ImagenUtiles;
import ec.fin.emagic.dao.DAOEntidad;
import ec.fin.emagic.dao.DAOPerfil;
import ec.fin.emagic.dao.DAORecursoPantalla;
import ec.fin.emagic.modelo.Entidad;
import ec.fin.emagic.modelo.Perfil;
import ec.fin.emagic.modelo.Recurso;
*/
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author guffenix
 */
@ManagedBean
@ViewScoped
public final class BeanMenu implements Serializable {

    private static final Logger LOG = Logger.getLogger(BeanMenu.class.getName());

    private MenuModel model;
    private boolean mostrarMenu;
    //private Entidad unaEntidad;
    private String pathLogoEntidad;

    public BeanMenu() throws Exception {
        /*
        mostrarMenu = false;
        ControlSesion sesion = new ControlSesion();

        if (sesion.obtenerEstadoSesionUsuario()) {
            model = new DefaultMenuModel();
            unaEntidad = new DAOEntidad().obtenerEntidadPorId(sesion.obtenerRucEntidadSesionActiva());
            pathLogoEntidad = unaEntidad.getEntidadLogo() != null ? ImagenUtiles.codificar(DirectorioConfig.DIRECTORIO_LOGO_ENTIDAD.getUrl().concat(unaEntidad.getEntidadLogo())) : ImagenUtiles.codificar(DirectorioConfig.DIRECTORIO_LOGO_ENTIDAD.getUrl().concat("images/entidad.png"));

            DAOPerfil daoPerfiles = new DAOPerfil();
            Perfil perfilEncontrado = daoPerfiles.obtenerPerfilPorId(Integer.parseInt(sesion.obtenerIdPerfilSesionActiva()));

            if (perfilEncontrado != null) {
                //TODO - activar cuando sean ACT
                if (perfilEncontrado.getPerfilEstatus().equals(EstadosConfig.PERFIL_ACTIVO.getCodigo())) {
                    Recurso list = new DAORecursoPantalla().obtenerYggdrasil();
                    if (list != null) {
                        mostrarMenu = true;
                        crearMenuDinamico(model, list.getIdRecurso(), Integer.parseInt(sesion.obtenerIdPerfilSesionActiva()), sesion.obtenerRucEntidadSesionActiva());
                    } else {
                        LOG.info("Sistema sin aplicaciones. Contactese con el Administrador del sistema.");
                        System.out.println("Sistema sin aplicaciones. Contactese con el Administrador del sistema.");
                    }

                }
            }
        }
        */
    }

    public void crearMenuDinamico(Object menuYgdrasil, Integer _idRecurso, Integer _idPerfil, String _rucEntidad) throws Exception {// throws Exception {
        /*
        List<Recurso> recursos = new DAORecursoPantalla().obtenerPantallasPorPerfilADM(_idRecurso, _idPerfil, _rucEntidad);
        recursos.stream().forEach(rx -> {
            if (rx.getRecPestana().equals(EstadosConfig.RECURSO_CATEGORIA.getCodigo())) {
                DefaultSubMenu itemMenu = new DefaultSubMenu(rx.getRecNombre());
                itemMenu.setIcon(rx.getRecIcono());
                if (menuYgdrasil instanceof DefaultMenuModel) {
                    ((DefaultMenuModel) menuYgdrasil).getElements().add(itemMenu);
                    try {
                        crearMenuDinamico(itemMenu, rx.getIdRecurso(), _idPerfil, _rucEntidad);
                    } catch (Exception ex) {
                        Logger.getLogger(BeanMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    ((DefaultSubMenu) menuYgdrasil).getElements().add(itemMenu);
                    try {
                        crearMenuDinamico(itemMenu, rx.getIdRecurso(), _idPerfil, _rucEntidad);
                    } catch (Exception ex) {
                        Logger.getLogger(BeanMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (!rx.getRecStatus().equals(EstadosConfig.RECURSO_OCULTO.getCodigo())) {
                DefaultMenuItem item = new DefaultMenuItem(rx.getRecNombre());
                item.setOutcome(rx.getRecPagina().concat(DocumentoConfig.DOT.getValor()).concat(DocumentoConfig.EXTENSION_XHTML.getValor()));
                item.setIcon(rx.getRecIcono());
                if (menuYgdrasil instanceof DefaultMenuModel) {
                    ((DefaultMenuModel) menuYgdrasil).getElements().add(item);
                } else {
                    ((DefaultSubMenu) menuYgdrasil).getElements().add(item);
                }
            }
        });
*/
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public boolean isMostrarMenu() {
        return mostrarMenu;
    }

    public void setMostrarMenu(boolean mostrarMenu) {
        this.mostrarMenu = mostrarMenu;
    }
/*
    public Entidad getUnaEntidad() {
        return unaEntidad;
    }

    public void setUnaEntidad(Entidad unaEntidad) {
        this.unaEntidad = unaEntidad;
    }*/

    public String getPathLogoEntidad() {
        return pathLogoEntidad;
    }

    public void setPathLogoEntidad(String pathLogoEntidad) {
        this.pathLogoEntidad = pathLogoEntidad;
    }

}
