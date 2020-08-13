/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.saltos.dao;


import ec.edu.saltos.modelo.Pedido;
import java.util.List;
/**
 *
 * @author kalex
 */
public class DAOPedido extends DAOGenerico<Pedido, Integer>{
    public DAOPedido() {
        super(Pedido.class);
    }
    
    public List<Pedido> obtenerPedidos() {
        return super.findAll();
    }
    
    public Boolean guardar(Pedido pedido){
        return super.save(pedido);
    }
    
    public Pedido obtenerPorId(Integer _idPedido) {
        return super.find(_idPedido);
    }

    public Boolean editar(Pedido _pedido) {
        return super.update(_pedido);
    }
    
    public Boolean eliminar(Pedido _pedido){
        return super.delete(_pedido);
    }
}
