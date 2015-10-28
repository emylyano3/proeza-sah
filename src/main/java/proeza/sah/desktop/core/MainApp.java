package proeza.sah.desktop.core;

import com.guiBuilder.api.component.application.DesktopApp;

public class MainApp extends DesktopApp<SahMainFrame, Splash> {

    @Override
    protected Splash createSplash() {
        return new Splash();
    }

    @Override
    protected void authenticateUser() {

    }

    @Override
    protected void challengeUser() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected SahMainFrame createMainFrame() {
        return new SahMainFrame();
    }
}