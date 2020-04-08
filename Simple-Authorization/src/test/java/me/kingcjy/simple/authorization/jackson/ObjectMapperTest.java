package me.kingcjy.simple.authorization.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import me.kingcjy.simple.authorization.model.OAuthToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class ObjectMapperTest {

    @Test
    public void Property_Name_Test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);


        String testJson = "" +
                "{\n" +
                "    \"access_token\": \"9f38f0b4-8e55-4127-881f-517a2807af17\",\n" +
                "    \"token_type\": \"bearer\",\n" +
                "    \"refresh_token\": \"2c63bcf8-51e2-42ce-8e76-9456fb9ce81a\",\n" +
                "    \"scope\": \"read write trust\",\n" +
                "    \"expires_in\": 30000\n" +
                "}";

        OAuthToken oAuthToken = objectMapper.readValue(testJson, OAuthToken.class);

        System.out.println(oAuthToken);

        System.out.println(objectMapper.writeValueAsString(oAuthToken));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest() {
        System.out.printf("testSecret : %s\n", passwordEncoder.encode("testSecret"));
    }
}
