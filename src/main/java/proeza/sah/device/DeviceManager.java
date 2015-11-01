package proeza.sah.device;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import proeza.sah.radio.ILocalRadio;

@Component
public class DeviceManager {

    public List<Device> getDevices(ILocalRadio localRadio, DeviceDiscoveryMessage msg) {
        if (isValidMessage(msg)) {
            List<DeviceStatus> devicesStatus = msg.getDevices();
            List<Device> result = new ArrayList<>(devicesStatus.size());
            for (DeviceStatus deviceStatus : devicesStatus) {
                Device device = new Device();
                device.setLocalRadio(localRadio);
                device.setRemoteRadio(msg.getRemoteRadio());
                device.setStatus(deviceStatus);
                result.add(device);
            }
            return result;
        }
        return new ArrayList<>(0);
    }

    private boolean isValidMessage(DeviceDiscoveryMessage msg) {
        return msg != null && msg.isValidMessage();
    }
}