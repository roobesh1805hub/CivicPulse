package com.smartcity.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AITest {

    @Test
    void testAIPriorityPrediction() {

        AIService aiService = new AIService();

        String[] complaints = {
                "Ambulance is stuck in heavy traffic and cannot reach the hospital",
                "One street light is not working near my house",
                "A major water pipeline has burst and is flooding the road",
                "Garbage has been overflowing for several days near the market"
        };

        for (String complaint : complaints) {

            String priority =
                    aiService.predictPriority(complaint);

            /*
             * AI may be available or unavailable.
             * If available, a priority should be returned.
             * If unavailable, the service should safely return null.
             */
            if (priority != null) {
                assertFalse(priority.isBlank());
                assertTrue(
                        priority.equals("HIGH")
                                || priority.equals("MEDIUM")
                                || priority.equals("LOW")
                );
            }
        }
    }
}