package maurosimoni.BEU2W2D5.devices;

import jakarta.websocket.server.PathParam;
import maurosimoni.BEU2W2D5.devices.payload.DeviceCreatePayload;
import maurosimoni.BEU2W2D5.exception.NotFoundException;
import maurosimoni.BEU2W2D5.users.User;
import maurosimoni.BEU2W2D5.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UsersService userService;

    // testata OK
    @GetMapping("")
    public Page<Device> getDevice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        return deviceService.find(page, size, sortBy);
    }
    // testata -> Ok risponde ma vuota
    @GetMapping("/producers/{producer}")
    public Page<Device> getDeviceByProducer(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                            @PathVariable() Producers producer ) {
        return deviceService.findByProducer(page, size, "producer", producer);
    }
    // testata -> 403??????
    @GetMapping("/types/{type}")
    public Page<Device> getDeviceByType(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                            @PathVariable() DeviceType type ) {
        return deviceService.findByType(page, size, "type", type);
    }
    // testata -> 403??????
    //Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; Invalid UUID string: er
    @GetMapping("/models/{model}")
    public Page<Device> findByModel(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                        @PathVariable() String model ) {
        return deviceService.findByModel(page, size, "model", model);
    }
    // testata -> 403??????
    @GetMapping("/states/{state}")
    public Page<Device> findByState(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                    @PathVariable() DeviceState state ) {
        return deviceService.findByState(page, size, "state", state);
    }
    // testata OK
    @GetMapping("/{deviceId}")
    public Device getDeviceById(@PathVariable UUID deviceId) throws Exception {
        return deviceService.findById(deviceId);
    }
    // testata OK
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody @Validated DeviceCreatePayload body) {
        return deviceService.create(body);
    }

    //Request method 'PUT' is not supported  --> testata
    @PutMapping("/{deviceId}")
    public Device updateDevice(@PathVariable UUID deviceId, @RequestBody Device body) throws Exception {
        return deviceService.findByIdAndUpdate(deviceId, body);
    }
    //Request method 'PUT' is not supported  --> testata
    @PutMapping("/assignment")
    public Device assignDevice(@RequestParam UUID deviceID, @RequestParam UUID userID) throws Exception {
        User user = userService.findById(userID);
        Device device = deviceService.findByIdAndState(deviceID,DeviceState.DISPONIBILE);

        device.setState(DeviceState.ASSEGNATO);
        device.setUser(user);

        return deviceService.findByIdAndUpdate(deviceID, device);
    }
    //testata OK funziona
    @DeleteMapping("/{serial}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable UUID serial) throws NotFoundException {
        deviceService.findByIdAndDelete(serial);
    }
}
