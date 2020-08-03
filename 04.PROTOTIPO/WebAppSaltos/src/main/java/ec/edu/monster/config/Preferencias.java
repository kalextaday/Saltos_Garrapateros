package ec.edu.monster.config;

import ec.edu.monster.utilidades.LogUtiles;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Preferencias implements Serializable {

    private static final long serialVersionUID = 1;

    private String theme = EstadosConfig.APP_TEMA_USER.getCodigo();

    private String layout = EstadosConfig.APP_LAYOUT_USER.getCodigo();

    private boolean overlayMenu = false;

    private boolean slimMenu = false;

    private boolean darkMenu = false;

    private String DIRECTORIO_CARGA_COMUN;// = DirectorioConfig.DIRECTORIO_CARGA_COMUN.getUrl();

    private int tiempoUpdate = EstadosConfig.TIEMPO_SESION_ADM.getId();

    private String versionApp = EstadosConfig.VERSION_APP.getCodigo();

    public void statusSesion() {
        LogUtiles.logSesion(Preferencias.class);
    }

    public String getTheme() {
        return theme;
    }

    public String getLayout() {
        return layout;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public boolean isOverlayMenu() {
        return this.overlayMenu;
    }

    public void setOverlayMenu(boolean value) {
        this.overlayMenu = value;
        this.slimMenu = false;
    }

    public boolean isSlimMenu() {
        return this.slimMenu;
    }

    public void setSlimMenu(boolean value) {
        this.slimMenu = value;
    }

    public boolean isDarkMenu() {
        return this.darkMenu;
    }

    public void setDarkMenu(boolean value) {
        this.darkMenu = value;
    }

    
    public String getDIRECTORIO_CARGA_COMUN() {
        return DIRECTORIO_CARGA_COMUN;
    }

    public void setDIRECTORIO_CARGA_COMUN(String DIRECTORIO_CARGA_COMUN) {
        this.DIRECTORIO_CARGA_COMUN = DIRECTORIO_CARGA_COMUN;
    }

    public int getTiempoUpdate() {
        return tiempoUpdate;
    }

    public void setTiempoUpdate(int tiempoUpdate) {
        this.tiempoUpdate = tiempoUpdate;
    }

    public String getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApp) {
        this.versionApp = versionApp;
    }
    
    
}
