package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAODescuento;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.Descuento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("descuentoConverter")
public class DescuentoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Descuento descuento = null;
        if (string != null && string.trim().length() > 0) {
            descuento = new DAODescuento().obtenerPorId(Integer.parseInt(string));
        }
        return descuento;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Descuento) o).getIdDescuento());
        } else {
            return null;
        }

    }

}
