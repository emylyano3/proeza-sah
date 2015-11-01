package proeza.sah.device;

import com.digi.xbee.api.RemoteXBeeDevice;

import proeza.sah.radio.ILocalRadio;

public class Device {

    private ILocalRadio       localRadio;
    private RemoteXBeeDevice remoteRadio;
    private DeviceStatus     status;

    public ILocalRadio getLocalRadio() {
        return this.localRadio;
    }

    public void setLocalRadio(ILocalRadio localRadio) {
        this.localRadio = localRadio;
    }

    public RemoteXBeeDevice getRemoteRadio() {
        return this.remoteRadio;
    }

    public void setRemoteRadio(RemoteXBeeDevice remoteRadio) {
        this.remoteRadio = remoteRadio;
    }

    public DeviceStatus getStatus() {
        return this.status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }
}