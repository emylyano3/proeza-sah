package proeza.sah.radio;

import org.apache.log4j.Logger;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;

public class DummyRadio implements ILocalRadio {

    private static final Logger log = Logger.getLogger(DummyRadio.class);

    @Override
    public void addDataListener(IDataReceiveListener dataReceiveListener) {

    }

    @Override
    public void removeDataListeners() {

    }

    @Override
    public void openConnection() throws XBeeException {
        log.info("Opening connection");
    }

    @Override
    public void closeConnection() throws XBeeException {
        log.info("Closing connection");
    }

    @Override
    public XBeeDevice getXbeeDevice() {
        return null;
    }

    @Override
    public void sendBroadcast(byte[] data) throws TimeoutException, XBeeException {
        log.info("Sending broadcast");
    }
}