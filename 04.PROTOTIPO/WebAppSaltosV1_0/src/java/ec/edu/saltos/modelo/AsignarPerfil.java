package ec.edu.saltos.modelo;
// Generated 31-ago-2020 17:42:00 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.Objects;

/**
 * AsignarPerfil generated by hbm2java
 */
public class AsignarPerfil  implements java.io.Serializable {


     private Integer idAsignarPerfil;
     private Perfil perfil;
     private UsuarioAcceso usuarioAcceso;
     private Date asiPerFechaCreacion;

    public AsignarPerfil() {
    }

    public AsignarPerfil(Perfil perfil, UsuarioAcceso usuarioAcceso, Date asiPerFechaCreacion) {
       this.perfil = perfil;
       this.usuarioAcceso = usuarioAcceso;
       this.asiPerFechaCreacion = asiPerFechaCreacion;
    }
   
    public Integer getIdAsignarPerfil() {
        return this.idAsignarPerfil;
    }
    
    public void setIdAsignarPerfil(Integer idAsignarPerfil) {
        this.idAsignarPerfil = idAsignarPerfil;
    }
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public UsuarioAcceso getUsuarioAcceso() {
        return this.usuarioAcceso;
    }
    
    public void setUsuarioAcceso(UsuarioAcceso usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
    }
    public Date getAsiPerFechaCreacion() {
        return this.asiPerFechaCreacion;
    }
    
    public void setAsiPerFechaCreacion(Date asiPerFechaCreacion) {
        this.asiPerFechaCreacion = asiPerFechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idAsignarPerfil);
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
        final AsignarPerfil other = (AsignarPerfil) obj;
        if (!Objects.equals(this.idAsignarPerfil, other.idAsignarPerfil)) {
            return false;
        }
        return true;
    }




}


