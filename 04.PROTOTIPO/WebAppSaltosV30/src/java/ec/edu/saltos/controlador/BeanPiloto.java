/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador;


import ec.edu.saltos.dao.DAOPiloto;
import ec.edu.saltos.modelo.Piloto;
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
public class BeanPiloto implements Serializable{

    private List<Piloto> listaPilotos;
    private Piloto piloto;
    /**
     * Creates a new instance of BeanEmpleado
     */
    public BeanPiloto() {
        piloto=new Piloto();
    }
    
    @PostConstruct
    public void init(){
        obtenerPilotos();
    }
    
    public void obtenerPilotos(){
        DAOPiloto daopiloto=new DAOPiloto();
        listaPilotos=daopiloto.obtenerPilotos();
    }
    
    public void agregarPiloto() {
        DAOPiloto daopiloto=new DAOPiloto();
        try{
            if(daopiloto.guardar(piloto)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se registro correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se registro correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void modificarPiloto() {
        DAOPiloto daopiloto=new DAOPiloto();
        try{
            if(daopiloto.editar(piloto)){
                limpiarPiloto();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se modifico correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se modifico correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
    }

    public void eliminarPiloto() {
        DAOPiloto daopiloto=new DAOPiloto();
        try{
            if(daopiloto.eliminar(piloto)){
                limpiarPiloto();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info: ","Se elimino correctamente"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: ","No se elimino correctamente"));
            }
        }catch(Exception e){
            System.out.println("Excepcion al agregar: "+e);
        }
        
    }
    
    public void limpiarPiloto() {
        piloto = new Piloto();
    }

    public List<Piloto> getListaPilotos() {
        obtenerPilotos();
        return listaPilotos;
    }

    public void setListaPilotos(List<Piloto> listaPilotos) {
        this.listaPilotos = listaPilotos;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }
    
    
}
