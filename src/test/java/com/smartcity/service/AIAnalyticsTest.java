package com.smartcity.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIAnalyticsTest {

    @Test
    void testAIPriorityAnalytics() {

        AIAnalyticsService analyticsService =
                new AIAnalyticsService();

        assertDoesNotThrow(() ->
                analyticsService.viewAIPriorityAnalytics()
        );
    }
}