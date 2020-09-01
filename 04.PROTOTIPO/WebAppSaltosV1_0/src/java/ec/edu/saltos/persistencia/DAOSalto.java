/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.Salto;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOSalto extends DAOGenerico<Salto, Integer>{

    public DAOSalto() {
        super(Salto.class);
    }
    
    public List<Salto> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Salto _salto){
        return super.save(_salto);
    }
    
    public Salto obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Salto _salto) {
        return super.update(_salto);
    }
    
    public Boolean eliminar(Salto _salto){
        return super.delete(_salto);
    }
}
