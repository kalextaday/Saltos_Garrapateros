/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.Paracaidista;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOParacaidista extends DAOGenerico<Paracaidista, Integer>{

    public DAOParacaidista() {
        super(Paracaidista.class);
    }
    
    public List<Paracaidista> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Paracaidista _paracaidista){
        return super.save(_paracaidista);
    }
    
    public Paracaidista obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Paracaidista _paracaidista) {
        return super.update(_paracaidista);
    }
    
    public Boolean eliminar(Paracaidista _paracaidista){
        return super.delete(_paracaidista);
    }
}
