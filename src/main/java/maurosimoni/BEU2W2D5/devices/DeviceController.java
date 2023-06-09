package maurosimoni.BEU2W2D5.devices;

import maurosimoni.BEU2W2D5.devices.payload.DeviceCreatePayload;
import maurosimoni.BEU2W2D5.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("")
    public Page<Device> getDevice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return deviceService.find(page, size, sortBy);
    }

    @GetMapping("/producers")
    public Page<Device> getDeviceByProducer(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                  @RequestParam() Producers producer ) {
        return deviceService.findByProducer(page, size, "producer", producer);
    }
    @GetMapping("/types")
    public Page<Device> getDeviceByType(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                            @RequestParam() DeviceType type ) {
        return deviceService.findByType(page, size, "type", type);
    }

    @GetMapping("/models")
    public Page<Device> findByModel(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                        @RequestParam() String model ) {
        return deviceService.findByModel(page, size, "model", model);
    }
    @GetMapping("/states")
    public Page<Device> findByState(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                    @RequestParam() DeviceState state ) {
        return deviceService.findByState(page, size, "state", state);
    }
    @GetMapping("/{serial}")
    public Device getDeviceById(@PathVariable UUID serial) throws Exception {
        return deviceService.findById(serial);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody @Validated DeviceCreatePayload body) {
        return deviceService.create(body);
    }

    @PutMapping("/{serial}")
    public Device updateDevice(@PathVariable UUID serial, @RequestBody Device body) throws Exception {
        return deviceService.findByIdAndUpdate(serial, body);
    }

    @DeleteMapping("/{serial}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable UUID serial) throws NotFoundException {
        deviceService.findByIdAndDelete(serial);
    }
}
