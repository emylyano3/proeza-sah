package proeza.sah.device;

public enum DeviceState {

    OFF(0),
    ON(1),
    DISABLED(2),
    OOO(2), // Out of order
    UNKNOWN(3),
    ;

    private int id;

    private DeviceState(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static DeviceState getById(int id) {
        for (DeviceState state : values()) {
            if (state.getId() == id) {
                return state;
            }
        }
        return null;
    }
}