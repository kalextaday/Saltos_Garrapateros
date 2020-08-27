/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.Perfil;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOPerfil extends DAOGenerico<Perfil, Integer>{

    public DAOPerfil() {
        super(Perfil.class);
    }
    
    public List<Perfil> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Perfil _perfil){
        return super.save(_perfil);
    }
    
    public Perfil obtenerPorId(Integer _idAeronave) {
        return super.find(_idAeronave);
    }

    public Boolean editar(Perfil _perfil) {
        return super.update(_perfil);
    }
    
    public Boolean eliminar(Perfil _perfil){
        return super.delete(_perfil);
    }
}
