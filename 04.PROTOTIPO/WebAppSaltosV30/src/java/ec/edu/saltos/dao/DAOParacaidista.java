/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;

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
    
    public List<Paracaidista> obtenerParacaidistas() {
        return super.findAll();
    }
    
    public Boolean guardar(Paracaidista paracaidista){
        return super.save(paracaidista);
    }
    
    public Paracaidista obtenerPorId(Integer _idParacaidista) {
        return super.find(_idParacaidista);
    }

    public Boolean editar(Paracaidista _paracaidista) {
        return super.update(_paracaidista);
    }
    
    public Boolean eliminar(Paracaidista _paracaidista){
        return super.delete(_paracaidista);
    }
}
