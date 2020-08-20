/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.Bitacora;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOBitacora extends DAOGenerico<Bitacora, Integer>{

    public DAOBitacora() {
        super(Bitacora.class);
    }
    
    public List<Bitacora> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Bitacora _bitacora){
        return super.save(_bitacora);
    }
    
    public Bitacora obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Bitacora _bitacora) {
        return super.update(_bitacora);
    }
    
    public Boolean eliminar(Bitacora _bitacora){
        return super.delete(_bitacora);
    }
}
