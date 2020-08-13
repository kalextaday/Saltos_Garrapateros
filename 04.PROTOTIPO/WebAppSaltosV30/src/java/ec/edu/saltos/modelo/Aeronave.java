package ec.edu.saltos.modelo;
// Generated 12-ago-2020 14:57:41 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Aeronave generated by hbm2java
 */
public class Aeronave  implements java.io.Serializable {


     private int idAeronave;
     private String nombreaer;
     private String modeloaer;
     private Integer capacidadtotalaer;
     private Set<Vuelo> vuelos = new HashSet<Vuelo>(0);

    public Aeronave() {
    }

	
    public Aeronave(int idAeronave) {
        this.idAeronave = idAeronave;
    }
    public Aeronave(int idAeronave, String nombreaer, String modeloaer, Integer capacidadtotalaer, Set<Vuelo> vuelos) {
       this.idAeronave = idAeronave;
       this.nombreaer = nombreaer;
       this.modeloaer = modeloaer;
       this.capacidadtotalaer = capacidadtotalaer;
       this.vuelos = vuelos;
    }
   
    public int getIdAeronave() {
        return this.idAeronave;
    }
    
    public void setIdAeronave(int idAeronave) {
        this.idAeronave = idAeronave;
    }
    public String getNombreaer() {
        return this.nombreaer;
    }
    
    public void setNombreaer(String nombreaer) {
        this.nombreaer = nombreaer;
    }
    public String getModeloaer() {
        return this.modeloaer;
    }
    
    public void setModeloaer(String modeloaer) {
        this.modeloaer = modeloaer;
    }
    public Integer getCapacidadtotalaer() {
        return this.capacidadtotalaer;
    }
    
    public void setCapacidadtotalaer(Integer capacidadtotalaer) {
        this.capacidadtotalaer = capacidadtotalaer;
    }
    public Set<Vuelo> getVuelos() {
        return this.vuelos;
    }
    
    public void setVuelos(Set<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idAeronave;
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
        final Aeronave other = (Aeronave) obj;
        if (this.idAeronave != other.idAeronave) {
            return false;
        }
        return true;
    }




}


