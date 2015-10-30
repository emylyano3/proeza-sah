package proeza.sah.device;

public enum DeviceType {

    LIGHT(0),
    MOTOR(1),
    SWITCH(2),
    SENSOR(3),
    SERVO(4),
    APPLIANCE(5);

    private int id;

    private DeviceType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static DeviceType getById(int id) {
        for (DeviceType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}