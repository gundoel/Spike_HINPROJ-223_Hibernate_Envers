package com.swisscom.uamspikeenvers.controller;

import com.swisscom.uamspikeenvers.model.Role;
import com.swisscom.uamspikeenvers.model.User;
import com.swisscom.uamspikeenvers.repository.RoleRepository;
import com.swisscom.uamspikeenvers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@RestController
public class UserController {
   @Autowired
   UserRepository userRepository;

   @Autowired
   RoleRepository roleRepository;

   @GetMapping
   public ResponseEntity<List<User>> getUsers() {
      return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
   }

   @GetMapping(value = "/setup")
   public ResponseEntity<String> saveUsers() {
      Set<User> emptyUserSet = new HashSet<>();
      Role admin = new Role(1L, "admin", "setup", null);
      Role user = new Role(2L, "user", "setup", null);
      Set<Role> adminRoleSet = new HashSet<>();
      adminRoleSet.add(admin);
      Set<Role> userRoleSet = new HashSet<>();
      userRoleSet.add(user);
      User user1 = new User(1L, "HAMU4", "setup", null);
      User user2 = new User(2L, "FRMU1", "setup", null);
      userRepository.saveAll(List.of(user1, user2));
      roleRepository.saveAll(List.of(admin, user));
      return new ResponseEntity<>("Users saved", HttpStatus.OK);
   }

   @GetMapping(value = "/removeRolesFromUser")
   public ResponseEntity<List<User>> removeRolesFromUser(@RequestParam(value = "username") String username) {
      User user = userRepository.findByUsername(username);
      user.setCreatedBy("removeRolesFromUser");
      user.setRoles(null);
      userRepository.save(user);
      return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
   }

   @GetMapping(value = "/addRoleToUser")
   public ResponseEntity<List<User>> addRoleToUser(@RequestParam(value = "username") String username, @RequestParam(value = "rolename") String rolename) {
      User user = userRepository.findByUsername(username);
      Role role = roleRepository.findByRolename(rolename);
      user.setCreatedBy("addRoleToUser");
      Set<Role> roleSet = user.getRoles();
      roleSet.add(role);
      user.setRoles(roleSet);
      userRepository.save(user);
      return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
   }

   @GetMapping(value = "/deleteUser")
   public ResponseEntity<List<User>> deleteUser(@RequestParam String username) {
      userRepository.deleteById(userRepository.findByUsername(username).getId());
      return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
   }
}
