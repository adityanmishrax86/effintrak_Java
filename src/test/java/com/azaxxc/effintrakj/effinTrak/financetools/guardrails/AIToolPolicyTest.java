package com.azaxxc.effintrakj.effinTrak.financetools.guardrails;

import com.azaxxc.effintrakj.effinTrak.financetools.config.AIChatProperties;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIToolPolicyTest {

    @Test
    void checkOperationAllowed_WhenPoliciesDisabled_AllowsOperation() {
        AIChatProperties properties = new AIChatProperties();
        properties.setEnforceToolPolicies(false);
        AIToolPolicy policy = new AIToolPolicy(properties);

        AIToolPolicy.PolicyDecision decision = policy.checkOperationAllowed("DELETE_EXPENSE");

        assertTrue(decision.allowed());
    }

    @Test
    void checkOperationAllowed_WhenOperationNotEnabled_Blocks() {
        AIChatProperties properties = new AIChatProperties();
        properties.setEnforceToolPolicies(true);
        properties.setEnabledOperations(new LinkedHashSet<>(List.of("ADD_EXPENSE")));
        AIToolPolicy policy = new AIToolPolicy(properties);

        AIToolPolicy.PolicyDecision decision = policy.checkOperationAllowed("DELETE_EXPENSE");

        assertFalse(decision.allowed());
        assertEquals("OPERATION_DISABLED", decision.errorCode());
    }

    @Test
    void checkOperationAllowed_WhenOperationEnabled_Allows() {
        AIChatProperties properties = new AIChatProperties();
        properties.setEnforceToolPolicies(true);
        properties.setEnabledOperations(new LinkedHashSet<>(List.of("ADD_EXPENSE", "DELETE_EXPENSE")));
        AIToolPolicy policy = new AIToolPolicy(properties);

        AIToolPolicy.PolicyDecision decision = policy.checkOperationAllowed("DELETE_EXPENSE");

        assertTrue(decision.allowed());
    }
}
