package proeza.sah.device;

import java.util.List;

public class DeviceResourceManager {

    private List<DeviceResource> resources;

    public DeviceResourceManager(List<DeviceResource> resources) {
        super();
        this.resources = resources;
    }

    public DeviceResource getResource(DeviceType deviceType) {
        for (DeviceResource resource : this.resources) {
            if (deviceType.equals(resource.getDeviceType())) {
                return resource;
            }
        }
        return null;
    }
}