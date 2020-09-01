package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.Salto;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.modelo.Vuelo;
import ec.edu.saltos.persistencia.DAOSalto;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.persistencia.DAOVuelo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("vueloConverter")
public class VueloConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Vuelo oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAOVuelo().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Vuelo) o).getIdVuelo());
        } else {
            return null;
        }

    }

}
