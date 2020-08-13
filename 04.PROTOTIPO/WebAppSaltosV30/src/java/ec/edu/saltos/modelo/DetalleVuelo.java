package ec.edu.saltos.modelo;
// Generated 12-ago-2020 14:57:41 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * DetalleVuelo generated by hbm2java
 */
public class DetalleVuelo  implements java.io.Serializable {


     private Integer idDetvuelo;
     private Pedido pedido;
     private Vuelo vuelo;
     private BigDecimal costototal;

    public DetalleVuelo() {
    }

    public DetalleVuelo(Pedido pedido, Vuelo vuelo, BigDecimal costototal) {
       this.pedido = pedido;
       this.vuelo = vuelo;
       this.costototal = costototal;
    }
   
    public Integer getIdDetvuelo() {
        return this.idDetvuelo;
    }
    
    public void setIdDetvuelo(Integer idDetvuelo) {
        this.idDetvuelo = idDetvuelo;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Vuelo getVuelo() {
        return this.vuelo;
    }
    
    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    public BigDecimal getCostototal() {
        return this.costototal;
    }
    
    public void setCostototal(BigDecimal costototal) {
        this.costototal = costototal;
    }




}


