package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAOResponsable;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.Responsable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("responsableConverter")
public class ResponsableConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Responsable responsable = null;
        if (string != null && string.trim().length() > 0) {
            responsable = new DAOResponsable().obtenerPorId(Integer.parseInt(string));
        }
        return responsable;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Responsable) o).getIdResponsable());
        } else {
            return null;
        }

    }

}
