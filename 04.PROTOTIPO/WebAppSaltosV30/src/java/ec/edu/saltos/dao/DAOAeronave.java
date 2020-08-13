/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Aeronave;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOAeronave extends DAOGenerico<Aeronave, Integer>{

    public DAOAeronave() {
        super(Aeronave.class);
    }
    
    public List<Aeronave> obtenerAeronaves() {
        return super.findAll();
    }
    
    public Boolean guardar(Aeronave _aeronave){
        return super.save(_aeronave);
    }
    
    public Aeronave obtenerPorId(Integer _idAeronave) {
        return super.find(_idAeronave);
    }

    public Boolean editar(Aeronave _aeronave) {
        return super.update(_aeronave);
    }
    
    public Boolean eliminar(Aeronave _aeronave){
        return super.delete(_aeronave);
    }
}
