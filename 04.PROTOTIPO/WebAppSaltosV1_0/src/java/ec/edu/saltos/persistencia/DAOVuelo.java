/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.Vuelo;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOVuelo extends DAOGenerico<Vuelo, Integer>{

    public DAOVuelo() {
        super(Vuelo.class);
    }
    
    public List<Vuelo> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Vuelo _vuelo){
        return super.save(_vuelo);
    }
    
    public Vuelo obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Vuelo _vuelo) {
        return super.update(_vuelo);
    }
    
    public Boolean eliminar(Vuelo _vuelo){
        return super.delete(_vuelo);
    }
}
