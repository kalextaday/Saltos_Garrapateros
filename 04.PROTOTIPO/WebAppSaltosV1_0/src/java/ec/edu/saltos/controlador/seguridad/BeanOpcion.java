/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.seguridad;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.minidao.IconoX;
import ec.edu.saltos.minidao.MDAOIconox;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanOpcion extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanOpcion.class.getName());
    
    private TreeNode opcionPadre;
    private TreeNode opcionSeleccionado;
    private Opcion unaOpcionSeleccionada;
    private Opcion opcionSeleccionCrud;
    private Boolean permiteUrl;
    private Boolean permiteEliminar;
    
    private List<IconoX> iconos;
    private IconoX iconoSeleccionado;
    
    /**
     * Creates a new instance of BeanOpcion
     */
    public BeanOpcion() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }
    
    @PostConstruct
    public void init(){
        iconos = new MDAOIconox().getIconosx();
        permiteEliminar = false;
        permiteUrl = false;
        initArbolPermisos();
    }
    
    public void initArbolPermisos() {
        if (opcionPadre == null) {
            crearArbolMain();
        }
    }
    
    public void crearArbolMain() {
        Opcion origen = obtenerOrigen();
        opcionPadre = new DefaultTreeNode();
        TreeNode categoria = new DefaultTreeNode(origen, opcionPadre);
        categoria.setExpanded(true);
        crearArbolSudo(categoria);

    }
    
    public Opcion obtenerOrigen() {
        Opcion tmp = new Opcion();
        try {

            DAOOpcion dao = new DAOOpcion();
            Opcion list = dao.obtenerOrigen();
            if (list != null) {
                tmp = list;
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    
    public void crearArbolSudo(TreeNode nodo) {
        try {
            DAOOpcion dao = new DAOOpcion();
            List<Opcion> opciones = dao.obtenerOpcionesSudo(((Opcion) nodo.getData()).getIdOpcion());
            TreeNode nodeNuevo;
            for (Opcion opcion : opciones) {
                nodeNuevo = new DefaultTreeNode(opcion, nodo);
                crearArbolSudo(nodeNuevo);
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public Opcion preparaCrear() {
        permiteUrl = false;
        iconoSeleccionado = null;
        LOG.log(Level.INFO, "Preparando la nueva opcion");
        
        opcionSeleccionCrud = new Opcion();
        opcionSeleccionCrud.setOpcPagina(EstadosConfig.OPCION_SIN_URL.getCodigo());
        opcionSeleccionCrud.setOpcion(unaOpcionSeleccionada);
        actualizarIcono();
        
        PrimeUtiles.primeExecute("PF('wv-crear').show();");
        return opcionSeleccionCrud;

    }

    public Opcion preparaActualizar() {
        permitirUrlActalizar();
        LOG.log(Level.INFO, "Preparando opcion para editar");
        PrimeUtiles.primeExecute("PF('wv-editar').show();");
        iconoSeleccionado = new MDAOIconox().obtenerPorClase(unaOpcionSeleccionada.getOpcNombre());
        return unaOpcionSeleccionada;

    }

    public Opcion preparaEliminar() {
        LOG.log(Level.INFO, "Opcion {0} listo para eliminar.", unaOpcionSeleccionada.getOpcNombre());
                
        PrimeUtiles.primeExecute("PF('wv-eliminar').show();");
        return unaOpcionSeleccionada;
    }
    
    public void actualizarIcono() {
        if (opcionSeleccionCrud != null && iconoSeleccionado != null) {
            opcionSeleccionCrud.setOpcIcono(iconoSeleccionado.getClase());
            System.out.println(opcionSeleccionCrud.getOpcIcono());
        }
        if (unaOpcionSeleccionada != null && iconoSeleccionado != null) {
            unaOpcionSeleccionada.setOpcIcono(iconoSeleccionado.getClase());
            System.out.println(unaOpcionSeleccionada.getOpcIcono());
        }
    }
    
    public void permitirUrlActalizar() {
        if (unaOpcionSeleccionada != null) {
            switch (unaOpcionSeleccionada.getOpcEstatus()) {
                case "MOD":
                    permiteUrl = false;
                    break;
                case "APP":
                    permiteUrl = true;
                    break;
                default:
                    permiteUrl = false;
                    break;
            }
        }
    }
    
    public void permitirUrl() {
        if (opcionSeleccionCrud != null) {
            switch (opcionSeleccionCrud.getOpcEstatus()) {
                case "MOD":
                    permiteUrl = false;
                    opcionSeleccionCrud.setOpcPagina(EstadosConfig.OPCION_SIN_URL.getCodigo());
                    break;
                case "APP":
                    permiteUrl = true;
                    opcionSeleccionCrud.setOpcPagina(EstadosConfig.OPCION_CONSTRUCCION.getCodigo());
                    break;
                default:
                    permiteUrl = false;
                    break;
            }
        }
    }
    
    public void permitirEliminar() {
        permiteEliminar = opcionSeleccionado.isLeaf();
    }
    
    public void agregarOpcion() {
        try {
            if (iconoSeleccionado != null) {
                DAOOpcion daor = new DAOOpcion();
                //opcionSeleccionCrud.setOpcEstatus(EstadosConfig.OPCION_PLANTILLA.getCodigo());
                daor.guardar(opcionSeleccionCrud);

                opcionPadre = null;

                LOG.log(Level.INFO, "Nueva opcion creado");
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Nueva opcion creada correctamente.");
            } else {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error:", "El campo icono es obligatorio.");
                LOG.log(Level.SEVERE, "El campo icono es obligatorio");
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "No se ha podido crear el recurso, verifique su conexión a internet e intente de nuevo.");
        } finally {
            initArbolPermisos();
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }
    
    public void modificarOpcion() {

        try {
            DAOOpcion daor = new DAOOpcion();
            if (daor.editar(unaOpcionSeleccionada)) {
                unaOpcionSeleccionada = null;
                opcionPadre = null;
                initArbolPermisos();
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Opcion actualizada correctamente.");

            } else {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error:", "Error al actualizar.");
            }
        }catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            PrimeUtiles.primeExecute("PF('wv-editar').hide();");
        }
    }
    
    public void eliminarOpcion() {
        try {
            DAOOpcion daor = new DAOOpcion();

            
            if (daor.eliminar(unaOpcionSeleccionada)) {
                unaOpcionSeleccionada = null;
                opcionPadre = null;
                initArbolPermisos();
                LOG.log(Level.INFO, "Opcion eliminado correctamente");
                
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Opcion eliminada correctamente");
            } else {
                LOG.log(Level.INFO, "No se puede eliminar recurso, por favor eliminar permisos manualmente.");
                
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error:", "No se puede eliminar la opcion, por favor eliminar permisos manualmente.");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            PrimeUtiles.primeExecute("PF('wv-eliminar').hide();");
        }
    }

    public TreeNode getOpcionPadre() {
        return opcionPadre;
    }

    public void setOpcionPadre(TreeNode opcionPadre) {
        this.opcionPadre = opcionPadre;
    }

    public TreeNode getOpcionSeleccionado() {
        return opcionSeleccionado;
    }

    public void setOpcionSeleccionado(TreeNode opcionSeleccionado) {
        //this.opcionSeleccionado = opcionSeleccionado;
        this.opcionSeleccionado = opcionSeleccionado;
        if (this.opcionSeleccionado != null) {
            this.unaOpcionSeleccionada = (Opcion) this.opcionSeleccionado.getData();
        }
    }

    public Opcion getUnaOpcionSeleccionada() {
        return unaOpcionSeleccionada;
    }

    public void setUnaOpcionSeleccionada(Opcion unaOpcionSeleccionada) {
        this.unaOpcionSeleccionada = unaOpcionSeleccionada;
    }

    public Opcion getOpcionSeleccionCrud() {
        return opcionSeleccionCrud;
    }

    public void setOpcionSeleccionCrud(Opcion opcionSeleccionCrud) {
        this.opcionSeleccionCrud = opcionSeleccionCrud;
    }

    public Boolean getPermiteUrl() {
        return permiteUrl;
    }

    public void setPermiteUrl(Boolean permiteUrl) {
        this.permiteUrl = permiteUrl;
    }

    public Boolean getPermiteEliminar() {
        return permiteEliminar;
    }

    public void setPermiteEliminar(Boolean permiteEliminar) {
        this.permiteEliminar = permiteEliminar;
    }

    public List<IconoX> getIconos() {
        return iconos;
    }

    public void setIconos(List<IconoX> iconos) {
        this.iconos = iconos;
    }

    public IconoX getIconoSeleccionado() {
        return iconoSeleccionado;
    }

    public void setIconoSeleccionado(IconoX iconoSeleccionado) {
        this.iconoSeleccionado = iconoSeleccionado;
    }
    
    
}
