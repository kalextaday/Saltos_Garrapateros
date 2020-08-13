/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.ServicioAdicional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOServicioAdicional extends DAOGenerico<ServicioAdicional, Integer>{
    public DAOServicioAdicional() {
        super(ServicioAdicional.class);
    }
    
    public List<ServicioAdicional> obtenerServicioAdicionals() {
        return super.findAll();
    }
    
    public Boolean guardar(ServicioAdicional vuelo){
        return super.save(vuelo);
    }
    
    public ServicioAdicional obtenerPorId(Integer _idServicioAdicional) {
        return super.find(_idServicioAdicional);
    }

    public Boolean editar(ServicioAdicional _vuelo) {
        return super.update(_vuelo);
    }
    
    public Boolean eliminar(ServicioAdicional _vuelo){
        return super.delete(_vuelo);
    }
}
