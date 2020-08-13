/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.modelo.Empleado;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOEmpleado extends DAOGenerico<Empleado, Integer>{
    public DAOEmpleado() {
        super(Empleado.class);
    }
    
    public List<Empleado> obtenerEmpleados() {
        return super.findAll();
    }
    
    public Boolean guardar(Empleado _empleado){
        return super.save(_empleado);
    }
    
    public Empleado obtenerPorId(Integer _idPerfil) {
        return super.find(_idPerfil);
    }

    public Boolean editar(Empleado _empleado) {
        return super.update(_empleado);
    }
    
    public Boolean eliminar(Empleado _empleado){
        return super.delete(_empleado);
    }
}
