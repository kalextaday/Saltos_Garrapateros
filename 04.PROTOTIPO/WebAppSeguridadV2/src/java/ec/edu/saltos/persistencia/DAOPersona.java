/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.Persona;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOPersona extends DAOGenerico<Persona, Integer>{

    public DAOPersona() {
        super(Persona.class);
    }
    
    public List<Persona> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Persona _persona){
        return super.save(_persona);
    }
    
    public Persona obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Persona _persona) {
        return super.update(_persona);
    }
    
    public Boolean eliminar(Persona _persona){
        return super.delete(_persona);
    }
}
