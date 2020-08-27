package ec.edu.saltos.minidao;


import ec.edu.saltos.config.IconoConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author guffenix
 */
public class MDAOIconox {

    private IconoX iconoSeleccionado;
    private List<IconoX> iconosx;

    public MDAOIconox() {
        List<IconoConfig> iconos = Arrays.asList(IconoConfig.values());
        iconosx = new ArrayList<>();
        iconos.forEach(ic -> iconosx.add(new IconoX(ic.getClase(), ic.getNombre())));

    }

    public IconoX getIconoSeleccionado() {
        return iconoSeleccionado;
    }

    public void setIconoSeleccionado(IconoX iconoSeleccionado) {
        this.iconoSeleccionado = iconoSeleccionado;
    }

    public List<IconoX> getIconosx() {
        return iconosx;
    }

    public void setIconosx(List<IconoX> iconosx) {
        this.iconosx = iconosx;
    }

    public IconoX obtenerPorClase(String _clase) {
        return iconosx.stream().filter(ix -> _clase.equals(ix.getClase()))
                .findFirst()
                .orElse(null);

    }

}
