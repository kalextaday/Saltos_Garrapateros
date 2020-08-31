package ec.edu.saltos.modelo;
// Generated 31-ago-2020 17:42:00 by Hibernate Tools 4.3.1

import java.util.Objects;




/**
 * DetalleFactura generated by hbm2java
 */
public class DetalleFactura  implements java.io.Serializable {


     private Integer idDetVuelo;
     private CabeceraFactura cabeceraFactura;
     private Pedido pedido;

    public DetalleFactura() {
    }

    public DetalleFactura(CabeceraFactura cabeceraFactura, Pedido pedido) {
       this.cabeceraFactura = cabeceraFactura;
       this.pedido = pedido;
    }
   
    public Integer getIdDetVuelo() {
        return this.idDetVuelo;
    }
    
    public void setIdDetVuelo(Integer idDetVuelo) {
        this.idDetVuelo = idDetVuelo;
    }
    public CabeceraFactura getCabeceraFactura() {
        return this.cabeceraFactura;
    }
    
    public void setCabeceraFactura(CabeceraFactura cabeceraFactura) {
        this.cabeceraFactura = cabeceraFactura;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idDetVuelo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalleFactura other = (DetalleFactura) obj;
        if (!Objects.equals(this.idDetVuelo, other.idDetVuelo)) {
            return false;
        }
        return true;
    }




}


