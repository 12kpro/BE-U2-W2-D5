package maurosimoni.BEU2W2D5.reservations;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maurosimoni.BEU2W2D5.devices.Device;
import maurosimoni.BEU2W2D5.users.User;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate data;
    private Boolean inUse;
    @ManyToOne(cascade = CascadeType.ALL)
    private Device device;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Reservation(LocalDate data, Device device, User user) {
        this.data = data;
        this.device = device;
        this.user = user;
    }
}