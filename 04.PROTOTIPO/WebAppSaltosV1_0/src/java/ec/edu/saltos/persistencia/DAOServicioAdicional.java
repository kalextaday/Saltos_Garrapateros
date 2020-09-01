/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.ServicioAdicional;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOServicioAdicional extends DAOGenerico<ServicioAdicional, Integer>{

    public DAOServicioAdicional() {
        super(ServicioAdicional.class);
    }
    
    public List<ServicioAdicional> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(ServicioAdicional _servicioAdicional){
        return super.save(_servicioAdicional);
    }
    
    public ServicioAdicional obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(ServicioAdicional _servicioAdicional) {
        return super.update(_servicioAdicional);
    }
    
    public Boolean eliminar(ServicioAdicional _servicioAdicional){
        return super.delete(_servicioAdicional);
    }
}
