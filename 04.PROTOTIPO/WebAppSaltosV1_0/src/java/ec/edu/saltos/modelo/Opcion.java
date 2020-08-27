package ec.edu.saltos.modelo;
// Generated 21-ago-2020 20:56:19 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Opcion generated by hbm2java
 */
public class Opcion  implements java.io.Serializable {


     private Integer idOpcion;
     private Opcion opcion;
     private String opcNombre;
     private String opcDescripcion;
     private String opcPagina;
     private String opcIcono;
     private Integer opcIndex;
     private String opcEstatus;
     private Set<OpcionPerfil> opcionPerfils = new HashSet<OpcionPerfil>(0);
     private Set<Opcion> opcions = new HashSet<Opcion>(0);

    public Opcion() {
    }

    public Opcion(Opcion opcion, String opcNombre, String opcDescripcion, String opcPagina, String opcIcono, Integer opcIndex, String opcEstatus, Set<OpcionPerfil> opcionPerfils, Set<Opcion> opcions) {
       this.opcion = opcion;
       this.opcNombre = opcNombre;
       this.opcDescripcion = opcDescripcion;
       this.opcPagina = opcPagina;
       this.opcIcono = opcIcono;
       this.opcIndex = opcIndex;
       this.opcEstatus = opcEstatus;
       this.opcionPerfils = opcionPerfils;
       this.opcions = opcions;
    }
   
    public Integer getIdOpcion() {
        return this.idOpcion;
    }
    
    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }
    public Opcion getOpcion() {
        return this.opcion;
    }
    
    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }
    public String getOpcNombre() {
        return this.opcNombre;
    }
    
    public void setOpcNombre(String opcNombre) {
        this.opcNombre = opcNombre;
    }
    public String getOpcDescripcion() {
        return this.opcDescripcion;
    }
    
    public void setOpcDescripcion(String opcDescripcion) {
        this.opcDescripcion = opcDescripcion;
    }
    public String getOpcPagina() {
        return this.opcPagina;
    }
    
    public void setOpcPagina(String opcPagina) {
        this.opcPagina = opcPagina;
    }
    public String getOpcIcono() {
        return this.opcIcono;
    }
    
    public void setOpcIcono(String opcIcono) {
        this.opcIcono = opcIcono;
    }
    public Integer getOpcIndex() {
        return this.opcIndex;
    }
    
    public void setOpcIndex(Integer opcIndex) {
        this.opcIndex = opcIndex;
    }
    public String getOpcEstatus() {
        return this.opcEstatus;
    }
    
    public void setOpcEstatus(String opcEstatus) {
        this.opcEstatus = opcEstatus;
    }
    public Set<OpcionPerfil> getOpcionPerfils() {
        return this.opcionPerfils;
    }
    
    public void setOpcionPerfils(Set<OpcionPerfil> opcionPerfils) {
        this.opcionPerfils = opcionPerfils;
    }
    public Set<Opcion> getOpcions() {
        return this.opcions;
    }
    
    public void setOpcions(Set<Opcion> opcions) {
        this.opcions = opcions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idOpcion);
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
        final Opcion other = (Opcion) obj;
        if (!Objects.equals(this.idOpcion, other.idOpcion)) {
            return false;
        }
        return true;
    }




}


