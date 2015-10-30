package proeza.sah.device;

import javax.swing.Icon;

import com.guiBuilder.api.util.ImageUtils;

public class DeviceResource {

    private String      resourcePath;
    private DeviceType  deviceType;
    private DeviceState deviceState;

    public DeviceResource(String resourcePath, DeviceType deviceType, DeviceState deviceState) {
        super();
        this.resourcePath = resourcePath;
        this.deviceType = deviceType;
        this.deviceState = deviceState;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    public DeviceState getDeviceState() {
        return this.deviceState;
    }

    public Icon asIcon () {
        return ImageUtils.loadIcon(this.resourcePath, getClass());
    }
}