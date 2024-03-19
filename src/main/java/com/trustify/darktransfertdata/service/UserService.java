package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.User;
import com.trustify.darktransfertdata.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User save(User user) {
        Optional<User> optionalUser = this.userRepository.findUserByUsername(user.getUsername());
        if (optionalUser.isPresent())
            throw new RuntimeException("Ce nom d'utilisateur existe deja pour un autre user");
        return this.userRepository.save(user);
    }

    public User findUserByUsernameAndPassword(String username, String password) {

        Optional<User> optionalUser = this.userRepository.findUserByUsernameAndPassword(username, password);
        if (optionalUser.isPresent())
            return optionalUser.get();

        throw new RuntimeException("Mot de pass ou nom d'utilisateur incorrect");
    }

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }


}
