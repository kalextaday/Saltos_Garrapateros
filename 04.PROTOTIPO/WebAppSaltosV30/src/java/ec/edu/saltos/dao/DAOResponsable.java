/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Responsable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOResponsable extends DAOGenerico<Responsable, Integer> {
    public DAOResponsable() {
        super(Responsable.class);
    }
    
    public List<Responsable> obtenerResponsables() {
        return super.findAll();
    }
    
    public Boolean guardar(Responsable responsable){
        return super.save(responsable);
    }
    
    public Responsable obtenerPorId(Integer _idResponsable) {
        return super.find(_idResponsable);
    }

    public Boolean editar(Responsable _responsable) {
        return super.update(_responsable);
    }
    
    public Boolean eliminar(Responsable _responsable){
        return super.delete(_responsable);
    }
}
