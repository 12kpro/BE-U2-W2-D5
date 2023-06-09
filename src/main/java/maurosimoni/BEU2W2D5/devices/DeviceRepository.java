package maurosimoni.BEU2W2D5.devices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Page<Device> findByProducer(Producers producer, Pageable pageable);



    Page<Device> findByType(DeviceType type, Pageable pageable);

    Page<Device> findByModelIgnoreCase(String model, Pageable pageable);

    Page<Device> findByState(DeviceState state, Pageable pageable);

    Optional<Device> findByIdAndState(UUID id, DeviceState state);



}
