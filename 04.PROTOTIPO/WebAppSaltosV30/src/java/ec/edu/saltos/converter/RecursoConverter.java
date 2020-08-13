package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOAeronave;
import ec.edu.saltos.dao.DAORecurso;
import ec.edu.saltos.modelo.Aeronave;
import ec.edu.saltos.modelo.Recurso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kalex
 */
@FacesConverter("recursoConverter")
public class RecursoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Recurso recurso = null;
        if (string != null && string.trim().length() > 0) {
            recurso = new DAORecurso().obtenerPorId(Integer.parseInt(string));
        }
        return recurso;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Recurso) o).getIdRecurso());
        } else {
            return null;
        }

    }

}
