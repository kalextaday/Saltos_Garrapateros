/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.FormaPago;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOFormaPago extends DAOGenerico<FormaPago, Integer>{

    public DAOFormaPago() {
        super(FormaPago.class);
    }
    
    public List<FormaPago> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(FormaPago _formaPago){
        return super.save(_formaPago);
    }
    
    public FormaPago obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(FormaPago _formaPago) {
        return super.update(_formaPago);
    }
    
    public Boolean eliminar(FormaPago _formaPago){
        return super.delete(_formaPago);
    }
}
