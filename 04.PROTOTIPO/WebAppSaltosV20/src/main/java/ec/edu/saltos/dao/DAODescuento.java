/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.modelo.Descuento;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAODescuento extends DAOGenerico<Descuento, Integer>{
    public DAODescuento() {
        super(Descuento.class);
    }
    
    public List<Descuento> obtenerDescuentos() {
        return super.findAll();
    }
    
    public Boolean guardar(Descuento _descuento){
        return super.save(_descuento);
    }
    
    public Descuento obtenerPorId(Integer _idDescuento) {
        return super.find(_idDescuento);
    }

    public Boolean editar(Descuento _descuento) {
        return super.update(_descuento);
    }
    
    public Boolean eliminar(Descuento _descuento){
        return super.delete(_descuento);
    }
}
