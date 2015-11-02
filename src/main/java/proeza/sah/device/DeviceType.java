package proeza.sah.device;

public enum DeviceType {

    LIGHT(1),
    MOTOR(2),
    SWITCH(3),
    SERVO(4),
    CONDITIONER(5),
    FRIDGE(6),
    CURTAIN(7),
    COFFE_MAKER(8),
    ALARM(9),
    SENSOR(10),
    ;

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