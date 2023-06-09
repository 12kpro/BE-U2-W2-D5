package maurosimoni.BEU2W2D5.reservations.payload;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import maurosimoni.BEU2W2D5.devices.Device;
import maurosimoni.BEU2W2D5.users.User;

import java.time.LocalDate;

public class ReservationPayload {
    private LocalDate data;
    private Device device;
    private User user;
}
