package com.azaxxc.effintrakj.effinTrak.financetools.guardrails;

import com.azaxxc.effintrakj.effinTrak.financetools.config.AIChatProperties;
import org.springframework.stereotype.Component;

@Component
public class AIToolPolicy {

    private final AIChatProperties properties;

    public AIToolPolicy(AIChatProperties properties) {
        this.properties = properties;
    }

    public PolicyDecision checkOperationAllowed(String operation) {
        if (operation == null || operation.isBlank()) {
            return PolicyDecision.blocked("UNKNOWN_OPERATION", "Operation could not be determined.");
        }

        if (!properties.isEnforceToolPolicies()) {
            return PolicyDecision.permit();
        }

        if (!properties.getEnabledOperations().contains(operation)) {
            return PolicyDecision.blocked(
                    "OPERATION_DISABLED",
                    "This operation is currently disabled by server policy."
            );
        }

        return PolicyDecision.permit();
    }

    public record PolicyDecision(boolean allowed, String errorCode, String message) {
        public static PolicyDecision permit() {
            return new PolicyDecision(true, null, null);
        }

        public static PolicyDecision blocked(String errorCode, String message) {
            return new PolicyDecision(false, errorCode, message);
        }
    }
}
