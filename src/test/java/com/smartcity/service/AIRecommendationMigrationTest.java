package com.smartcity.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIRecommendationMigrationTest {

    @Test
    void testUpdateMissingRecommendations() {

        AIRecommendationMigrationService migrationService =
                new AIRecommendationMigrationService();

        assertDoesNotThrow(() ->
                migrationService.updateMissingRecommendations()
        );
    }
}