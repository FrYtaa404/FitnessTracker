package pl.wsb.fitnesstracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CapFitnessTrackerApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Autowired
    UserRepository userRepository;


    @Test
    void testUserRepoQuery(){

        var users = userRepository.findByDomain("@domain.com");

        System.out.println("Ilosc uzytkownikow:" + users.size());

        users = userRepository.findAll();
        System.out.println("Ilosc wszystkich uzytkownikow:" + users.size());
        //var

        //var users = userRepository.
    }

}
