<<<<<<< HEAD
package com.project.chat.controllers;

import com.project.chat.producers.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducer producer;

    @PostMapping("/email")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String content) {
        producer.sendEmail(to, subject, content);
        return "Email event published!";
    }

    @PostMapping("/sms")
    public String sendSms(@RequestParam String to,
                          @RequestParam String content) {
        producer.sendSms(to, content);
        return "SMS event published!";
    }
}
=======
package com.project.chat.controllers;

import com.project.chat.producers.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducer producer;

    @PostMapping("/email")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String content) {
        producer.sendEmail(to, subject, content);
        return "Email event published!";
    }

    @PostMapping("/sms")
    public String sendSms(@RequestParam String to,
                          @RequestParam String content) {
        producer.sendSms(to, content);
        return "SMS event published!";
    }
}
>>>>>>> b123204 (mail-service)
