package proeza.sah.device;

import java.util.ArrayList;
import java.util.List;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.models.XBeeMessage;

public class DeviceDiscoveryMessage {

    private static final char DEVICE_STATUS_SEPARATOR = '^';
    private static final int  DEVICE_ID               = 0;
    private static final int  DEVICE_TYPE             = 1;
    private static final int  DEVICE_STATUS           = 2;
    private static final int  DEVICE_STATUS_VALUE_MSB = 3;
    private static final int  DEVICE_STATUS_VALUE_LSB = 4;
    private static final int  DEVICE_NAME_SIZE        = 5;

    private XBeeMessage       xBeeMessage;

    public DeviceDiscoveryMessage() {
    }

    public DeviceDiscoveryMessage(XBeeMessage xBeeMessage) {
        this.xBeeMessage = xBeeMessage;
    }

    public XBeeMessage getXBeeMessage() {
        return this.xBeeMessage;
    }

    public boolean isValidMessage() {
        return this.xBeeMessage != null && this.xBeeMessage.getData() != null && this.xBeeMessage.getData().length > 0;
    }

    public RemoteXBeeDevice getRemoteRadio() {
        return this.xBeeMessage.getDevice();
    }

    public List<DeviceStatus> getDevices() {
        byte[] data = this.xBeeMessage.getData();
        List<List<Byte>> statuses = processBinaryData(data);
        List<DeviceStatus> result = new ArrayList<>(data.length >> 2);
        for (List<Byte> stat : statuses) {
            int id = stat.get(DEVICE_ID);
            DeviceType type = DeviceType.getById(stat.get(DEVICE_TYPE));
            DeviceState state = DeviceState.getById(stat.get(DEVICE_STATUS));
            int stateValue = readStateValue(stat);
            String name = readName(stat);
            DeviceStatus status = new DeviceStatus(id, name, type);
            status.setState(state);
            status.setStateValue(stateValue);
            result.add(status);
        }
        return result;
    }

    private String readName(List<Byte> stat) {
        byte size = stat.get(DEVICE_NAME_SIZE);
        StringBuilder builder = new StringBuilder();
        for (int i = DEVICE_NAME_SIZE + 1; i < DEVICE_NAME_SIZE + 1 + size; i++) {
            builder.append((char) stat.get(i).byteValue());
        }
        return builder.toString();
    }

    private int readStateValue(List<Byte> stat) {
        byte msb = stat.get(DEVICE_STATUS_VALUE_MSB);
        byte lsb = stat.get(DEVICE_STATUS_VALUE_LSB);
        return msb << 8 | lsb;
    }

    private List<List<Byte>> processBinaryData(byte[] data) {
        List<List<Byte>> statuses = new ArrayList<>(1);
        for (int i = 0; i < data.length; i++) {
            if ((char) data[i] == DEVICE_STATUS_SEPARATOR) {
                List<Byte> status = new ArrayList<>();
                while (i + 1 < data.length && data[i + 1] != DEVICE_STATUS_SEPARATOR) {
                    status.add(data[++i]);
                }
                statuses.add(status);
            }
        }
        return statuses;
    }
}