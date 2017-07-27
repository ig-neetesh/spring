package com.trickyjava.aop.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String message) {
        List<String> lines = new ArrayList<>();
        lines.add("\n**[MAIL SENT]*******************************");
        lines.addAll(formatKeyValue("To:", to, 10, 30));
        lines.addAll(formatKeyValue("Subject:", subject, 10, 30));
        lines.addAll(formatKeyValue("Message:", message, 10, 30));
        lines.add("********************************************");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private List<String> formatKeyValue(String key, String value, int keyLength, int valLength) {
        List<String> lines = new ArrayList<>();
        String format = "*%" + keyLength + "s %-" + valLength + "s *";
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i += valLength) {
            key = i == 0 ? key : "";
            int length = (chars.length - i) > valLength ? valLength : (chars.length - i);
            String val = new String(chars, i, length);
            lines.add(String.format(format, key, val));
        }
        return lines;
    }
}
