/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.ConfigClave;
import java.util.List;

/**
 *
 * @author kalex
 */
public class DAOConfigClave extends DAOGenerico<ConfigClave, Integer>{

    public DAOConfigClave() {
        super(ConfigClave.class);
    }
    
    public List<ConfigClave> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(ConfigClave _configClave){
        return super.save(_configClave);
    }
    
    public ConfigClave obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(ConfigClave _configClave) {
        return super.update(_configClave);
    }
    
    public Boolean eliminar(ConfigClave _configClave){
        return super.delete(_configClave);
    }
}
