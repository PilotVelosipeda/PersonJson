import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String json = Files.readString(Paths.get("src/main/resources/person.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person);

        Car car = new Car();
        car.setLicensePlate("A555MP23");
        person.setCar(car);

        Person.hobbies.add("EndlessRepair");

        String newJson = objectMapper.writeValueAsString(person);
        FileWriter fileWriter =
                new FileWriter("personModified.json");
        fileWriter.write(newJson);
        fileWriter.close();

    }
}
