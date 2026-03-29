package com.azaxxc.effintrakj.effinTrak.usersettings.service;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsRequest;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsResponse;
import com.azaxxc.effintrakj.effinTrak.usersettings.model.UserSettings;
import com.azaxxc.effintrakj.effinTrak.usersettings.repo.UserSettingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class UserSettingsService {

    private final UserSettingsRepository repository;
    private final UserService userService;

    public UserSettingsService(UserSettingsRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public Optional<UserSettings> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public UserSettingsResponse getEffectiveSettings(Long userId) {
        return toResponse(findByUserId(userId).orElseGet(() -> newDefault(userId)));
    }

    public UserSettingsResponse updateForUser(Long userId, UserSettingsRequest request) {
        UserSettings settings = repository.findByUserId(userId).orElseGet(() -> {
            UserSettings created = newDefault(userId);
            User user = userService.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
            created.setUser(user);
            return created;
        });

        apply(settings, request);
        return toResponse(repository.save(settings));
    }

    public UserSettings newDefault(Long userId) {
        UserSettings defaults = new UserSettings();
        User user = new User();
        user.setId(userId);
        defaults.setUser(user);
        return defaults;
    }

    private void apply(UserSettings settings, UserSettingsRequest request) {
        if (request.getCurrencyCode() != null) {
            String currencyCode = request.getCurrencyCode().trim().toUpperCase(Locale.ROOT);
            Currency.getInstance(currencyCode);
            settings.setCurrencyCode(currencyCode);
        }
        if (request.getLocale() != null) {
            String localeTag = request.getLocale().trim();
            Locale parsedLocale = Locale.forLanguageTag(localeTag);
            if (parsedLocale.getLanguage().isBlank()) {
                throw new IllegalArgumentException("Invalid locale tag: " + localeTag);
            }
            settings.setLocale(parsedLocale.toLanguageTag());
        }
        if (request.getTimeZone() != null) {
            String zoneId = request.getTimeZone().trim();
            ZoneId.of(zoneId);
            settings.setTimeZone(zoneId);
        }
        if (request.getDateFormat() != null) {
            settings.setDateFormat(request.getDateFormat().trim());
        }
        if (request.getAiPersona() != null) {
            settings.setAiPersona(request.getAiPersona().trim().toLowerCase(Locale.ROOT));
        }
        if (request.getIncludeProactiveInsights() != null) {
            settings.setIncludeProactiveInsights(request.getIncludeProactiveInsights());
        }
        if (request.getIncludeCategoryHints() != null) {
            settings.setIncludeCategoryHints(request.getIncludeCategoryHints());
        }
        if (request.getWeekStartsOn() != null) {
            String value = request.getWeekStartsOn().trim().toUpperCase(Locale.ROOT);
            DayOfWeek.valueOf(value);
            settings.setWeekStartsOn(value);
        }
    }

    private UserSettingsResponse toResponse(UserSettings settings) {
        return UserSettingsResponse.builder()
                .userId(settings.getUser().getId())
                .currencyCode(settings.getCurrencyCode())
                .locale(settings.getLocale())
                .timeZone(settings.getTimeZone())
                .dateFormat(settings.getDateFormat())
                .aiPersona(settings.getAiPersona())
                .includeProactiveInsights(settings.isIncludeProactiveInsights())
                .includeCategoryHints(settings.isIncludeCategoryHints())
                .weekStartsOn(settings.getWeekStartsOn())
                .build();
    }
}
