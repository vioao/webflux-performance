package springfluxtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * @author w.ao on 2020/7/6
 */
@RestController
@SpringBootApplication
public class SpringFluxApplication {

    @Value("${url}")
    private String url;

    private final WebClient client = WebClient.create();

    public static void main(String[] args) {
        SpringApplication.run(SpringFluxApplication.class, args);
    }

    @GetMapping(value = "/reactor/{times}")
    public Mono<String> reactor(@PathVariable int times) {
        return Mono.just(times)
                .flatMap(t -> client.get()
                        .uri(url + "/hello/" + times).retrieve().bodyToMono(String.class));
    }
}
