/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.modelo.Recurso;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAORecurso extends DAOGenerico<Recurso, Integer>{
    public DAORecurso() {
        super(Recurso.class);
    }
    
    public List<Recurso> obtenerRecursos() {
        return super.findAll();
    }
    
    public Boolean guardar(Recurso recurso){
        return super.save(recurso);
    }
    
    public Recurso obtenerPorId(Integer _idRecurso) {
        return super.find(_idRecurso);
    }

    public Boolean editar(Recurso _recurso) {
        return super.update(_recurso);
    }
    
    public Boolean eliminar(Recurso _recurso){
        return super.delete(_recurso);
    }
}
