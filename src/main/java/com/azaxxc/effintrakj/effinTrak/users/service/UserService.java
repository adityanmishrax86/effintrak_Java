package com.azaxxc.effintrakj.effinTrak.users.service;

import com.azaxxc.effintrakj.effinTrak.users.dto.LoginRequestDTO;
import com.azaxxc.effintrakj.effinTrak.users.dto.RegisterRequest;
import com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RefreshTokenService refreshTokenService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest userRegisterRequest) {

        String userEmail = userRegisterRequest.getEmail();
        Optional<User> user  = userRepository.findByEmail(userEmail);

        if(user.isPresent()) {
            throw new RuntimeException("User with email " + userEmail + " already exists");
        } else {
            User newUser = mapToUser(userRegisterRequest);
            userRepository.save(newUser);
        }
    }

    private User mapToUser(RegisterRequest userRegisterRequest) {
        User newUser = new User();
        // Use username as first name, empty last name
        newUser.setFirstName(userRegisterRequest.getUsername());
        newUser.setLastName("");
        newUser.setEmail(userRegisterRequest.getEmail());
        newUser.setPhoneNumber("");
        newUser.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        // Never trust role from registration payload.
        newUser.setRole("USER");
        newUser.setActive(true);

        return newUser;
    }

    public Optional<User> authenticateUser(LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            return Optional.empty();
        }
        String hashedPassword = user.get().getPassword();

        if(passwordEncoder.matches(password, hashedPassword)) {
            return user;
        }
        else return Optional.empty();
    }

    public Optional<UserResponseDTO> fetchUsersDetails(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty())
            return Optional.empty();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.get().getId());
        userResponseDTO.setFirstName(user.get().getFirstName());
        userResponseDTO.setLastName(user.get().getLastName());
        userResponseDTO.setPhoneNumber(user.get().getPhoneNumber());
        userResponseDTO.setEmail(user.get().getEmail());
        userResponseDTO.setRole(user.get().getRole());
        userResponseDTO.setActive(user.get().isActive());

        return Optional.of(userResponseDTO);
    }

    public void logout() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(refreshTokenService::deleteByUser);
    }

    @Cacheable("users")
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
