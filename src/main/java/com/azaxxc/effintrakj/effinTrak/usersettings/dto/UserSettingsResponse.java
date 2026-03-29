package com.azaxxc.effintrakj.effinTrak.usersettings.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSettingsResponse {
    private Long userId;
    private String currencyCode;
    private String locale;
    private String timeZone;
    private String dateFormat;
    private String aiPersona;
    private boolean includeProactiveInsights;
    private boolean includeCategoryHints;
    private String weekStartsOn;
}
