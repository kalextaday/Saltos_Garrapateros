package ec.edu.saltos.controlador.seguridad;

import ec.edu.saltos.config.ControlSesion;
import ec.edu.saltos.config.EstadosConfig;
import ec.edu.saltos.controlador.FiltroAcceso;
import ec.edu.saltos.modelo.Perfil;
import ec.edu.saltos.modelo.UsuarioAcceso;
import ec.edu.saltos.persistencia.DAOPerfil;
import ec.edu.saltos.persistencia.DAOUsuarioAcceso;
import ec.edu.saltos.util.FechaUtil;
import ec.edu.saltos.util.PrimeUtiles;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class BeanPerfil extends FiltroAcceso implements Serializable{

    private List<Perfil> perfiles;
    private Perfil perfilSeleccionado;
    private Perfil perfilPanel;
    private UsuarioAcceso usuarioAcceso;
    private StreamedContent archivoDescarga;
    private Boolean permiteDescargar;
    private StringBuilder rutaPlantilla;
    private StringBuilder rutaReporte;

    private static final Logger LOG = Logger.getLogger(BeanPerfil.class.getName());

    public BeanPerfil() {
        super(FacesContext.getCurrentInstance().getExternalContext());

    }

    @PostConstruct
    public void init() {
        permiteDescargar = Boolean.FALSE;
        initPerfil();
        actualizarMisPerfiles();
        obtenerMiUsuarioAcc();
        initParametrosExcel();
    }

    public void initParametrosExcel() {

        rutaPlantilla = new StringBuilder();
        /*
        rutaPlantilla.append(DirectorioConfig.DIRECTORIO_CARGA_TEMPLATE.getUrl())
                .append(Reportes.REPORTE_PERMISOS_PERFIL.getNombre())
                .append(DocumentoConfig.DOT.getValor())
                .append(DocumentoConfig.EXTENSION_XLSX.getValor());

        rutaReporte = new StringBuilder();
        rutaReporte.append(DirectorioConfig.DIRECTORIO_REPORTE_GENERADO.getUrl())
                .append(Reportes.REPORTE_PERMISOS_PERFIL.getNombre())
                .append(DocumentoConfig.UNDERSCORE.getValor())
                .append(entidadSeleccionada.getEntidadCodigo())
                .append(DocumentoConfig.DOT.getValor())
                .append(DocumentoConfig.EXTENSION_XLSX.getValor());
        */
    }

    public void obtenerMiUsuarioAcc() {
        ControlSesion cs = new ControlSesion();
        usuarioAcceso = new DAOUsuarioAcceso().obtenerPorId(Integer.parseInt(cs.obtenerIdUsuarioAccesoSesionActiva()));
    }

    public void actualizarMisPerfiles() {
        DAOPerfil daope = new DAOPerfil();

        if (perfiles == null || perfiles.isEmpty()) {
            try {
                perfiles = daope.obtenerTodos();
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    public Perfil preparaCrear() {
        LOG.log(Level.INFO, "Preparando nuevo perfil");

        perfilSeleccionado = new Perfil();
        PrimeUtiles.primeExecute("PF('wv-crear').show();");
        return perfilSeleccionado;

    }

    public Perfil preparaActualizar() {
        LOG.log(Level.INFO, "Preparando perfil para editar");
        PrimeUtiles.primeExecute("PF('wv-actualizar').show();");
        return perfilSeleccionado;

    }

    public void preparaEliminar() {

        LOG.log(Level.INFO, "Preparando perfil {0} para eliminar", perfilSeleccionado.getPerfilNombre());
        PrimeUtiles.primeExecute("PF('wv-eliminar').show();");
    }

    public void preparaDeshabilitar() {

        LOG.log(Level.INFO, "Preparando perfil {0} para deshabilitar, esperando confirmaci\u00f3n del administrador...", perfilSeleccionado.getPerfilNombre());
        PrimeUtiles.primeExecute("PF('wv-deshabilitar').show();");
    }

    public void preparaHabilitar() {

        LOG.log(Level.INFO, "Preparando perfil {0} para habilitar, esperando confirmaci\u00f3n del administrador...", perfilSeleccionado.getPerfilNombre());
        PrimeUtiles.primeExecute("PF('wv-habilitar').show();");
    }

    public void agregarPerfil() {
        try{
            DAOPerfil daop = new DAOPerfil();
            perfilSeleccionado.setPerfilEstatus(EstadosConfig.PERFIL_EST_ACTIVADO.getCodigo());
            
            if(daop.guardar(perfilSeleccionado)){
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:", "Se registro correctamente.");
            }else{
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ","Hubo un error al registrar");
            }
        }catch(Exception e){
            LOG.log(Level.INFO, "Excepcion al agregar: {0}",e);
        }finally{
            PrimeUtiles.primeExecute("PF('wv-crear').hide();");
        }
    }
    
    public void modificarPerfil() {

        DAOPerfil daop = new DAOPerfil();
        try {
            if (daop.editar(perfilSeleccionado)) {
                perfiles = null;
                perfilSeleccionado = null;
                perfilPanel = new Perfil();

                PrimeUtiles.primeUpdate("form:panelPerfil");
                actualizarMisPerfiles();
                
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Info:","Perfil actualizado correctamente.");
            } else {
                PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_ERROR,"Error: ", "Error al actualizar.");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            PrimeUtiles.primeExecute("PF('wv-actualizar').hide();");
        }

    }
    
    public void activarPerfil() {
        try {
            DAOPerfil daop = new DAOPerfil();
            perfilSeleccionado.setPerfilEstatus(EstadosConfig.PERFIL_EST_ACTIVADO.getCodigo());
            
            daop.editar(perfilSeleccionado);
            perfiles = null;
            perfilSeleccionado = null;
            perfilPanel = new Perfil();

            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Perfil HABILITADO correctamente.");
            //PrimeUtiles.primeUpdate("form:panelPerfil");
            actualizarMisPerfiles();
            LOG.log(Level.INFO, "Perfil activado correctamente.");


        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            PrimeUtiles.primeExecute("PF('wv-habilitar').hide();");
        }

    }

    public void desactivarPerfil() {
        LOG.log(Level.INFO, "Se inicia el proceso de deshabilitacion de Perfil.");
        try {
            DAOPerfil daop = new DAOPerfil();
            perfilSeleccionado.setPerfilEstatus(EstadosConfig.PERFIL_EST_DESACTIVADO.getCodigo());
            daop.editar(perfilSeleccionado);
            perfiles = null;
            perfilSeleccionado = null;
            perfilPanel = new Perfil();

            //PrimeUtiles.primeUpdate("form:panelPerfil");
            PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Perfil DESHABILITADO correctamente.");
            actualizarMisPerfiles();
            LOG.log(Level.INFO, "Perfil desactivado correctamente.");


        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            PrimeUtiles.primeExecute("PF('wv-deshabilitar').hide();");
        }

    }

    

    

    public void descargarReporte() {
        /*
        List<VoReportePermisos> reportePermisosPorPerfil = new DAOPermiso().reportePermisosPorPerfilYEntidad(entidadSeleccionada.getRucEntidad());

        try {
            XSSFWorkbook reporteFinal = initExcel(rutaPlantilla.toString());
            if (depositarReportePermisos(reporteFinal, Reportes.REPORTE_PERMISOS_PERFIL.getHojaDeposito(), reportePermisosPorPerfil)) {
                archivoDescarga = guardarReporte(reporteFinal, rutaReporte.toString());
                if (archivoDescarga != null) {
                    permiteDescargar = true;
                    PrimeUtiles.primeExecute("PF('wv-descargaReporte').show();");
                } else {
                    permiteDescargar = false;
                    PrimeUtiles.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Estado:", "Reporte no encontrado!");
                }
            }
        } catch (IOException | InvalidFormatException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        */
    }

    public void initPerfil() {
        perfilPanel = new Perfil();
    }

    public void onRowSelect(SelectEvent event) {
        setPerfilSeleccionado((Perfil) event.getObject());
        setPerfilPanel((Perfil) event.getObject());
        LOG.log(Level.INFO, "Perfil listo para usar.");
        PrimeUtiles.primeUpdate("form:panelPerfil");

    }

    public void onRowUnselect(UnselectEvent event) {
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Perfil getPerfilSeleccionado() {
        return perfilSeleccionado;
    }

    public void setPerfilSeleccionado(Perfil perfilSeleccionado) {
        this.perfilSeleccionado = perfilSeleccionado;
    }

    public Perfil getPerfilPanel() {
        return perfilPanel;
    }

    public void setPerfilPanel(Perfil perfilPanel) {
        this.perfilPanel = perfilPanel;
    }

    public UsuarioAcceso getUsuarioAcceso() {
        return usuarioAcceso;
    }

    public void setUsuarioAcceso(UsuarioAcceso usuarioAcceso) {
        this.usuarioAcceso = usuarioAcceso;
    }

    public StreamedContent getArchivoDescarga() {
        return archivoDescarga;
    }

    public void setArchivoDescarga(StreamedContent archivoDescarga) {
        this.archivoDescarga = archivoDescarga;
    }

    public Boolean getPermiteDescargar() {
        return permiteDescargar;
    }

    public void setPermiteDescargar(Boolean permiteDescargar) {
        this.permiteDescargar = permiteDescargar;
    }

    public StringBuilder getRutaReporte() {
        return rutaReporte;
    }

    public void setRutaReporte(StringBuilder rutaReporte) {
        this.rutaReporte = rutaReporte;
    }

    public StringBuilder getRutaPlantilla() {
        return rutaPlantilla;
    }

    public void setRutaPlantilla(StringBuilder rutaPlantilla) {
        this.rutaPlantilla = rutaPlantilla;
    }

}
