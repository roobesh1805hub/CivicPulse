package com.smartcity.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileReportUtil {

    public static void saveReport(String report) {

        if (report == null || report.trim().isEmpty()) {
            System.out.println("Report is empty. Nothing to save.");
            return;
        }

        String fileName = "complaint_report.txt";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write(report);

            System.out.println(
                    "Complaint report saved successfully!"
            );

            System.out.println(
                    "File Name : " + fileName
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving complaint report."
            );

            System.out.println(
                    "File Error: " + e.getMessage()
            );
        }
    }
}