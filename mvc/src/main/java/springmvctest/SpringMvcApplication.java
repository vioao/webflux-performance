package springmvctest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author w.ao on 2020/7/6
 */
@RestController
@SpringBootApplication
public class SpringMvcApplication {
    @Value("${url}")
    private String url;

    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @GetMapping(value = "/block/{times}")
    public String block(@PathVariable int times) {
        return restTemplate.getForObject(url + "/hello/" + times, String.class);
    }
}
