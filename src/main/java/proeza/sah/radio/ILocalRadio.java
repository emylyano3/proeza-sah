package proeza.sah.radio;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;

public interface ILocalRadio {

    void addDataListener(IDataReceiveListener dataReceiveListener);

    void removeDataListeners();

    void openConnection() throws XBeeException;

    void closeConnection() throws XBeeException;

    XBeeDevice getXbeeDevice();

    void sendBroadcast(byte[] data) throws TimeoutException, XBeeException;
}