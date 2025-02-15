package ec.edu.saltos.modelo;
// Generated 31-ago-2020 17:42:00 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.Objects;

/**
 * Bitacora generated by hbm2java
 */
public class Bitacora  implements java.io.Serializable {


     private Integer idBitacora;
     private UsuarioAcceso usuarioAcceso;
     private Date bitFecha;
     private String bitOperacion;
     private String bitOpcionAfectado;
     private String bitDescripcion;
     private String bitIp;
     private String bitSo;
     private String bitNavegador;

    public Bitacora() {
    }

    public Bitacora(UsuarioAcceso usuarioAcceso, Date bitFecha, String bitOperacion, String bitOpcionAfectado, String bitDescripcion, String bitIp, String bitSo, String bitNavegador) {
       this.usuarioAcceso = usuarioAcceso;
       this.bitFecha = bitFecha;
       this.bitOperacion = bitOperacion;
       this.bitOpcionAfectado = bitOpcionAfectado;
       this.bitDescripcion = bitDescripcion;
       this.bitIp = bitIp;
       this.bitSo = bitSo;
       this.bitNavegador = bitNavegador;
    }
   
    public Integer getIdBitacora() {
        return this.idBitacora;
    }
    
    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }
    public UsuarioAcceso getUsuarioAcceso() {
        return this.usuarioAcceso;
    }
    
    public void setUsuarioAcceso(UsuarioAcceso usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
    }
    public Date getBitFecha() {
        return this.bitFecha;
    }
    
    public void setBitFecha(Date bitFecha) {
        this.bitFecha = bitFecha;
    }
    public String getBitOperacion() {
        return this.bitOperacion;
    }
    
    public void setBitOperacion(String bitOperacion) {
        this.bitOperacion = bitOperacion;
    }
    public String getBitOpcionAfectado() {
        return this.bitOpcionAfectado;
    }
    
    public void setBitOpcionAfectado(String bitOpcionAfectado) {
        this.bitOpcionAfectado = bitOpcionAfectado;
    }
    public String getBitDescripcion() {
        return this.bitDescripcion;
    }
    
    public void setBitDescripcion(String bitDescripcion) {
        this.bitDescripcion = bitDescripcion;
    }
    public String getBitIp() {
        return this.bitIp;
    }
    
    public void setBitIp(String bitIp) {
        this.bitIp = bitIp;
    }
    public String getBitSo() {
        return this.bitSo;
    }
    
    public void setBitSo(String bitSo) {
        this.bitSo = bitSo;
    }
    public String getBitNavegador() {
        return this.bitNavegador;
    }
    
    public void setBitNavegador(String bitNavegador) {
        this.bitNavegador = bitNavegador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idBitacora);
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
        final Bitacora other = (Bitacora) obj;
        if (!Objects.equals(this.idBitacora, other.idBitacora)) {
            return false;
        }
        return true;
    }




}


