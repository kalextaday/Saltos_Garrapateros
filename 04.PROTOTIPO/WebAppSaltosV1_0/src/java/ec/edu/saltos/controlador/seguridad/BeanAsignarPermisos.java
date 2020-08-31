package ec.edu.saltos.controlador.seguridad;

import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.OpcionPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOSaveGenerico;
import ec.edu.saltos.util.PrimeUtiles;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class BeanAsignarPermisos extends FiltroAcceso implements Serializable {
    private static final Logger LOG = Logger.getLogger(BeanAsignarPermisos.class.getName());
    
    private List<Perfil> perfiles;
    private List<Integer> idsRx;
    private List<String> nombreRx;

    private Perfil perfilSeleccionado;

    private TreeNode permisoPadre;
    private TreeNode[] permisoSeleccionado;

    private List<Perfil> perfilesMasAdmin;
    private List<Perfil> perfilesAdmin;
    private List<Perfil> perfilesReport;
    private TreeNode permisoPerfil;
    private List<Integer> idsPermisosPerfil;

    private Boolean permiteAsignar;

    

    public BeanAsignarPermisos() {
        super(FacesContext.getCurrentInstance().getExternalContext());
        permiteAsignar = false;
    }

    @PostConstruct
    public void init() {
        obtenerMisPerfiles();
        permisoPadre=new DefaultTreeNode();
    }

    public void initArbolPermisos() {
        permisoPadre=null;
        if (permisoPadre == null) {
            crearArbolMain();
        }
    }

    public void obtenerMisPerfiles() {
        
        perfiles = new DAOPerfil().obtenerTodos();
        //perfilSeleccionado = perfiles.get(0);
        
        perfilesMasAdmin = perfiles.stream().filter(p -> p.getPerfilEstatus2().equals(EstadosConfig.PERFIL_SUPERADMIN.getCodigo())).collect(Collectors.toList());
        perfilesAdmin = perfiles.stream().filter(p -> p.getPerfilEstatus2().equals(EstadosConfig.PERFIL_ADMINISTRADOR.getCodigo())).collect(Collectors.toList());
        perfilesReport=perfiles.stream().filter(p -> p.getPerfilEstatus2().equals(EstadosConfig.PERFIL_USUARIO.getCodigo())).collect(Collectors.toList());
    }
    
    public void actualizarPermisos() {
        permisoPadre = null;
        initArbolPermisos();

    }
    
    public void actualizarPermisosPerfil(SelectEvent event) {
        perfilSeleccionado=(Perfil)event.getObject();
        permisoPerfil = null;
        idsPermisosPerfil = new ArrayList<>();
        crearArbolPerfil();
    }
    
    public void crearArbolPerfil() {
        Opcion raiz = obtenerRaiz();
        permisoPerfil = new DefaultTreeNode();
        TreeNode real = new DefaultTreeNode(raiz, permisoPerfil);
        real.setExpanded(true);
        crearArbolSec(real);
        actualizarPermisos();
    }

    public void crearArbolMain() {
        Opcion raiz = obtenerRaiz();
        permisoPadre = new DefaultTreeNode();
        TreeNode categoria = new DefaultTreeNode(raiz, permisoPadre);
        categoria.setExpanded(true);
        crearArbolSudo(categoria);

    }

    public void crearArbolSudo(TreeNode nodo) {
        try {
            DAOOpcion dao = new DAOOpcion();
            List<Opcion> recursos = dao.obtenerOpcionesSudo(((Opcion) nodo.getData()).getIdOpcion());
            TreeNode nodeNuevo;
            for (Opcion recurso : recursos) {
                nodeNuevo = new DefaultTreeNode(recurso, nodo);
                for(int i=0;i<idsPermisosPerfil.size();i++){
                    if(recurso.getIdOpcion()==idsPermisosPerfil.get(i)){
                        nodeNuevo.setSelected(true);
                    }
                }
                crearArbolSudo(nodeNuevo);
            }
            nodo.setExpanded(true);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearArbolSec(TreeNode pantalla) {
        try {
            DAOOpcion dao = new DAOOpcion();
            
            List<Opcion> recursos =listarOpcionesPermitidos(((Opcion) pantalla.getData()).getIdOpcion(), perfilSeleccionado.getIdPerfil());
            
            TreeNode pantallaNueva;
            for (Opcion recurso : recursos) {
                pantallaNueva = new DefaultTreeNode(recurso, pantalla);
                idsPermisosPerfil.add(recurso.getIdOpcion());
                crearArbolSec(pantallaNueva);
            }
            pantalla.setExpanded(true);
            
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Opcion> listarOpcionesPermitidos(Integer _idRecursoPadre,Integer _idPerfil){
        List<Opcion> listaOpciones=new ArrayList<>();
        List<OpcionPerfil> listaOpcionPerfil;
        
        DAOOpcion daoOpcion=new DAOOpcion();
        DAOOpcionPerfil daoOpcionesPerfil=new DAOOpcionPerfil();
        Opcion opcP=new Opcion(); 
        
        listaOpcionPerfil=daoOpcionesPerfil.opcionesPorPerfil(_idPerfil);
        
        opcP=daoOpcion.obtenerPorId(_idRecursoPadre);
        for(OpcionPerfil op1:listaOpcionPerfil){
            if(op1.getOpcion().getIdOpcion()==opcP.getIdOpcion()){
                listaOpcionPerfil.remove(opcP);
            }
        }

        for(OpcionPerfil op:listaOpcionPerfil){
            Opcion opcion=daoOpcion.obtenerOpcionesSudo(op.getOpcion().getIdOpcion(),_idRecursoPadre);
            if(opcion!=null){
                listaOpciones.add(opcion);
            }
        }
        
        return listaOpciones;
    }

    public Opcion obtenerRaiz() {
        Opcion tmp = new Opcion();
        try {

            DAOOpcion daorp = new DAOOpcion();
            Opcion list = daorp.obtenerOrigen();
            if (list != null) {
                tmp = list;
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    public void cerrarRecursosAsignados() {
        PrimeUtiles.primeExecute("PF('wv-permisosAsignados').hide();");
        PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: ","Se han asignado los permisos correctamente");
    }

    public TreeNode[] preparaAsignarRecursos() {
        
        PrimeUtiles.primeExecute("PF('wv-asignarRecursos').show();");
        return permisoSeleccionado;
    }

    public void obtenerRecursos() {

        Stream<TreeNode> permisosx = Arrays.stream(permisoSeleccionado);
        idsRx = new ArrayList<>();
        nombreRx = new ArrayList<>();
        permisosx.forEach(px -> {
            if (px.isLeaf()) {
                Opcion r = ((Opcion) px.getData());
                idsRx.add(r.getIdOpcion());
                nombreRx.add(r.getOpcNombre());
                obtenerPadrex(px);

            }
        });

        List<Integer> listaPermisos = idsRx.stream().distinct().collect(Collectors.toList());
        List<Opcion> listaRecursos = listaPermisos.stream()
                .map(lr -> new DAOOpcion().obtenerPorId(lr))
                .collect(Collectors.toList());

        DAOOpcionPerfil daop = new DAOOpcionPerfil();
        List<OpcionPerfil> xpermisos = new ArrayList<>();
        listaRecursos.forEach(lp -> {
            xpermisos.add(new OpcionPerfil(lp, perfilSeleccionado,  EstadosConfig.OPCION_PER_ACTIVADA.getCodigo()));
        });

        try {
            if (daop.eliminarNOpcionesPerfil(perfilSeleccionado.getIdPerfil())) {
                LOG.info("Eliminación de Permisos antiguos correctamente.");
                List<Object> listaObjt = new ArrayList<>();
                listaObjt.addAll(xpermisos);
                
                if (DAOSaveGenerico.guardarLotes(listaObjt)) {
                    LOG.info("Permisos nuevos asignados correctamente.");
                    PrimeUtiles.primeUpdate("pd-permisos-asignados");
                    PrimeUtiles.primeExecute("PF('wv-permisosAsignados').show();");
                    PrimeUtiles.primeExecute("PF('wv-asignarRecursos').hide();");

                } else {
                    LOG.info("No se han podido agregar permisos.");
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","No se ha asignado los permisos.");
                }
            } else {
                LOG.info("No se han eliminado permisos.");
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Fatal: ","Revisa tu conexion a internet.");
            }

        } catch (Exception e) {
            System.out.println("err:" + e.getMessage());
        }

    }

    public void preparaRetirarRecursosPerfil() {
        LOG.info("Preparando Eliminar TODOS los Permisos ");
        PrimeUtiles.primeExecute("PF('wv-retirarPermisos').show();");

    }

    public void retirarRecursosPerfil() {
        try {
            
            if (new DAOOpcionPerfil().eliminarPermisos(perfilSeleccionado.getIdPerfil())) {
                LOG.log(Level.INFO, "Se eliminaron todos los recursos del perfil: {0} ", new Object[]{perfilSeleccionado.getPerfilNombre()});
                PrimeUtiles.primeExecute("PF('wv-retirarPermisos').hide();");
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: ","Se han retirado todos los permisos correctamente.");
            } else {
                System.out.println("No se han podido eliminar permisos");
                LOG.log(Level.SEVERE, null, "No se han podido eliminar permisos");
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Info: ","No se ha retirado los permisos correctamente.");
            }
            
        } catch (Exception e) {
            System.out.println("err:" + e.getMessage());
            LOG.log(Level.SEVERE, null, e.getMessage());
        }
    }

    public void obtenerPadrex(TreeNode _rx) {
        try {
            Opcion miRx;
            if (_rx.getParent() != null) {
                miRx = (Opcion) _rx.getParent().getData();
                idsRx.add(miRx.getIdOpcion());
                
                obtenerPadrex(_rx.getParent());
            }
        } catch (Exception e) {
            
        }

    }

    public TreeNode[] seleccionRecurso(NodeSelectEvent event) {

        Stream<TreeNode> permisosx = Arrays.stream(permisoSeleccionado);

        short[] cont = {0};
        List<Opcion> recursos = new ArrayList<>();
        permisosx.forEach(p -> {
            cont[0]++;
            Opcion rx = (Opcion) p.getData();
            List<TreeNode> rxHijos = p.getChildren();
            recursos.add(rx);
            
        });

        permiteAsignar = !recursos.isEmpty();

        return permisoSeleccionado;

    }

    public TreeNode[] deselecccionRecurso(NodeUnselectEvent event) {
        Stream<TreeNode> permisosx = Arrays.stream(permisoSeleccionado);

        short[] cont = {0};
        List<Opcion> recursos = new ArrayList<>();
        permisosx.forEach(p -> {
            cont[0]++;
            Opcion rx = (Opcion) p.getData();
            List<TreeNode> rxHijos = p.getChildren();
            recursos.add(rx);

        });

        permiteAsignar = !recursos.isEmpty();

        return permisoSeleccionado;
    }

    public TreeNode getPermisoPadre() {
        return permisoPadre;
    }

    public void setPermisoPadre(TreeNode permisoPadre) {
        this.permisoPadre = permisoPadre;
    }

    public TreeNode[] getPermisoSeleccionado() {
        return permisoSeleccionado;
    }

    public void setPermisoSeleccionado(TreeNode[] permisoSeleccionado) {
        this.permisoSeleccionado = permisoSeleccionado;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public List<Integer> getIdsRx() {
        return idsRx;
    }

    public void setIdsRx(List<Integer> idsRx) {
        this.idsRx = idsRx;
    }

    public Boolean getPermiteAsignar() {
        return permiteAsignar;
    }

    public void setPermiteAsignar(Boolean permiteAsignar) {
        this.permiteAsignar = permiteAsignar;
    }

    public List<String> getNombreRx() {
        return nombreRx;
    }

    public void setNombreRx(List<String> nombreRx) {
        this.nombreRx = nombreRx;
    }

    public TreeNode getPermisoPerfil() {
        return permisoPerfil;
    }

    public void setPermisoPerfil(TreeNode permisoPerfil) {
        this.permisoPerfil = permisoPerfil;
    }

    public List<Integer> getIdsPermisosPerfil() {
        return idsPermisosPerfil;
    }

    public void setIdsPermisosPerfil(List<Integer> idsPermisosPerfil) {
        this.idsPermisosPerfil = idsPermisosPerfil;
    }

    public List<Perfil> getPerfilesAdmin() {
        return perfilesAdmin;
    }

    public void setPerfilesAdmin(List<Perfil> perfilesAdmin) {
        this.perfilesAdmin = perfilesAdmin;
    }

    public List<Perfil> getPerfilesMasAdmin() {
        return perfilesMasAdmin;
    }

    public void setPerfilesMasAdmin(List<Perfil> perfilesMasAdmin) {
        this.perfilesMasAdmin = perfilesMasAdmin;
    }

    public List<Perfil> getPerfilesReport() {
        return perfilesReport;
    }

    public void setPerfilesReport(List<Perfil> perfilesReport) {
        this.perfilesReport = perfilesReport;
    }

}
