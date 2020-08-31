package ec.edu.saltos.modelo;
// Generated 31-ago-2020 17:42:00 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * FormaPago generated by hbm2java
 */
public class FormaPago  implements java.io.Serializable {


     private int idFormaPago;
     private String pagoNombre;
     private String pagoDescripcion;
     private Set<CabeceraFactura> cabeceraFacturas = new HashSet<CabeceraFactura>(0);

    public FormaPago() {
    }

	
    public FormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }
    public FormaPago(int idFormaPago, String pagoNombre, String pagoDescripcion, Set<CabeceraFactura> cabeceraFacturas) {
       this.idFormaPago = idFormaPago;
       this.pagoNombre = pagoNombre;
       this.pagoDescripcion = pagoDescripcion;
       this.cabeceraFacturas = cabeceraFacturas;
    }
   
    public int getIdFormaPago() {
        return this.idFormaPago;
    }
    
    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }
    public String getPagoNombre() {
        return this.pagoNombre;
    }
    
    public void setPagoNombre(String pagoNombre) {
        this.pagoNombre = pagoNombre;
    }
    public String getPagoDescripcion() {
        return this.pagoDescripcion;
    }
    
    public void setPagoDescripcion(String pagoDescripcion) {
        this.pagoDescripcion = pagoDescripcion;
    }
    public Set<CabeceraFactura> getCabeceraFacturas() {
        return this.cabeceraFacturas;
    }
    
    public void setCabeceraFacturas(Set<CabeceraFactura> cabeceraFacturas) {
        this.cabeceraFacturas = cabeceraFacturas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idFormaPago;
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
        final FormaPago other = (FormaPago) obj;
        if (this.idFormaPago != other.idFormaPago) {
            return false;
        }
        return true;
    }




}


