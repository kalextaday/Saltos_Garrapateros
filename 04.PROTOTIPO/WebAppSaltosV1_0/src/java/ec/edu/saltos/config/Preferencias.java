package ec.edu.saltos.config;

import ec.edu.saltos.util.LogUtiles;
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

    private int tiempoUpdate = EstadosConfig.TIEMPO_SESION_SUPER.getId();
    
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
