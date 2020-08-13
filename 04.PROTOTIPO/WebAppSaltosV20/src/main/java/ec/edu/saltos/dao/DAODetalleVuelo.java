/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;



import ec.edu.saltos.modelo.DetalleVuelo;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAODetalleVuelo extends DAOGenerico<DetalleVuelo, Integer>{
    public DAODetalleVuelo() {
        super(DetalleVuelo.class);
    }
    
    public List<DetalleVuelo> obtenerDetalleVuelos() {
        return super.findAll();
    }
    
    public Boolean guardar(DetalleVuelo detalleVuelo){
        return super.save(detalleVuelo);
    }
    
    public DetalleVuelo obtenerPorId(Integer _idDetalle) {
        return super.find(_idDetalle);
    }

    public Boolean editar(DetalleVuelo _detalleVuelo) {
        return super.update(_detalleVuelo);
    }
    
    public Boolean eliminar(DetalleVuelo _detalleVuelo){
        return super.delete(_detalleVuelo);
    }
}
