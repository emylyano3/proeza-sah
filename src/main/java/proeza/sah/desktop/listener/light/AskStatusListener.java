package proeza.sah.desktop.listener.light;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.digi.xbee.api.exceptions.XBeeException;
import com.guiBuilder.api.component.listener.GBEventListener;
import com.guiBuilder.app.main.GBBeanFactory;
import com.guiBuilder.core.GuiManager;

import proeza.sah.radio.LocalRadio;

public class AskStatusListener extends GBEventListener implements ActionListener {

    private LocalRadio radio = GBBeanFactory.getInstance().getBean(LocalRadio.class);

    private static final String STATUS = "STATUS";

    public AskStatusListener(GuiManager manager) {
        super(manager);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            this.radio.sendBroadcast(STATUS.getBytes());
        } catch (XBeeException e) {
            e.printStackTrace();
        }
    }
}