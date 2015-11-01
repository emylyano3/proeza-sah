package proeza.sah.device;

public class DeviceStatus {

    private int         id;
    private String      name;
    private DeviceType  type;
    private DeviceState state;
    private int         stateValue;

    public DeviceStatus(int id, String name, DeviceType type) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    public int getStateValue() {
        return this.stateValue;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public void setStateValue(int stateValue) {
        this.stateValue = stateValue;
    }
}