/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Vuelo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOVuelo extends DAOGenerico<Vuelo, Integer>{
    public DAOVuelo() {
        super(Vuelo.class);
    }
    
    public List<Vuelo> obtenerVuelos() {
        return super.findAll();
    }
    
    public Boolean guardar(Vuelo vuelo){
        return super.save(vuelo);
    }
    
    public Vuelo obtenerPorId(Integer _idVuelo) {
        return super.find(_idVuelo);
    }

    public Boolean editar(Vuelo _vuelo) {
        return super.update(_vuelo);
    }
    
    public Boolean eliminar(Vuelo _vuelo){
        return super.delete(_vuelo);
    }
}
