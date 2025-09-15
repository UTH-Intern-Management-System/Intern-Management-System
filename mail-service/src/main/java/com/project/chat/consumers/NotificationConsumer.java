<<<<<<< HEAD
package com.project.chat.consumers;

import com.project.chat.services.EmailService;
import com.project.chat.services.SMSService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final SMSService smsService;
    private final EmailService emailService;

    @KafkaListener(topics = "notification-sms", groupId = "notification-service")
    public void consumeSms(String message) {
        JSONObject json = new JSONObject(message);
        String to = json.getString("to");
        String content = json.getString("content");

        smsService.sendOtpViaSMS(to); // hoặc TwilioService.sendSms(to, content)
        System.out.println("📩 SMS sent via Kafka: " + message);
    }

    @KafkaListener(topics = "notification-email", groupId = "notification-service")
    public void consumeEmail(String message) {
        JSONObject json = new JSONObject(message);
        String to = json.getString("to");
        String subject = json.getString("subject");
        String content = json.getString("content");

        emailService.sendCancel(to, subject, content);
        System.out.println("📧 Email sent via Kafka: " + message);
    }
}
=======
package com.project.chat.consumers;

import com.project.chat.services.EmailService;
import com.project.chat.services.SMSService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final SMSService smsService;
    private final EmailService emailService;

    @KafkaListener(topics = "notification-sms", groupId = "notification-service")
    public void consumeSms(String message) {
        JSONObject json = new JSONObject(message);
        String to = json.getString("to");
        String content = json.getString("content");

        smsService.sendOtpViaSMS(to); // hoặc TwilioService.sendSms(to, content)
        System.out.println("📩 SMS sent via Kafka: " + message);
    }

    @KafkaListener(topics = "notification-email", groupId = "notification-service")
    public void consumeEmail(String message) {
        JSONObject json = new JSONObject(message);
        String to = json.getString("to");
        String subject = json.getString("subject");
        String content = json.getString("content");

        emailService.sendCancel(to, subject, content);
        System.out.println("📧 Email sent via Kafka: " + message);
    }
}
>>>>>>> b123204 (mail-service)
