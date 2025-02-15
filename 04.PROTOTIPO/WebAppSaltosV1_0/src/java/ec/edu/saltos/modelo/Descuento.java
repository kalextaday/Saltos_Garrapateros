package ec.edu.saltos.modelo;
// Generated 31-ago-2020 17:42:00 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Descuento generated by hbm2java
 */
public class Descuento  implements java.io.Serializable {


     private int idDescuento;
     private String desNombre;
     private String desDescripcion;
     private BigDecimal desValor;
     private Date desFechaCreacion;
     private Date desFechaMod;
     private Set<Pedido> pedidos = new HashSet<Pedido>(0);

    public Descuento() {
    }

	
    public Descuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }
    public Descuento(int idDescuento, String desNombre, String desDescripcion, BigDecimal desValor, Date desFechaCreacion, Date desFechaMod, Set<Pedido> pedidos) {
       this.idDescuento = idDescuento;
       this.desNombre = desNombre;
       this.desDescripcion = desDescripcion;
       this.desValor = desValor;
       this.desFechaCreacion = desFechaCreacion;
       this.desFechaMod = desFechaMod;
       this.pedidos = pedidos;
    }
   
    public int getIdDescuento() {
        return this.idDescuento;
    }
    
    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }
    public String getDesNombre() {
        return this.desNombre;
    }
    
    public void setDesNombre(String desNombre) {
        this.desNombre = desNombre;
    }
    public String getDesDescripcion() {
        return this.desDescripcion;
    }
    
    public void setDesDescripcion(String desDescripcion) {
        this.desDescripcion = desDescripcion;
    }
    public BigDecimal getDesValor() {
        return this.desValor;
    }
    
    public void setDesValor(BigDecimal desValor) {
        this.desValor = desValor;
    }
    public Date getDesFechaCreacion() {
        return this.desFechaCreacion;
    }
    
    public void setDesFechaCreacion(Date desFechaCreacion) {
        this.desFechaCreacion = desFechaCreacion;
    }
    public Date getDesFechaMod() {
        return this.desFechaMod;
    }
    
    public void setDesFechaMod(Date desFechaMod) {
        this.desFechaMod = desFechaMod;
    }
    public Set<Pedido> getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idDescuento;
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
        final Descuento other = (Descuento) obj;
        if (this.idDescuento != other.idDescuento) {
            return false;
        }
        return true;
    }




}


