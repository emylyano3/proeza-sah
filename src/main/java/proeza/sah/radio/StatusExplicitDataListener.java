package proeza.sah.radio;

import org.springframework.stereotype.Component;

import com.digi.xbee.api.listeners.IExplicitDataReceiveListener;
import com.digi.xbee.api.models.ExplicitXBeeMessage;

@Component
public class StatusExplicitDataListener implements IExplicitDataReceiveListener {

    @Override
    public void explicitDataReceived(ExplicitXBeeMessage explicitXBeeMessage) {
        System.out.println(explicitXBeeMessage);
    }
}