package backend.tunetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController
public class ExampleApplication {
    public static void main(String[] args){
        SpringApplication.run(ExampleApplication.class, args);
    }

//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
//        return String.format("Hello %s!", name);
//    }
}
