/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.config.HibernateUtil;
import ec.edu.saltos.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOUsuario extends DAOGenerico<Usuario, Integer>{
    public DAOUsuario() {
        super(Usuario.class);
    }
    
    public List<Usuario> obtenerUsuarios() {
        return super.findAll();
    }
    
    public Boolean guardar(Usuario usuario){
        return super.save(usuario);
    }
    
    public Usuario obtenerPorId(Integer _idUsuario) {
        return super.find(_idUsuario);
    }

    public Boolean editar(Usuario _usuario) {
        return super.update(_usuario);
    }
    
    public Boolean eliminar(Usuario _usuario){
        return super.delete(_usuario);
    }
}
