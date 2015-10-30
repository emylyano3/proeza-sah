package proeza.sah.radio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;

public class LocalRadio {
    private String                     port;
    private int                        baudRate;
    private XBeeDevice                 xbDevice;

    // private List<RemoteXBeeDevice> devices;
    // private List<RemoteXBeeDevice> devicesOnError = new ArrayList<>(0);

    private List<IDataReceiveListener> dataListeners = new ArrayList<>(0);

    public LocalRadio(String port, Integer baudRate) {
        this.port = port;
        this.baudRate = baudRate;
        this.xbDevice = new XBeeDevice(this.port, this.baudRate);
    }

    public void addDataListener(IDataReceiveListener dataReceiveListener) {
        this.dataListeners.add(dataReceiveListener);
    }

    public void removeDataListeners() {
        for (IDataReceiveListener listener : this.dataListeners) {
            this.xbDevice.removeDataListener(listener);
        }
        this.dataListeners.clear();
    }

    @PostConstruct
    public void openConnection() throws XBeeException {
        try {
            this.xbDevice.open();
        } catch (XBeeException e) {

        }
    }

    @PreDestroy
    public void closeConnection() throws XBeeException {
        this.xbDevice.close();
    }

    public XBeeDevice getXbeeDevice() {
        return this.xbDevice;
    }

    public void sendBroadcast(byte[] data) throws TimeoutException, XBeeException {
        this.xbDevice.sendBroadcastData(data);
    }
    //
    // public void setDataListeners(List<IDataReceiveListener> dataListeners) {
    // this.dataListeners = dataListeners;
    // }
    //
    // public void setPacketReceiveListeners(List<IPacketReceiveListener>
    // packetReceiveListeners) {
    // this.packetReceiveListeners = packetReceiveListeners;
    // }
    //
    // private void addAllDeviceDataListeners(List<IDataReceiveListener>
    // dataListeners) {
    // for (IDataReceiveListener dataListener : dataListeners) {
    // this.zbDevice.addDataListener(dataListener);
    // }
    // }
    //
    // private void
    // addAllDevicePacketReceiveListeners(List<IPacketReceiveListener>
    // packetReceiveListeners) {
    // for (IPacketReceiveListener listener : packetReceiveListeners) {
    // this.zbDevice.addPacketListener(listener);
    // }
    // }
}