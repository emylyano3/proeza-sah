package proeza.sah.radio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;

public class LocalRadio implements ILocalRadio {
    private String                     port;
    private int                        baudRate;
    private XBeeDevice                 xbDevice;

    private List<IDataReceiveListener> dataListeners = new ArrayList<>(0);

    public LocalRadio(String port, Integer baudRate) {
        this.port = port;
        this.baudRate = baudRate;
        this.xbDevice = new XBeeDevice(this.port, this.baudRate);
    }

    @Override
    public void addDataListener(IDataReceiveListener dataReceiveListener) {
        this.dataListeners.add(dataReceiveListener);
    }

    @Override
    public void removeDataListeners() {
        for (IDataReceiveListener listener : this.dataListeners) {
            this.xbDevice.removeDataListener(listener);
        }
        this.dataListeners.clear();
    }

    @Override
    @PostConstruct
    public void openConnection() throws XBeeException {
        this.xbDevice.open();
    }

    @Override
    @PreDestroy
    public void closeConnection() throws XBeeException {
        this.xbDevice.close();
    }

    @Override
    public XBeeDevice getXbeeDevice() {
        return this.xbDevice;
    }

    @Override
    public void sendBroadcast(byte[] data) throws TimeoutException, XBeeException {
        this.xbDevice.sendBroadcastData(data);
    }
}