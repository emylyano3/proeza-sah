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

    private XBeeMessage       xBeeMessage;

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
            DeviceStatus status = new DeviceStatus(
                    stat.get(DEVICE_ID),
                    DeviceType.getById(stat.get(DEVICE_TYPE)),
                    DeviceState.getById(stat.get(DEVICE_STATUS)));
            result.add(status);
        }
        return result;
    }

    private List<List<Byte>> processBinaryData(byte[] data) {
        List<List<Byte>> statuses = new ArrayList<>(1);
        for (int i = 0; i < data.length; i++) {
            if ((char)data[i] == DEVICE_STATUS_SEPARATOR) {
                List<Byte> status = new ArrayList<>(3);
                for (++i; i < data.length; i++) {
                    if ((char)data[i] != DEVICE_STATUS_SEPARATOR) {
                        status.add(data[i]);
                    }
                }
                statuses.add(status);
            }
        }
        return statuses;
    }
}