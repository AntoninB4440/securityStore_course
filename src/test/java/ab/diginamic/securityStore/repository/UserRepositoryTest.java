package ab.diginamic.securityStore.repository;

import ab.diginamic.securityStore.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void encodePassword(){
        User user = userRepository.findById(1L).get();
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        System.out.println(user.toString());
    }

}