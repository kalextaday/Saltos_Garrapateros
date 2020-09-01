/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.persistencia;

import ec.edu.saltos.modelo.Empresa;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOEmpresa extends DAOGenerico<Empresa, Integer>{

    public DAOEmpresa() {
        super(Empresa.class);
    }
    
    public List<Empresa> obtenerTodos() {
        return super.findAll();
    }
    
    public Boolean guardar(Empresa _empresa){
        return super.save(_empresa);
    }
    
    public Empresa obtenerPorId(Integer _id) {
        return super.find(_id);
    }

    public Boolean editar(Empresa _empresa) {
        return super.update(_empresa);
    }
    
    public Boolean eliminar(Empresa _empresa){
        return super.delete(_empresa);
    }
}
