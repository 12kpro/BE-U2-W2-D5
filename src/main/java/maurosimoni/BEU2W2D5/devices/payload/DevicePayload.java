package maurosimoni.BEU2W2D5.devices.payload;

import jakarta.validation.constraints.NotNull;
import maurosimoni.BEU2W2D5.devices.DeviceType;
import maurosimoni.BEU2W2D5.devices.Producers;

import java.util.UUID;

public class DevicePayload {
    private UUID id;
    private String model;
    private Producers producer;
    private DeviceType type;
}
