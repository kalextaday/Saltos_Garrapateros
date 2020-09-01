/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.controlador.app;


import ec.edu.saltos.controlador.seguridad.*;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.FormaPago;
import ec.edu.saltos.modelo.Persona;
import ec.edu.saltos.persistencia.DAOFormaPago;
import ec.edu.saltos.persistencia.DAOPersona;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import ec.edu.saltos.validaciones.Cedula;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author kalex
 */
@ManagedBean
@ViewScoped
public class BeanFormaPago extends FiltroAcceso implements Serializable{

    private static final Logger LOG = Logger.getLogger(BeanFormaPago.class.getName());
    
    private List<FormaPago> listaFormasPago; 

    private FormaPago formPagoSeleccionada;
    
    private String formatoFecha;
    
    /**
     * Creates a new instance of BeanPersona
     */
    public BeanFormaPago() {
        super(FacesContext.getCurrentInstance().getExternalContext());
    }
    
    @PostConstruct
    public void init(){
        formatoFecha="si";
        listaFormasPago=new ArrayList<>();
        obtenerFormasPago();
    }
    
    public void obtenerFormasPago(){
        DAOFormaPago dao=new DAOFormaPago();
        try{
            listaFormasPago=dao.obtenerTodos();
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al obtener a todos: {0}",e);
        }
    }
    
    public void agregarFormaPago() {
        DAOFormaPago dao=new DAOFormaPago();

        try{
            if(dao.guardar(formPagoSeleccionada)){
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
                limpiarFormaPago();
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al registrar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al agregar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }

    public void modificarFormaPago() {
        DAOFormaPago dao=new DAOFormaPago();
        
        try{
            if(dao.editar(formPagoSeleccionada)){
                limpiarFormaPago();
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: ","Se actualizo correctamente");
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al actualizar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al modificar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-actualizar').hide();");
        }
    }

    public void eliminarFormaPago() {
        DAOFormaPago dao=new DAOFormaPago();
        
        try{
            if(dao.eliminar(formPagoSeleccionada)){
                limpiarFormaPago();
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info: ", "Se elimino correctamente");
                
                LOG.log(Level.INFO, "Persona Eliminada Correctamente");
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "Hubo un error al eliminar");
                
                LOG.log(Level.INFO, "No se pudo eliminar la persona");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al eliminar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-eliminar').hide();");
        }
        
    }

    public FormaPago onRowSelect(SelectEvent event) {
        setFormPagoSeleccionada((FormaPago) event.getObject());
        LOG.log(Level.INFO, "Persona {0} listo para usar a: ", formPagoSeleccionada.getPagoNombre());
        return formPagoSeleccionada;
    }

    public void onRowUnselect(UnselectEvent event) {
    }

    public FormaPago preparaCrear() {
        LOG.log(Level.INFO, "Preparando nueva persona");

        formPagoSeleccionada = new FormaPago();
        PrimeUtiles.primeExecute("PF('wv-crear').show();");
        return formPagoSeleccionada;

    }

    public FormaPago preparaActualizar() {
        LOG.log(Level.INFO, "Persona {0} lista para actualizar.", formPagoSeleccionada.getPagoNombre());
        
        PrimeUtiles.primeExecute("PF('wv-actualizar').show();");
        return formPagoSeleccionada;
    }

    public void preparaArchivar() {
        LOG.log(Level.INFO, "Persona {0} lista para archivar.", formPagoSeleccionada.getPagoNombre());
        
        PrimeUtiles.primeExecute("PF('wv-archivar').show();");
    }
    
    public void preparaEliminar() {

        LOG.log(Level.INFO, "Persona {0} lista para eliminar.", formPagoSeleccionada.getPagoNombre());
        PrimeUtiles.primeExecute("PF('wv-eliminar').show();");
    }
    
    public void limpiarFormaPago() {
        formPagoSeleccionada = new FormaPago();
    }

    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public List<FormaPago> getListaFormasPago() {
        obtenerFormasPago();
        return listaFormasPago;
    }

    public void setListaFormasPago(List<FormaPago> listaFormasPago) {
        this.listaFormasPago = listaFormasPago;
    }

    public FormaPago getFormPagoSeleccionada() {
        return formPagoSeleccionada;
    }

    public void setFormPagoSeleccionada(FormaPago formPagoSeleccionada) {
        this.formPagoSeleccionada = formPagoSeleccionada;
    }
    
}
