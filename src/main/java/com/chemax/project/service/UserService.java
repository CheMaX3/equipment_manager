package com.chemax.project.service;

import com.chemax.project.dto.UserProfile;
import com.chemax.project.entity.Role;
import com.chemax.project.entity.User;
import com.chemax.project.repository.RoleRepository;
import com.chemax.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with name: " + username + " not found");
        }
        return user;
    }

    public User findUserById(Integer userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public List<Role> allRoles() {return roleRepository.findAll(); }

    public void saveUser(User user) {
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public UserProfile getUserProfile (Integer id) {
        UserProfile userProfile = new UserProfile();
        User user = findUserById(id);
        userProfile.setId(user.getId());
        userProfile.setUsername(user.getUsername());
        userProfile.setPassword(user.getPassword());
        userProfile.setRoles(user.getRoles());
        return userProfile;
    }

    public void updateUserProfile (UserProfile userProfile, Integer id) {
        User user = userRepository.getReferenceById(id);
        user.setUsername(Optional.ofNullable(userProfile.getUsername()).orElse(user.getUsername()));
        user.setPassword(Optional.ofNullable(bCryptPasswordEncoder.encode(userProfile.getPassword())).orElse(user.getPassword()));
        user.setRoles(Optional.ofNullable(userProfile.getRoles()).orElse(user.getRoles()));
        userRepository.save(user);
    }

    public String passwordConfirmation(User user) {
        String message = "";
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
                message = "Пароли не совпадают";
            }
        return message;
    }

    public String userExists(User user) {
        String message = "";
        if (userRepository.findByUsername(user.getUsername()) != null) {
            message = "Пользователь с таким именем уже существует";
        }
        return message;
    }
}
