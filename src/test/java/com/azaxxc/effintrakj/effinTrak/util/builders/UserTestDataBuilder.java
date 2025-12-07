package com.azaxxc.effintrakj.effinTrak.util.builders;

import com.azaxxc.effintrakj.effinTrak.users.config.Encoder;
import com.azaxxc.effintrakj.effinTrak.users.models.User;

public class UserTestDataBuilder {
    private Long id;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@example.com";
    private String phoneNumber = "1234567890";
    private String password = "password123";
    private String role = "USER";
    private boolean isActive = true;

    public UserTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserTestDataBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserTestDataBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserTestDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserTestDataBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserTestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserTestDataBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public UserTestDataBuilder withActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        Encoder encoder = new Encoder();
        user.setPassword(encoder.passwordEncoder().encode(password));
        user.setRole(role);
        user.setActive(isActive);
        return user;
    }

    public static UserTestDataBuilder aUser() {
        return new UserTestDataBuilder();
    }
}

