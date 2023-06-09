package maurosimoni.BEU2W2D5.reservations;


import maurosimoni.BEU2W2D5.devices.DeviceRepository;
import maurosimoni.BEU2W2D5.devices.DeviceState;
import maurosimoni.BEU2W2D5.exception.BadRequestException;
import maurosimoni.BEU2W2D5.reservations.payload.ReservationCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepo;
    @Autowired
    private DeviceRepository deviceRepo;

    public Reservation create(ReservationCreatePayload r) {
        reservationRepo.findByDeviceAndUser(r.getDevice(),r.getUser()).ifPresent(reservation -> {
            throw new BadRequestException("Dispositivo " + reservation.getDevice().getModel() + " già Assegnato!");
        });

        deviceRepo.findByIdAndState(r.getDevice().getId(), DeviceState.DISPONIBILE).orElseThrow(() -> {
            throw new BadRequestException("Dispositivo " + r.getDevice().getModel() + " già Assegnato!");
        });

        Reservation newReservation = new Reservation();
        return reservationRepo.save(newReservation);
    }


}
