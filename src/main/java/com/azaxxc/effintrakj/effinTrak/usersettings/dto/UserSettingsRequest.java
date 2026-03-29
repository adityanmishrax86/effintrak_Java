package com.azaxxc.effintrakj.effinTrak.usersettings.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserSettingsRequest {

    @Pattern(regexp = "^[A-Z]{3}$", message = "currencyCode must be ISO-4217 format, e.g. USD")
    private String currencyCode;

    private String locale;

    private String timeZone;

    private String dateFormat;

    private String aiPersona;

    private Boolean includeProactiveInsights;

    private Boolean includeCategoryHints;

    @Pattern(regexp = "^(MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY|SATURDAY|SUNDAY)$",
            message = "weekStartsOn must be a full weekday name in uppercase")
    private String weekStartsOn;
}
