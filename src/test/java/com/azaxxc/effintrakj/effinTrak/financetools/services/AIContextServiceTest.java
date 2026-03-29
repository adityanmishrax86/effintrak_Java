package com.azaxxc.effintrakj.effinTrak.financetools.services;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.repo.BankAccountRepository;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsResponse;
import com.azaxxc.effintrakj.effinTrak.usersettings.service.UserSettingsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AIContextServiceTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private UserSettingsService userSettingsService;

    @InjectMocks
    private AIContextService aiContextService;

    @Test
    void buildUserContext_WithCategoriesAndAccounts_ShouldIncludeIdsAndNames() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Groceries");

        BankAccount account = new BankAccount();
        account.setId(10L);
        account.setName("Primary Checking");
        account.setBalance(1200.0);

        when(categoryService.getCategories()).thenReturn(List.of(category));
        when(bankAccountRepository.findByuser_id(99L)).thenReturn(List.of(account));
        when(userSettingsService.getEffectiveSettings(99L)).thenReturn(UserSettingsResponse.builder()
                .userId(99L)
                .currencyCode("EUR")
                .locale("de-DE")
                .timeZone("Europe/Berlin")
                .dateFormat("dd.MM.yyyy")
                .weekStartsOn("MONDAY")
                .aiPersona("concise")
                .includeCategoryHints(true)
                .includeProactiveInsights(false)
                .build());

        String context = aiContextService.buildUserContext(99L);

        assertThat(context).contains("Currency: EUR");
        assertThat(context).contains("Locale: de-DE");
        assertThat(context).contains("ID: 1, Name: Groceries");
        assertThat(context).contains("ID: 10, Name: Primary Checking");
    }

    @Test
    void getCategoryContext_ShouldReturnKeyValuePairs() {
        Category category = new Category();
        category.setId(2L);
        category.setName("Bills");

        when(categoryService.getCategories()).thenReturn(List.of(category));

        String context = aiContextService.getCategoryContext();

        assertThat(context).isEqualTo("2=Bills");
    }

    @Test
    void getBankAccountContext_ShouldReturnKeyValuePairs() {
        BankAccount account = new BankAccount();
        account.setId(7L);
        account.setName("Credit Card");

        when(bankAccountRepository.findByuser_id(5L)).thenReturn(List.of(account));

        String context = aiContextService.getBankAccountContext(5L);

        assertThat(context).isEqualTo("7=Credit Card");
    }
}
