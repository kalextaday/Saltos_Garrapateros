package ec.edu.saltos.controlador.seguridad;

import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.OpcionPerfil;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOOpcionPerfil;
import ec.edu.saltos.persistencia.DAOPerfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class BeanPermisosVisor extends FiltroAcceso implements Serializable {

    private static final Logger LOG = Logger.getLogger(BeanPermisosVisor.class.getName());
    
    private ControlSesion cs;
    private List<Perfil> perfiles;

    private Perfil perfilSeleccionado;

    private TreeNode permisoPadre;


    
    public BeanPermisosVisor() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }

    @PostConstruct
    public void init() {
        cs= new ControlSesion();
        permisoPadre = new DefaultTreeNode();
        
        obtenerMisPerfiles();
    }

    public void actualizarPermisos() {
        permisoPadre = null;
        initArbolPermisos();
    }

    public void initArbolPermisos() {
        if (permisoPadre == null) {
            crearArbolMain();
        }
    }

    public void obtenerMisPerfiles() {
        perfiles = new DAOPerfil().obtenerTodos();
        perfilSeleccionado=new Perfil();
    }

    public void crearArbolMain() {
        Opcion raiz = obtenerOpcionRaiz();
        permisoPadre = new DefaultTreeNode();
        TreeNode real = new DefaultTreeNode(raiz, permisoPadre);
        real.setExpanded(true);
        crearArbol(real);
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

    public void crearArbol(TreeNode pantalla) {
        try {
            DAOOpcion dao = new DAOOpcion();

            List<Opcion> recursos =listarOpcionesPermitidos(((Opcion) pantalla.getData()).getIdOpcion(), perfilSeleccionado.getIdPerfil());
            
            TreeNode pantallaNueva;
            for (Opcion recurso : recursos) {
                pantallaNueva = new DefaultTreeNode(recurso, pantalla);
                crearArbol(pantallaNueva);
            }
            pantalla.setExpanded(true);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public Opcion obtenerOpcionRaiz() {
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

    public TreeNode getPermisoPadre() {
        return permisoPadre;
    }

    public void setPermisoPadre(TreeNode permisoPadre) {
        this.permisoPadre = permisoPadre;
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

}
