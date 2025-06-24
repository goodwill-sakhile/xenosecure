package com.securecryptor.report;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Converts log entries into a simple HTML report.
 */
public class HtmlReportGenerator {

    public static void generate(List<String> logLines, String outputPath) throws Exception {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write("<html><head><title>SecureCryptor Report</title></head><body>");
            writer.write("<h1>Audit Log Report</h1><ul>");

            for (String line : logLines) {
                writer.write("<li>" + escapeHtml(line) + "</li>");
            }

            writer.write("</ul><footer><p>Generated on " +
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                    "</p></footer></body></html>");
        }
    }

    private static String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;");
    }
}
