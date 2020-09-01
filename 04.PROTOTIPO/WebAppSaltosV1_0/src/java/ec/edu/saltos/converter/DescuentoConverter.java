package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.Descuento;
import ec.edu.saltos.modelo.Salto;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAODescuento;
import ec.edu.saltos.persistencia.DAOSalto;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
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
        Descuento oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAODescuento().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
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
