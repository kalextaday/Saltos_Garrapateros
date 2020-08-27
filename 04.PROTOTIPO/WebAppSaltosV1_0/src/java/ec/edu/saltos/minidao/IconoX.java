package ec.edu.saltos.minidao;

import java.util.Objects;

/**
 *
 * @author guffenix
 */
public class IconoX {

    private String clase;
    private String nombre;

    public IconoX(String clase, String nombre) {
        this.clase = clase;
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public IconoX() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.clase);
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
        final IconoX other = (IconoX) obj;
        if (!Objects.equals(this.clase, other.clase)) {
            return false;
        }
        return true;
    }

}
