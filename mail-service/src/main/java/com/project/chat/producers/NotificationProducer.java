<<<<<<< HEAD
package com.project.chat.producers;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEmail(String to, String subject, String content) {
        String payload = """
                {
                  "to": "%s",
                  "subject": "%s",
                  "content": "%s"
                }
                """.formatted(to, subject, content);

        kafkaTemplate.send("notification-email", payload);
    }

    public void sendSms(String to, String content) {
        String payload = """
                {
                  "to": "%s",
                  "content": "%s"
                }
                """.formatted(to, content);

        kafkaTemplate.send("notification-sms", payload);
    }
}
=======
package com.project.chat.producers;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendEmail(String to, String subject, String content) {
        String payload = """
                {
                  "to": "%s",
                  "subject": "%s",
                  "content": "%s"
                }
                """.formatted(to, subject, content);

        kafkaTemplate.send("notification-email", payload);
    }

    public void sendSms(String to, String content) {
        String payload = """
                {
                  "to": "%s",
                  "content": "%s"
                }
                """.formatted(to, content);

        kafkaTemplate.send("notification-sms", payload);
    }
}
>>>>>>> b123204 (mail-service)
