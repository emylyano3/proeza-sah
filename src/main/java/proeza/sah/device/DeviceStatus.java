package proeza.sah.device;

public class DeviceStatus {

    private int         id;
    private DeviceType  type;
    private DeviceState state;

    public DeviceStatus(int id, DeviceType type, DeviceState state) {
        super();
        this.id = id;
        this.type = type;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public DeviceType getType() {
        return this.type;
    }

    public DeviceState getState() {
        return this.state;
    }
}