package com.trustify.darktransfertdata.repository;

import com.trustify.darktransfertdata.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findUserByUsernameAndPassword(String username, String password);

    public Optional<User> findUserByUsername(String username);
}
