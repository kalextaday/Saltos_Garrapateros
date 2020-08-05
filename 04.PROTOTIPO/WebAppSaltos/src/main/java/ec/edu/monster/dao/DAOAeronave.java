/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.monster.dao;

import ec.edu.monster.modelo.Aeronave;

/**
 *
 * @author kalex
 */
public class DAOAeronave extends DAOGenerico<Aeronave, Integer> {
    
    public DAOAeronave() {
        super(Aeronave.class);
    }
    
    public Aeronave guardarAeronave(Aeronave aeronave) {
        return super.save(aeronave);
    }

    public Aeronave obtenerAeronavePorId(Integer _idAeronave) {
        return super.find(_idAeronave);
    }

    public Boolean editar(Aeronave _aeronave) {
        Aeronave perfil = super.merge(_aeronave);
        return perfil != null;
    }
    
}
