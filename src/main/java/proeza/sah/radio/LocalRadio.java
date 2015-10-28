package proeza.sah.radio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.listeners.IExplicitDataReceiveListener;

public class LocalRadio {
    private String     port;
    private int        baudRate;
    private XBeeDevice zbDevice;

    private List<IDataReceiveListener>         dataListeners         = new ArrayList<>(0);
    private List<IExplicitDataReceiveListener> explicitDataListeners = new ArrayList<>(0);

    public LocalRadio(String port, Integer baudRate) {
        this.port = port;
        this.baudRate = baudRate;
        this.zbDevice = new XBeeDevice(this.port, this.baudRate);
    }

    @PostConstruct
    public void openConnection() throws XBeeException {
        this.zbDevice.open();
    }

    @PreDestroy
    public void closeConnection() throws XBeeException {
        this.zbDevice.close();
    }

    public void addDataListener(IDataReceiveListener dataListener) {
        this.zbDevice.addDataListener(dataListener);
    }

    public void sendBroadcast(byte[] data) throws TimeoutException, XBeeException {
        this.zbDevice.sendBroadcastData(data);
    }

    public List<IDataReceiveListener> getDataListeners() {
        return this.dataListeners;
    }

    public void setDataListeners(List<IDataReceiveListener> dataListeners) {
        removeAllDeviceDataListeners();
        addAllDeviceDataListeners(dataListeners);
        this.dataListeners = dataListeners;
    }

    public List<IExplicitDataReceiveListener> getExplicitDataListeners() {
        return this.explicitDataListeners;
    }

    public void setExplicitDataListeners(List<IExplicitDataReceiveListener> explicitDataListeners) {
        removeAllDeviceDataListeners();
        addAllDeviceExplicitDataListeners(explicitDataListeners);
        this.explicitDataListeners = explicitDataListeners;
    }

    private void removeAllDeviceDataListeners() {
        for (IDataReceiveListener dataListener : this.dataListeners) {
            this.zbDevice.removeDataListener(dataListener);
        }
    }

    private void addAllDeviceDataListeners(List<IDataReceiveListener> dataListeners) {
        for (IDataReceiveListener dataListener : dataListeners) {
            this.zbDevice.addDataListener(dataListener);
        }
    }

    private void addAllDeviceExplicitDataListeners(List<IExplicitDataReceiveListener> explicitDataListeners) {
        for (IExplicitDataReceiveListener dataListener : explicitDataListeners) {
        }
    }
}