package ec.edu.saltos.modelo;
// Generated 12-ago-2020 14:57:41 by Hibernate Tools 4.3.1



/**
 * Permiso generated by hbm2java
 */
public class Permiso  implements java.io.Serializable {


     private Integer idPermiso;
     private Perfil perfil;
     private Recurso recurso;
     private String nombreper;
     private String estadoper;

    public Permiso() {
    }

    public Permiso(Perfil perfil, Recurso recurso, String nombreper, String estadoper) {
       this.perfil = perfil;
       this.recurso = recurso;
       this.nombreper = nombreper;
       this.estadoper = estadoper;
    }
   
    public Integer getIdPermiso() {
        return this.idPermiso;
    }
    
    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Recurso getRecurso() {
        return this.recurso;
    }
    
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }
    public String getNombreper() {
        return this.nombreper;
    }
    
    public void setNombreper(String nombreper) {
        this.nombreper = nombreper;
    }
    public String getEstadoper() {
        return this.estadoper;
    }
    
    public void setEstadoper(String estadoper) {
        this.estadoper = estadoper;
    }




}


