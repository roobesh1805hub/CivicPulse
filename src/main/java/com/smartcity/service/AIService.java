package com.smartcity.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class AIService {

    private static final String AI_API_URL =
            "http://127.0.0.1:8000/predict";

    public String predictPriority(String complaintText) {

        HttpURLConnection connection = null;

        try {
            // Create connection
            URI uri = URI.create(AI_API_URL);
            connection = (HttpURLConnection) uri.toURL().openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );
            connection.setRequestProperty(
                    "Accept",
                    "application/json"
            );

            connection.setDoOutput(true);

            // Prepare JSON request
            String jsonRequest =
                    "{\"complaint_text\":\""
                            + escapeJson(complaintText)
                            + "\"}";

            // Send request
            try (OutputStream outputStream =
                         connection.getOutputStream()) {

                outputStream.write(
                        jsonRequest.getBytes(StandardCharsets.UTF_8)
                );
            }

            // Read response
            int responseCode = connection.getResponseCode();

            InputStream inputStream;

            if (responseCode >= 200 && responseCode < 300) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }

            StringBuilder response = new StringBuilder();

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(
                                         inputStream,
                                         StandardCharsets.UTF_8
                                 ))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            if (responseCode >= 200 && responseCode < 300) {

                String responseBody = response.toString();

                // Extract predicted_priority from JSON response
                String key = "\"predicted_priority\":\"";

                int start = responseBody.indexOf(key);

                if (start != -1) {

                    start += key.length();

                    int end = responseBody.indexOf(
                            "\"",
                            start
                    );

                    if (end != -1) {
                        return responseBody.substring(start, end);
                    }
                }

                System.out.println(
                        "AI response format was unexpected."
                );

            } else {

                System.out.println(
                        "AI API Error. HTTP Status: "
                                + responseCode
                );

                System.out.println(
                        "Response: "
                                + response
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "AI Service: Unavailable"
            );

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }


    private String escapeJson(String text) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}