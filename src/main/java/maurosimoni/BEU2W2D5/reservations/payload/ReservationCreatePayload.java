package maurosimoni.BEU2W2D5.reservations.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import maurosimoni.BEU2W2D5.devices.Device;
import maurosimoni.BEU2W2D5.users.User;

import java.time.LocalDate;
@Getter
public class ReservationCreatePayload {
    @NotNull(message = "Il Data è obbligatoria")
    private LocalDate data;
    @NotNull(message = "Il Dispositivo è obbligatorio")
    private Device device;
    @NotNull(message = "L'utente è obbligatorio")
    private User user;
}
