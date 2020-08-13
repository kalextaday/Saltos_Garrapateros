package ec.edu.saltos.modelo;
// Generated 12-ago-2020 14:57:41 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Pedido generated by hbm2java
 */
public class Pedido  implements java.io.Serializable {


     private Integer idPedido;
     private Cliente cliente;
     private Descuento descuento;
     private Paracaidista paracaidista;
     private Salto salto;
     private ServicioAdicional servicioAdicional;
     private Date fechaped;
     private BigDecimal costosalto;
     private BigDecimal costototalped;
     private Set<DetalleVuelo> detalleVuelos = new HashSet<DetalleVuelo>(0);

    public Pedido() {
    }

    public Pedido(Cliente cliente, Descuento descuento, Paracaidista paracaidista, Salto salto, ServicioAdicional servicioAdicional, Date fechaped, BigDecimal costosalto, BigDecimal costototalped, Set<DetalleVuelo> detalleVuelos) {
       this.cliente = cliente;
       this.descuento = descuento;
       this.paracaidista = paracaidista;
       this.salto = salto;
       this.servicioAdicional = servicioAdicional;
       this.fechaped = fechaped;
       this.costosalto = costosalto;
       this.costototalped = costototalped;
       this.detalleVuelos = detalleVuelos;
    }
   
    public Integer getIdPedido() {
        return this.idPedido;
    }
    
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Descuento getDescuento() {
        return this.descuento;
    }
    
    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }
    public Paracaidista getParacaidista() {
        return this.paracaidista;
    }
    
    public void setParacaidista(Paracaidista paracaidista) {
        this.paracaidista = paracaidista;
    }
    public Salto getSalto() {
        return this.salto;
    }
    
    public void setSalto(Salto salto) {
        this.salto = salto;
    }
    public ServicioAdicional getServicioAdicional() {
        return this.servicioAdicional;
    }
    
    public void setServicioAdicional(ServicioAdicional servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }
    public Date getFechaped() {
        return this.fechaped;
    }
    
    public void setFechaped(Date fechaped) {
        this.fechaped = fechaped;
    }
    public BigDecimal getCostosalto() {
        return this.costosalto;
    }
    
    public void setCostosalto(BigDecimal costosalto) {
        this.costosalto = costosalto;
    }
    public BigDecimal getCostototalped() {
        return this.costototalped;
    }
    
    public void setCostototalped(BigDecimal costototalped) {
        this.costototalped = costototalped;
    }
    public Set<DetalleVuelo> getDetalleVuelos() {
        return this.detalleVuelos;
    }
    
    public void setDetalleVuelos(Set<DetalleVuelo> detalleVuelos) {
        this.detalleVuelos = detalleVuelos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idPedido);
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.idPedido, other.idPedido)) {
            return false;
        }
        return true;
    }




}


