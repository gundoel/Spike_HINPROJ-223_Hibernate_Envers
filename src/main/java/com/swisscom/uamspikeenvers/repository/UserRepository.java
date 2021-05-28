package com.swisscom.uamspikeenvers.repository;

import com.swisscom.uamspikeenvers.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
   List<User> findAll();
   User findByUsername(String username);
   void deleteById(Long id);
}
