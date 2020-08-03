package ec.edu.monster.config;

/**
 *
 * @author guffenix
 */
public enum Ultra {

    CIFRADO("e06AiRQX1I"),
    EMAIL_PROPIEDADES("config-mail.properties"),
    ARCHIVO_PROPIEDADES("propiedades.properties");

    private final String nombre;

    Ultra(String _nombre) {
        this.nombre = _nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
