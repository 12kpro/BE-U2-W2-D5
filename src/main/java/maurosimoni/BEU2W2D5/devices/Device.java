package maurosimoni.BEU2W2D5.devices;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import maurosimoni.BEU2W2D5.users.User;

import java.util.UUID;
@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
public class Device {
    @Id
    private UUID id; //funziona da seriale
    private String model;
    @Enumerated(EnumType.STRING)
    private Producers producer;
    @Enumerated(EnumType.STRING)
    private DeviceType type;
    @Enumerated(EnumType.STRING)
    private DeviceState state;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;

    public Device(UUID id, String model, Producers producer, DeviceType type, DeviceState state) {
        this.id = id;
        this.model = model;
        this.producer = producer;
        this.type = type;
        this.state = state;
    }
}
