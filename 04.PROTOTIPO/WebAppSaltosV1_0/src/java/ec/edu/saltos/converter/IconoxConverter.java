package ec.edu.saltos.converter;


import ec.edu.saltos.minidao.IconoX;
import ec.edu.saltos.minidao.MDAOIconox;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("iconoxConverter")
public class IconoxConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        IconoX entidad = null;
        if (string != null && string.trim().length() > 0) {
            entidad = new MDAOIconox().obtenerPorClase(string);
        }
        return entidad;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return ((IconoX) o).getClase();
        } else {
            return null;
        }

    }

}
