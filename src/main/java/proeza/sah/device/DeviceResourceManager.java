package proeza.sah.device;

import java.util.List;

public class DeviceResourceManager {

    private List<DeviceResource> resources;

    public DeviceResourceManager(List<DeviceResource> resources) {
        super();
        this.resources = resources;
    }

    public DeviceResource getResource(DeviceType deviceType, DeviceState deviceState) {
        for (DeviceResource resource : this.resources) {
            if (deviceType.equals(resource.getDeviceType()) && deviceState.equals(resource.getDeviceState())) {
                return resource;
            }
        }
        return null;
    }

    public DeviceResource getResource(Device device, DeviceState deviceState) {
        for (DeviceResource resource : this.resources) {
            if (device.getStatus().getType().equals(resource.getDeviceType()) && deviceState.equals(resource.getDeviceState())) {
                return resource;
            }
        }
        return null;
    }
}