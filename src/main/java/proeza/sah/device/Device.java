package proeza.sah.device;

import com.digi.xbee.api.RemoteXBeeDevice;

import proeza.sah.radio.LocalRadio;

public class Device {

    private LocalRadio       localRadio;
    private RemoteXBeeDevice remoteRadio;
    private DeviceStatus     status;

    public LocalRadio getLocalRadio() {
        return this.localRadio;
    }

    public void setLocalRadio(LocalRadio localRadio) {
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