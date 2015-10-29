package proeza.sah.radio;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;

@Component
public class StatusDataListener implements IDataReceiveListener {

    @Override
    public void dataReceived(XBeeMessage xbeeMessage) {
        System.out.println(xbeeMessage.getDevice().get64BitAddress());
        System.out.println(Arrays.toString(xbeeMessage.getData()));
    }
}