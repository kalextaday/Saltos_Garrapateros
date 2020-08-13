package ec.edu.saltos.converter;

import ec.edu.saltos.dao.DAOCliente;
import ec.edu.saltos.modelo.Cliente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guffenix
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Cliente cliente = null;
        if (string != null && string.trim().length() > 0) {
            cliente = new DAOCliente().obtenerPorId(Integer.parseInt(string));
        }
        return cliente;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return Integer.toString(((Cliente) o).getIdCliente());
        } else {
            return null;
        }

    }

}
