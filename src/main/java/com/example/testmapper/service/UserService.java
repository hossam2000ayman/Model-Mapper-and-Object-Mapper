package com.example.testmapper.service;

import com.example.testmapper.dto.UserDto;
import com.example.testmapper.mixins.UserMixin;
import com.example.testmapper.model.Device;
import com.example.testmapper.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto getUser() {
        //Mock db call
        User user = new User();
        user.setId(1234L);
        user.setEmail("hossam2000.albadry@gmail.com");
        user.setFirstName("Hossam");
        user.setLastName("Albadry");
        user.setPassword("123456");

        //how can we map from User to UserDto
        //one of the solution is using mapping library
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        return userDto;
    }

    public User saveUser() {

        UserDto userDto = new UserDto(1234L, "Hossam", "ALBadry", "Hossam AlBadry", "hossam2000.albadry@gmail.com");
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public Device convertJSONBackToEntity() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"brand\" : \"Nissan Sony\" , \"model\" : \" 2015\"}";
        Device device = objectMapper.readValue(json, Device.class);


        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        log.info("Device Object after convert from JSON String is :::" + device.toString());
        return device;
    }


    public Device[] convertJsonArrayBackToArrayOfEntities() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "[{ \"brand\" : \"Nissan Sony\" , \"model\" : \" 2015\"} , { \"brand\" : \"BMW\" , \"model\" : \" 2023\"}, {\"brand\": \"DEF\" , \"serial\": \"19-00417\", \"model\": \"hossam-model\"}]";
        Device[] devices = objectMapper.readValue(json, Device[].class);

        for (Device device : devices) {
            System.out.println(device);
        }

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        log.info("Device Object after convert from JSON String is :::" + Arrays.stream(devices).collect(Collectors.toList()));
        return devices;
    }


    public List<Device> convertJsonArrayBackToListOfEntity() throws JsonProcessingException {
        String json = "[{ \"brand\" : \"ABC\" , \"model\" : \"XYZ\"}, {\"brand\": \"DEFDEF\", \"model\": \"UVX\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Device> devices = objectMapper.readValue(json, new TypeReference<List<Device>>() {
        });

        for (Device device : devices) {
            System.out.println(device);
        }

        return devices;

    }

    public Map<String, String> convertJsonArrayBackToMapOfEntity() throws JsonProcessingException {
        String json = "{\"brand\":\"MITSHUBISHI\", \"model\": \"2022\", \"serial\": \"19-00417\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> devicesMap = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
        });
        for (Map.Entry<String, String> entry : devicesMap.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " , " + "Value : " + entry.getValue());
        }
        return devicesMap;

    }

    public String convertJavaObjectToJsonString() throws IOException {
        Device device = new Device();
        device.setBrand("2024");
        device.setSerial("19-00417");
        device.setModel("Lancer");

        ObjectMapper objectMapper = new ObjectMapper();
//        StringWriter writer = new StringWriter();
//        objectMapper.writeValue(writer, device);
//        String json = writer.toString();
        String json = objectMapper.writeValueAsString(device);
        System.out.println("json :::::  " + json);
        return json;
    }

    public String convertObjectToJsonUsingMixins() throws JsonProcessingException {
        User user = new User();
        user.setId(4321L);
        user.setFirstName("Hossam");
        user.setLastName("Ayman");
        user.setEmail("hossam2000.albadry@gmail.com");
        user.setPassword("1234567");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(User.class, UserMixin.class);


        String json = objectMapper.writeValueAsString(user);
        return json;
    }
}
