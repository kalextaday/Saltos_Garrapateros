/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Salto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOSalto extends DAOGenerico<Salto, Integer>{
    public DAOSalto() {
        super(Salto.class);
    }
    
    public List<Salto> obtenerSaltos() {
        return super.findAll();
    }
    
    public Boolean guardar(Salto salto){
        return super.save(salto);
    }
    
    public Salto obtenerPorId(Integer _idSalto) {
        return super.find(_idSalto);
    }

    public Boolean editar(Salto _salto) {
        return super.update(_salto);
    }
    
    public Boolean eliminar(Salto _salto){
        return super.delete(_salto);
    }
}
