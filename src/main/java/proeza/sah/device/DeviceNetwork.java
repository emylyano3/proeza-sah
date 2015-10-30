package proeza.sah.device;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;

import proeza.sah.radio.LocalRadio;

@Component
public class DeviceNetwork {

    private static final int       READ_STATUS_TIMEOUT  = 2000;
    private static final int       DISCOVERY_TIMEOUT    = 10000;
    private static final int       DISCOVERY_STEP_PAUSE = 100;

    @Autowired
    private LocalRadio             localRadio;

    @Autowired
    private DeviceManager          deviceManager;

    private List<RemoteXBeeDevice> xbees;
    private List<RemoteXBeeDevice> xbeesOnError         = new ArrayList<>(0);
    private List<Device>           devices              = new ArrayList<>(0);

    public void createNetwork() throws TimeoutException, XBeeException {
        try {
            clearData();
            discoverRadios();
            loadDevices();
        } catch (Exception e) {

        }
    }

    private void loadDevices() {
        this.xbees = this.localRadio.getXbeeDevice().getNetwork().getDevices();
        for (RemoteXBeeDevice device : this.xbees) {
            try {
                this.localRadio.getXbeeDevice().sendData(device, "STATUS".getBytes());
                XBeeMessage message = this.localRadio.getXbeeDevice().readDataFrom(device, READ_STATUS_TIMEOUT);
                this.devices.addAll(this.deviceManager.getDevices(this.localRadio, new DeviceDiscoveryMessage(message)));
            } catch (XBeeException e) {
                this.xbeesOnError.add(device);
            }
        }
    }

    private void discoverRadios() throws TimeoutException, XBeeException {
        this.localRadio.getXbeeDevice().getNetwork().setDiscoveryTimeout(DISCOVERY_TIMEOUT);
        this.localRadio.getXbeeDevice().getNetwork().startDiscoveryProcess();
        long timeWaited = 0;
        while (timeWaited < DISCOVERY_TIMEOUT && this.localRadio.getXbeeDevice().getNetwork().isDiscoveryRunning()) {
            try {
                timeWaited += DISCOVERY_STEP_PAUSE;
                Thread.sleep(DISCOVERY_STEP_PAUSE);
            } catch (InterruptedException e) {
                throw new XBeeException("Ocurrio un error durante el descubrimiento de la red XBee causado por: " + e.getMessage(), e);
            }
        }
    }

    private void clearData() {
        this.devices.clear();
        this.xbeesOnError.clear();
        this.localRadio.getXbeeDevice().getNetwork().clearDeviceList();
    }

    public List<Device> getDevices() {
        return this.devices;
    }
}