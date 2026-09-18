package com.smartcity.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIRecommendationTest {

    @Test
    void testAIRecommendations() {

        AIRecommendationService recommendationService =
                new AIRecommendationService();

        String[] complaints = {
                "Ambulance is stuck in heavy traffic near the hospital",
                "A major water pipeline has burst and is flooding the road",
                "Garbage has been overflowing for several days",
                "Street light is not working near my house",
                "There is a problem in the public park"
        };

        String[] priorities = {
                "HIGH",
                "HIGH",
                "MEDIUM",
                "LOW",
                "LOW"
        };

        for (int i = 0; i < complaints.length; i++) {

            String recommendation =
                    recommendationService.generateRecommendation(
                            complaints[i],
                            priorities[i]
                    );

            assertNotNull(recommendation);
            assertFalse(recommendation.isBlank());
        }
    }
}