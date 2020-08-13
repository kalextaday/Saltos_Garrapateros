/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;

import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Perfil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOPerfil extends DAOGenerico<Perfil, Integer>{
    public DAOPerfil() {
        super(Perfil.class);
    }
    
    public List<Perfil> obtenerPerfils() {
        return super.findAll();
    }
    
    public Boolean guardar(Perfil perfil){
        return super.save(perfil);
    }
    
    public Perfil obtenerPorId(Integer _idPerfil) {
        return super.find(_idPerfil);
    }

    public Boolean editar(Perfil _perfil) {
        return super.update(_perfil);
    }
    
    public Boolean eliminar(Perfil _perfil){
        return super.delete(_perfil);
    }
}
