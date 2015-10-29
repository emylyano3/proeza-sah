package proeza.sah.radio;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.digi.xbee.api.listeners.IPacketReceiveListener;
import com.digi.xbee.api.packet.XBeePacket;

@Component
public class StatusPacketListener implements IPacketReceiveListener {

    @Override
    public void packetReceived(XBeePacket packet) {
        System.out.println(packet.getPacketLength());
        System.out.println(Arrays.toString(packet.getPacketData()));
    }
}