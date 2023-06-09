package maurosimoni.BEU2W2D5.reservations;

import maurosimoni.BEU2W2D5.devices.Device;
import maurosimoni.BEU2W2D5.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Optional<Reservation> findByDeviceAndUser(Device device, User user);



}
