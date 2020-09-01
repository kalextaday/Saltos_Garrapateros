package ec.edu.saltos.converter;

import ec.edu.saltos.modelo.Paracaidista;
import ec.edu.saltos.modelo.Salto;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOParacaidista;
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
@FacesConverter("paracaidistaConverter")
public class ParacaidistaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Paracaidista oficina = null;
        if (string != null && string.trim().length() > 0) {
            oficina = new DAOParacaidista().obtenerPorId(Integer.parseInt(string));
        }
        return oficina;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Paracaidista) o).getIdParacaidista());
        } else {
            return null;
        }

    }

}
