package proeza.sah.desktop.listener.light;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.digi.xbee.api.exceptions.XBeeException;
import com.guiBuilder.api.component.listener.GBEventListener;
import com.guiBuilder.app.main.GBBeanFactory;
import com.guiBuilder.core.GuiManager;

import proeza.sah.radio.ILocalRadio;

public class LightsSwitchListener extends GBEventListener implements ActionListener {

    private ILocalRadio         radio = GBBeanFactory.getInstance().getBean(ILocalRadio.class);

    private static final String ON    = "ON";
    private static final String OFF   = "OFF";
    private boolean             isOn;

    public LightsSwitchListener(GuiManager manager) {
        super(manager);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            if (this.isOn) {
                this.isOn = false;
                this.radio.sendBroadcast(OFF.getBytes());
            } else {
                this.isOn = true;
                this.radio.sendBroadcast(ON.getBytes());
            }
        } catch (XBeeException e) {
            e.printStackTrace();
        }
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }
}