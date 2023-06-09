package maurosimoni.BEU2W2D5.devices;

import maurosimoni.BEU2W2D5.devices.payload.DeviceCreatePayload;
import maurosimoni.BEU2W2D5.exception.BadRequestException;
import maurosimoni.BEU2W2D5.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepo;

    public Device create(DeviceCreatePayload d) {
        deviceRepo.findById(d.getId()).ifPresent(device -> {
            throw new BadRequestException("Dispositivo " + device.getId() + " esiste già!");
        });
        Device newDevice = new Device(d.getId(),d.getModel(),d.getProducer(),d.getType(), DeviceState.DISPONIBILE);
        return deviceRepo.save(newDevice);
    }

    public Page<Device> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return deviceRepo.findAll(pageable);
    }
    public Page<Device> findByProducer(int page, int size, String sortBy, Producers producer ) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return deviceRepo.findByProducer(producer,pageable);
    }

    public Page<Device> findByType(int page, int size, String sortBy, DeviceType type ) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return deviceRepo.findByType(type,pageable);
    }

    public Page<Device> findByModel(int page, int size, String sortBy, String model ) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return deviceRepo.findByModelIgnoreCase(model,pageable);
    }

    public Page<Device> findByState(int page, int size, String sortBy, DeviceState state ) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return deviceRepo.findByState(state,pageable);
    }
    public Device findById(UUID id) throws NotFoundException {
        return deviceRepo.findById(id).orElseThrow(() -> new NotFoundException("Device con id" + id + "Non trovato"));
    }
    public Device findByIdAndState(UUID id, DeviceState state) throws NotFoundException {
        return deviceRepo.findByIdAndState(id, state).orElseThrow(() -> new NotFoundException("Device con id" + id + "Non disponibile o non esistente"));
    }

    public Device findByIdAndUpdate(UUID id, Device d) throws NotFoundException {
        Device found = this.findById(id);

        found.setId(id);
        found.setModel(d.getModel());
        found.setProducer(d.getProducer());
        found.setType(d.getType());
        found.setState(d.getState());

        return deviceRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Device found = this.findById(id);
        deviceRepo.delete(found);
    }
}
