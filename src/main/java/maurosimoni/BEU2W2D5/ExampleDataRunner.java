package maurosimoni.BEU2W2D5;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import maurosimoni.BEU2W2D5.devices.DeviceService;
import maurosimoni.BEU2W2D5.devices.DeviceType;
import maurosimoni.BEU2W2D5.devices.Producers;
import maurosimoni.BEU2W2D5.devices.payload.DeviceCreatePayload;
import maurosimoni.BEU2W2D5.users.Role;
import maurosimoni.BEU2W2D5.users.UsersService;
import maurosimoni.BEU2W2D5.users.payload.UserCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Slf4j
@Component
public class ExampleDataRunner implements CommandLineRunner {
    @Autowired
    UsersService utenteService;
    @Autowired
    DeviceService deviceService;
    Faker faker = new Faker(new Locale("it"));
    private Boolean auto = false;
    @Override
    public void run(String... args) throws Exception {
        if(auto){
            createUsers();
            createDevices();
        }
    }

    public void createUsers(){
        for (int i = 0; i < 10; i++) {
            try {

                String name = faker.name().firstName();
                String surname = faker.name().lastName();
                String userName = faker.name().username();
                String email = faker.internet().emailAddress();
                String password = faker.internet().password();
                Role role = faker.options().option(Role.class);
                UserCreatePayload user = new UserCreatePayload(name, surname, userName,email,password);
                utenteService.create(user);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void createDevices(){
        for (int i = 0; i < 10; i++) {
            try {

                 UUID id = UUID.randomUUID();
                 String model = faker.commerce().productName();
                 Producers producer = faker.options().option(Producers.class);
                 DeviceType type = faker.options().option(DeviceType.class);

                DeviceCreatePayload device = new DeviceCreatePayload(id,model,producer,type);
                deviceService.create(device);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

//    private UUID id;
//    private String model;
//    private Producers producer;
//    private String serial;
//    private DeviceType type;