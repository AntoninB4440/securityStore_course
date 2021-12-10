package ab.diginamic.securityStore.service;

import ab.diginamic.securityStore.dto.UserDTO;
import ab.diginamic.securityStore.model.User;
import ab.diginamic.securityStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public User registerNewUserAccount(UserDTO userDTO){
        User newUser = new User();
        newUser.setEnabled(1);
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        newUser.setRole("ROLE_USER");

        newUser.setFirstname(userDTO.getFirstname());
        newUser.setLastname(userDTO.getLastname());
        newUser.setEmail(userDTO.getEmail());

        return userRepository.save(newUser);
    }
}
