package com.smartcity.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIDecisionSupportTest {

    @Test
    void testHighPriorityPendingComplaints() {

        AIDecisionSupportService decisionSupportService =
                new AIDecisionSupportService();

        assertDoesNotThrow(() ->
                decisionSupportService
                        .viewHighPriorityPendingComplaints()
        );
    }
}