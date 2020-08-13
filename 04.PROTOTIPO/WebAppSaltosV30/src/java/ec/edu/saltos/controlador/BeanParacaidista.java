/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOParacaidista;
import ec.edu.saltos.modelo.Paracaidista;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanParacaidista implements Serializable{

    private List<Paracaidista> listaParacaidistas;
    private Paracaidista paracaidista;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanParacaidista() {
        paracaidista=new Paracaidista();
    }
    
    @PostConstruct
    public void init(){
        obtenerParacaidistas();
    }
    
    public void obtenerParacaidistas(){
        DAOParacaidista daoparacaidista=new DAOParacaidista();
        listaParacaidistas=daoparacaidista.obtenerParacaidistas();
    }
    
    public void agregarParacaidista() {
        DAOParacaidista daoparacaidista=new DAOParacaidista();
        try{
            if(daoparacaidista.guardar(paracaidista)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarParacaidista() {
        DAOParacaidista daoparacaidista=new DAOParacaidista();
        try{
            if(daoparacaidista.editar(paracaidista)){
                //limpiarParacaidista();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarParacaidista() {
        DAOParacaidista daoparacaidista=new DAOParacaidista();
        try{
            if(daoparacaidista.eliminar(paracaidista)){
                //limpiarParacaidista();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarParacaidista() {
        paracaidista = new Paracaidista();
    }

    public List<Paracaidista> getListaParacaidistas() {
        obtenerParacaidistas();
        return listaParacaidistas;
    }

    public void setListaParacaidistas(List<Paracaidista> listaParacaidistas) {
        this.listaParacaidistas = listaParacaidistas;
    }

    public Paracaidista getParacaidista() {
        return paracaidista;
    }

    public void setParacaidista(Paracaidista paracaidista) {
        this.paracaidista = paracaidista;
    }
    
    
}
