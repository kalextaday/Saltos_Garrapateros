/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.app;


import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.modelo.Opcion;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.persistencia.DAOOpcion;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.util.FechaUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
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
public class BeanOpcion implements Serializable{

    private TreeNode opcionPadre;
    private TreeNode opcionSeleccionado;
    private Opcion unaOpcionSeleccionada;
    private Opcion opcionSeleccionCrud;
    private Boolean permiteUrl;
    private Boolean permiteEliminar;
    
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanOpcion() {
    }
    
    @PostConstruct
    public void init(){
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
            //LOG.log(Level.SEVERE, null, ex);
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
            //LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public void preparaCrear(){
        
    }
    /*
    public void agregarOpcion() {
        DAOPersona daopersona=new DAOPersona();

        try{
            if(daopersona.guardar(persona)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
                limpiarPersona();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }finally{
            
        }
    }

    public void modificarPersona() {
        DAOPersona daopersona=new DAOPersona();
        try{
            if(daopersona.editar(persona)){
                limpiarPersona();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarPersona() {
        DAOPersona daopersona=new DAOPersona();
        try{
            if(daopersona.eliminar(persona)){
                limpiarPersona();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarPersona() {
        persona = new Persona();
    }
    */

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
        this.opcionSeleccionado = opcionSeleccionado;
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
    
}
