/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;

import ec.edu.saltos.modelo.FormacionParacaidista;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOFormacionParacaidista extends DAOGenerico<FormacionParacaidista, Integer>{
    public DAOFormacionParacaidista() {
        super(FormacionParacaidista.class);
    }
    
    public List<FormacionParacaidista> obtenerFormacionParacaidistas() {
        return super.findAll();
    }
    
    public Boolean guardar(FormacionParacaidista formacionParacaidista){
        return super.save(formacionParacaidista);
    }
    
    public FormacionParacaidista obtenerPorId(Integer _idFormacion) {
        return super.find(_idFormacion);
    }

    public Boolean editar(FormacionParacaidista _formacionParacaidista) {
        return super.update(_formacionParacaidista);
    }
    
    public Boolean eliminar(FormacionParacaidista _formacionParacaidista){
        return super.delete(_formacionParacaidista);
    }
}
