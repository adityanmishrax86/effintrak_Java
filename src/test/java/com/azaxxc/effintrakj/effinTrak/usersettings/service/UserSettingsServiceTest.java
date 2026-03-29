package com.azaxxc.effintrakj.effinTrak.usersettings.service;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsRequest;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsResponse;
import com.azaxxc.effintrakj.effinTrak.usersettings.model.UserSettings;
import com.azaxxc.effintrakj.effinTrak.usersettings.repo.UserSettingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSettingsServiceTest {

    @Mock
    private UserSettingsRepository repository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserSettingsService service;

    @Test
    void getEffectiveSettings_WhenMissing_ShouldReturnDefaults() {
        when(repository.findByUserId(9L)).thenReturn(Optional.empty());

        UserSettingsResponse response = service.getEffectiveSettings(9L);

        assertThat(response.getUserId()).isEqualTo(9L);
        assertThat(response.getCurrencyCode()).isEqualTo("USD");
        assertThat(response.getLocale()).isEqualTo("en-US");
    }

    @Test
    void updateForUser_ShouldCreateAndPersistSettings() {
        User user = new User();
        user.setId(7L);

        when(repository.findByUserId(7L)).thenReturn(Optional.empty());
        when(userService.findById(7L)).thenReturn(Optional.of(user));
        when(repository.save(any(UserSettings.class))).thenAnswer(inv -> inv.getArgument(0));

        UserSettingsRequest request = new UserSettingsRequest();
        request.setCurrencyCode("INR");
        request.setLocale("en-IN");
        request.setTimeZone("Asia/Kolkata");
        request.setAiPersona("Coach");
        request.setIncludeProactiveInsights(false);

        UserSettingsResponse updated = service.updateForUser(7L, request);

        assertThat(updated.getUserId()).isEqualTo(7L);
        assertThat(updated.getCurrencyCode()).isEqualTo("INR");
        assertThat(updated.getLocale()).isEqualTo("en-IN");
        assertThat(updated.getTimeZone()).isEqualTo("Asia/Kolkata");
        assertThat(updated.getAiPersona()).isEqualTo("coach");
        assertThat(updated.isIncludeProactiveInsights()).isFalse();
    }

    @Test
    void updateForUser_WithInvalidCurrency_ShouldFail() {
        User user = new User();
        user.setId(5L);

        when(repository.findByUserId(5L)).thenReturn(Optional.empty());
        when(userService.findById(5L)).thenReturn(Optional.of(user));

        UserSettingsRequest request = new UserSettingsRequest();
        request.setCurrencyCode("BAD");

        assertThatThrownBy(() -> service.updateForUser(5L, request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
