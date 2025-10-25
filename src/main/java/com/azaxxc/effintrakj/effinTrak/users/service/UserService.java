package com.azaxxc.effintrakj.effinTrak.users.service;

import com.azaxxc.effintrakj.effinTrak.users.config.Encoder;
import com.azaxxc.effintrakj.effinTrak.users.dto.LoginRequestDTO;
import com.azaxxc.effintrakj.effinTrak.users.dto.RegisterRequest;
import com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserCredentialsSummary;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserRepository;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserSummary;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    public UserService(UserRepository userRepository, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
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
        Encoder encoder = new Encoder();
        newUser.setFirstName(userRegisterRequest.getFirstName());
        newUser.setLastName(userRegisterRequest.getLastName());
        newUser.setEmail(userRegisterRequest.getEmail());
        newUser.setPhoneNumber(userRegisterRequest.getPhoneNumber());
        newUser.setPassword(encoder.passwordEncoder().encode(userRegisterRequest.getPassword()));
        newUser.setRole(userRegisterRequest.getRole());
        newUser.setActive(true);

        return newUser;
    }

    public Optional<User> authenticateUser(LoginRequestDTO loginRequestDTO) {
        Encoder encoder = new Encoder();
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            return Optional.empty();
        }
        String hashedPassword = user.get().getPassword();

        if(encoder.passwordEncoder().matches(password, hashedPassword)) {
            return user;
        }
        else return Optional.empty();
    }

    public Optional<UserResponseDTO> fetchUsersDetails(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty())
            return Optional.empty();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName(user.get().getFirstName());
        userResponseDTO.setLastName(user.get().getLastName());
        userResponseDTO.setPhoneNumber(user.get().getPhoneNumber());
        userResponseDTO.setEmail(user.get().getEmail());
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


}
