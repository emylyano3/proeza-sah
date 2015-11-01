package proeza.sah.desktop.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.digi.xbee.api.exceptions.XBeeException;
import com.guiBuilder.api.component.application.DesktopApp;

import proeza.sah.device.DeviceNetwork;

public class MainApp extends DesktopApp<MainFrame, Splash> {

    @Autowired
    private DeviceNetwork deviceNetwork;

    @Autowired
    private Splash        splash;

    @Override
    protected Splash createSplash() {
        return this.splash;
    }

    @Override
    protected void authenticateUser() {

    }

    @Override
    protected void challengeUser() {

    }

    @Override
    protected void init() {
        try {
            this.deviceNetwork.createNetwork();
        } catch (XBeeException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected MainFrame createMainFrame() {
        return new MainFrame();
    }
}