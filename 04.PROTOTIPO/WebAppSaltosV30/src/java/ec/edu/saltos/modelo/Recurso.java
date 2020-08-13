package ec.edu.saltos.modelo;
// Generated 12-ago-2020 14:57:41 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Recurso generated by hbm2java
 */
public class Recurso  implements java.io.Serializable {


     private int idRecurso;
     private String nombrerec;
     private String paginarec;
     private String iconorec;
     private Integer indicerec;
     private String estadorec;
     private Set<Permiso> permisos = new HashSet<Permiso>(0);

    public Recurso() {
    }

	
    public Recurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }
    public Recurso(int idRecurso, String nombrerec, String paginarec, String iconorec, Integer indicerec, String estadorec, Set<Permiso> permisos) {
       this.idRecurso = idRecurso;
       this.nombrerec = nombrerec;
       this.paginarec = paginarec;
       this.iconorec = iconorec;
       this.indicerec = indicerec;
       this.estadorec = estadorec;
       this.permisos = permisos;
    }
   
    public int getIdRecurso() {
        return this.idRecurso;
    }
    
    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }
    public String getNombrerec() {
        return this.nombrerec;
    }
    
    public void setNombrerec(String nombrerec) {
        this.nombrerec = nombrerec;
    }
    public String getPaginarec() {
        return this.paginarec;
    }
    
    public void setPaginarec(String paginarec) {
        this.paginarec = paginarec;
    }
    public String getIconorec() {
        return this.iconorec;
    }
    
    public void setIconorec(String iconorec) {
        this.iconorec = iconorec;
    }
    public Integer getIndicerec() {
        return this.indicerec;
    }
    
    public void setIndicerec(Integer indicerec) {
        this.indicerec = indicerec;
    }
    public String getEstadorec() {
        return this.estadorec;
    }
    
    public void setEstadorec(String estadorec) {
        this.estadorec = estadorec;
    }
    public Set<Permiso> getPermisos() {
        return this.permisos;
    }
    
    public void setPermisos(Set<Permiso> permisos) {
        this.permisos = permisos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idRecurso;
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
        final Recurso other = (Recurso) obj;
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        return true;
    }




}


