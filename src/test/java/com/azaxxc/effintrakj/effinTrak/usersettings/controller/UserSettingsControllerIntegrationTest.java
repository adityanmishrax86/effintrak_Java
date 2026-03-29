package com.azaxxc.effintrakj.effinTrak.usersettings.controller;

import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsRequest;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsResponse;
import com.azaxxc.effintrakj.effinTrak.usersettings.service.UserSettingsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserSettingsController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserSettingsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserSettingsService userSettingsService;

    @MockBean
    private AuthenticatedUserResolver authenticatedUserResolver;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void getCurrentUserSettings_ShouldReturnSettings() throws Exception {
        UserSettingsResponse response = UserSettingsResponse.builder()
                .userId(1L)
                .currencyCode("USD")
                .locale("en-US")
                .timeZone("America/New_York")
                .dateFormat("yyyy-MM-dd")
                .aiPersona("balanced")
                .includeCategoryHints(true)
                .includeProactiveInsights(true)
                .weekStartsOn("MONDAY")
                .build();

        when(authenticatedUserResolver.resolveOrThrow(any())).thenReturn(1L);
        when(userSettingsService.getEffectiveSettings(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/user-settings/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyCode").value("USD"))
                .andExpect(jsonPath("$.timeZone").value("America/New_York"));
    }

    @Test
    @WithMockUser
    void updateCurrentUserSettings_ShouldReturnUpdatedSettings() throws Exception {
        UserSettingsRequest request = new UserSettingsRequest();
        request.setCurrencyCode("EUR");
        request.setLocale("de-DE");

        UserSettingsResponse response = UserSettingsResponse.builder()
                .userId(1L)
                .currencyCode("EUR")
                .locale("de-DE")
                .timeZone("Europe/Berlin")
                .dateFormat("dd.MM.yyyy")
                .aiPersona("concise")
                .includeCategoryHints(true)
                .includeProactiveInsights(false)
                .weekStartsOn("MONDAY")
                .build();

        when(authenticatedUserResolver.resolveOrThrow(any())).thenReturn(1L);
        when(userSettingsService.updateForUser(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/user-settings/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyCode").value("EUR"))
                .andExpect(jsonPath("$.locale").value("de-DE"));
    }
}
