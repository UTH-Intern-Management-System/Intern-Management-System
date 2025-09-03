package com.example.feedback;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/feedback/feedbacks")
public class FeedbackController {
    private final FeedbackRepository repo;
    public FeedbackController(FeedbackRepository repo){ this.repo=repo; }

    @GetMapping("/to/{userId}") public List<Feedback> toUser(@PathVariable Long userId){ return repo.findByToUserId(userId); }

    @PostMapping public Feedback submit(@RequestParam Long fromUserId, @RequestParam Long toUserId, @RequestParam String content){
        return repo.save(Feedback.builder().fromUserId(fromUserId).toUserId(toUserId).content(content).build());
    }
}
