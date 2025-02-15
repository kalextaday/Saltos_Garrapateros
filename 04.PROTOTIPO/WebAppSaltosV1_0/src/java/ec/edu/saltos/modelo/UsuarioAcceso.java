package ec.edu.saltos.modelo;
// Generated 31-ago-2020 17:42:00 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * UsuarioAcceso generated by hbm2java
 */
public class UsuarioAcceso  implements java.io.Serializable {


     private Integer idUsuarioAcceso;
     private ConfigClave configClave;
     private Persona persona;
     private String usrAccesoNombre;
     private String usrAccesoClave;
     private Integer usrAccesoIntentosFallidos;
     private String usrAccesoEstatus;
     private Date usrAccesoFechaCreacion;
     private Date usrAccesoFechaMod;
     private Set<Vuelo> vuelos = new HashSet<Vuelo>(0);
     private Set<AsignarPerfil> asignarPerfils = new HashSet<AsignarPerfil>(0);
     private Set<Bitacora> bitacoras = new HashSet<Bitacora>(0);
     private Set<Paracaidista> paracaidistas = new HashSet<Paracaidista>(0);
     private Set<ServicioAdicional> servicioAdicionals = new HashSet<ServicioAdicional>(0);
     private Set<CabeceraFactura> cabeceraFacturasForIdUsuarioGeneradorFac = new HashSet<CabeceraFactura>(0);
     private Set<CabeceraFactura> cabeceraFacturasForIdUsuarioCliente = new HashSet<CabeceraFactura>(0);

    public UsuarioAcceso() {
    }

    public UsuarioAcceso(ConfigClave configClave, Persona persona, String usrAccesoNombre, String usrAccesoClave, Integer usrAccesoIntentosFallidos, String usrAccesoEstatus, Date usrAccesoFechaCreacion, Date usrAccesoFechaMod, Set<Vuelo> vuelos, Set<AsignarPerfil> asignarPerfils, Set<Bitacora> bitacoras, Set<Paracaidista> paracaidistas, Set<ServicioAdicional> servicioAdicionals, Set<CabeceraFactura> cabeceraFacturasForIdUsuarioGeneradorFac, Set<CabeceraFactura> cabeceraFacturasForIdUsuarioCliente) {
       this.configClave = configClave;
       this.persona = persona;
       this.usrAccesoNombre = usrAccesoNombre;
       this.usrAccesoClave = usrAccesoClave;
       this.usrAccesoIntentosFallidos = usrAccesoIntentosFallidos;
       this.usrAccesoEstatus = usrAccesoEstatus;
       this.usrAccesoFechaCreacion = usrAccesoFechaCreacion;
       this.usrAccesoFechaMod = usrAccesoFechaMod;
       this.vuelos = vuelos;
       this.asignarPerfils = asignarPerfils;
       this.bitacoras = bitacoras;
       this.paracaidistas = paracaidistas;
       this.servicioAdicionals = servicioAdicionals;
       this.cabeceraFacturasForIdUsuarioGeneradorFac = cabeceraFacturasForIdUsuarioGeneradorFac;
       this.cabeceraFacturasForIdUsuarioCliente = cabeceraFacturasForIdUsuarioCliente;
    }
   
    public Integer getIdUsuarioAcceso() {
        return this.idUsuarioAcceso;
    }
    
    public void setIdUsuarioAcceso(Integer idUsuarioAcceso) {
        this.idUsuarioAcceso = idUsuarioAcceso;
    }
    public ConfigClave getConfigClave() {
        return this.configClave;
    }
    
    public void setConfigClave(ConfigClave configClave) {
        this.configClave = configClave;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public String getUsrAccesoNombre() {
        return this.usrAccesoNombre;
    }
    
    public void setUsrAccesoNombre(String usrAccesoNombre) {
        this.usrAccesoNombre = usrAccesoNombre;
    }
    public String getUsrAccesoClave() {
        return this.usrAccesoClave;
    }
    
    public void setUsrAccesoClave(String usrAccesoClave) {
        this.usrAccesoClave = usrAccesoClave;
    }
    public Integer getUsrAccesoIntentosFallidos() {
        return this.usrAccesoIntentosFallidos;
    }
    
    public void setUsrAccesoIntentosFallidos(Integer usrAccesoIntentosFallidos) {
        this.usrAccesoIntentosFallidos = usrAccesoIntentosFallidos;
    }
    public String getUsrAccesoEstatus() {
        return this.usrAccesoEstatus;
    }
    
    public void setUsrAccesoEstatus(String usrAccesoEstatus) {
        this.usrAccesoEstatus = usrAccesoEstatus;
    }
    public Date getUsrAccesoFechaCreacion() {
        return this.usrAccesoFechaCreacion;
    }
    
    public void setUsrAccesoFechaCreacion(Date usrAccesoFechaCreacion) {
        this.usrAccesoFechaCreacion = usrAccesoFechaCreacion;
    }
    public Date getUsrAccesoFechaMod() {
        return this.usrAccesoFechaMod;
    }
    
    public void setUsrAccesoFechaMod(Date usrAccesoFechaMod) {
        this.usrAccesoFechaMod = usrAccesoFechaMod;
    }
    public Set<Vuelo> getVuelos() {
        return this.vuelos;
    }
    
    public void setVuelos(Set<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }
    public Set<AsignarPerfil> getAsignarPerfils() {
        return this.asignarPerfils;
    }
    
    public void setAsignarPerfils(Set<AsignarPerfil> asignarPerfils) {
        this.asignarPerfils = asignarPerfils;
    }
    public Set<Bitacora> getBitacoras() {
        return this.bitacoras;
    }
    
    public void setBitacoras(Set<Bitacora> bitacoras) {
        this.bitacoras = bitacoras;
    }
    public Set<Paracaidista> getParacaidistas() {
        return this.paracaidistas;
    }
    
    public void setParacaidistas(Set<Paracaidista> paracaidistas) {
        this.paracaidistas = paracaidistas;
    }
    public Set<ServicioAdicional> getServicioAdicionals() {
        return this.servicioAdicionals;
    }
    
    public void setServicioAdicionals(Set<ServicioAdicional> servicioAdicionals) {
        this.servicioAdicionals = servicioAdicionals;
    }
    public Set<CabeceraFactura> getCabeceraFacturasForIdUsuarioGeneradorFac() {
        return this.cabeceraFacturasForIdUsuarioGeneradorFac;
    }
    
    public void setCabeceraFacturasForIdUsuarioGeneradorFac(Set<CabeceraFactura> cabeceraFacturasForIdUsuarioGeneradorFac) {
        this.cabeceraFacturasForIdUsuarioGeneradorFac = cabeceraFacturasForIdUsuarioGeneradorFac;
    }
    public Set<CabeceraFactura> getCabeceraFacturasForIdUsuarioCliente() {
        return this.cabeceraFacturasForIdUsuarioCliente;
    }
    
    public void setCabeceraFacturasForIdUsuarioCliente(Set<CabeceraFactura> cabeceraFacturasForIdUsuarioCliente) {
        this.cabeceraFacturasForIdUsuarioCliente = cabeceraFacturasForIdUsuarioCliente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.idUsuarioAcceso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioAcceso other = (UsuarioAcceso) obj;
        if (!Objects.equals(this.idUsuarioAcceso, other.idUsuarioAcceso)) {
            return false;
        }
        return true;
    }




}


