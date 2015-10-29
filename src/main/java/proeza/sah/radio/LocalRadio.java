package proeza.sah.radio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.listeners.IPacketReceiveListener;

public class LocalRadio {
    private String                       port;
    private int                          baudRate;
    private XBeeDevice                   zbDevice;

    private List<IDataReceiveListener>   dataListeners          = new ArrayList<>(0);
    private List<IPacketReceiveListener> packetReceiveListeners = new ArrayList<>(0);

    public LocalRadio(String port, Integer baudRate) {
        this.port = port;
        this.baudRate = baudRate;
        this.zbDevice = new XBeeDevice(this.port, this.baudRate);
    }

    @PostConstruct
    public void openConnection() throws XBeeException {
        this.zbDevice.open();
        addAllDeviceDataListeners(this.dataListeners);
        addAllDevicePacketReceiveListeners(this.packetReceiveListeners);
    }

    @PreDestroy
    public void closeConnection() throws XBeeException {
        this.zbDevice.close();
    }

    public void sendBroadcast(byte[] data) throws TimeoutException, XBeeException {
        this.zbDevice.sendBroadcastData(data);
    }

    public void setDataListeners(List<IDataReceiveListener> dataListeners) {
        this.dataListeners = dataListeners;
    }

    public void setPacketReceiveListeners(List<IPacketReceiveListener> packetReceiveListeners) {
        this.packetReceiveListeners = packetReceiveListeners;
    }

    private void addAllDeviceDataListeners(List<IDataReceiveListener> dataListeners) {
        for (IDataReceiveListener dataListener : dataListeners) {
            this.zbDevice.addDataListener(dataListener);
        }
    }

    private void addAllDevicePacketReceiveListeners(List<IPacketReceiveListener> packetReceiveListeners) {
        for (IPacketReceiveListener listener : packetReceiveListeners) {
            this.zbDevice.addPacketListener(listener);
        }
    }
}