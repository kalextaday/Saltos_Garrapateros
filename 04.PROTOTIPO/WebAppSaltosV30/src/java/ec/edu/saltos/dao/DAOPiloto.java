/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.modelo.Piloto;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOPiloto extends DAOGenerico<Piloto, Integer>{
    public DAOPiloto() {
        super(Piloto.class);
    }
    
    public List<Piloto> obtenerPilotos() {
        return super.findAll();
    }
    
    public Boolean guardar(Piloto piloto){
        return super.save(piloto);
    }
    
    public Piloto obtenerPorId(Integer _idPiloto) {
        return super.find(_idPiloto);
    }

    public Boolean editar(Piloto _piloto) {
        return super.update(_piloto);
    }
    
    public Boolean eliminar(Piloto _piloto){
        return super.delete(_piloto);
    }
}
