/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Cliente;
import ec.edu.saltos.modelo.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOCliente extends DAOGenerico<Cliente, Integer>{
    public DAOCliente() {
        super(Cliente.class);
    }
    
    public List<Cliente> obtenerClientes() {
        return super.findAll();
    }
    
    public Boolean guardar(Cliente _cliente){
        return super.save(_cliente);
    }
    
    public Cliente obtenerPorId(Integer _idCliente) {
        return super.find(_idCliente);
    }

    public Boolean editar(Cliente _cliente) {
        return super.update(_cliente);
    }
    
    public Boolean eliminar(Cliente _cliente){
        return super.delete(_cliente);
    }
}
