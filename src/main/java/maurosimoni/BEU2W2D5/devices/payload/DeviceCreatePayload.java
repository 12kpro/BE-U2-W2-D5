package maurosimoni.BEU2W2D5.devices.payload;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import maurosimoni.BEU2W2D5.devices.DeviceType;
import maurosimoni.BEU2W2D5.devices.Producers;

import java.util.UUID;
@Getter
public class DeviceCreatePayload {
    @NotNull(message = "Il Seriale (Id) è obbligatorio")
    private UUID id;
    @NotNull(message = "Il Modello è obbligatorio")
    @Size(min = 3, max = 20, message = "Nome min 3 caratteri, massimo 30")
    private String model;
    @NotNull(message = "Il Produttore è obbligatorio")
    private Producers producer;
    @NotNull(message = "Il Seriale è obbligatorio")
    private String serial;
    @NotNull(message = "Il Tipo di dispositivo è obbligatorio")
    private DeviceType type;
}
