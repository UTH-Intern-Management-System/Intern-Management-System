package com.example.feedback;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/feedback/feedbacks")
public class FeedbackController {
    private final FeedbackRepository repo;
    public FeedbackController(FeedbackRepository repo){ this.repo=repo; }

    @GetMapping
    public List<Feedback> getAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Feedback getById(@PathVariable Long id){
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Feedback not found"));
    }

    @GetMapping("/intern/{internId}")
    public List<Feedback> getByIntern(@PathVariable Long internId){
        return repo.findByInternId(internId);
    }

    @GetMapping("/mentor/{mentorId}")
    public List<Feedback> getByMentor(@PathVariable Long mentorId){
        return repo.findByMentorId(mentorId);
    }

    @GetMapping("/type/{type}")
    public List<Feedback> getByType(@PathVariable String type){
        return repo.findByType(type);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Feedback create(@RequestBody FeedbackCreateRequest req){
        Feedback fb = Feedback.builder()
                .internId(req.getInternId())
                .mentorId(req.getMentorId())
                .type(req.getType())
                .category(req.getCategory())
                .rating(req.getRating())
                .comment(req.getComment())
                .status("submitted")
                .build();
        return repo.save(fb);
    }

    @PutMapping("/{id}")
    public Feedback update(@PathVariable Long id, @RequestBody FeedbackUpdateRequest req){
        Feedback fb = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Feedback not found"));
        if(req.getType()!=null) fb.setType(req.getType());
        if(req.getCategory()!=null) fb.setCategory(req.getCategory());
        if(req.getRating()!=null) fb.setRating(req.getRating());
        if(req.getComment()!=null) fb.setComment(req.getComment());
        if(req.getStatus()!=null) fb.setStatus(req.getStatus());
        return repo.save(fb);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        repo.deleteById(id);
    }

    @GetMapping("/analytics")
    public FeedbackAnalyticsResponse analytics(){
        List<Feedback> all = repo.findAll();
        int total = all.size();
        double average = total==0 ? 0.0 : all.stream().mapToDouble(f->Optional.ofNullable(f.getRating()).orElse(0.0)).average().orElse(0.0);
        Map<String, Long> byType = new HashMap<>();
        all.forEach(f -> byType.merge(Optional.ofNullable(f.getType()).orElse("unknown"), 1L, Long::sum));

        Map<String, Long> ratingDist = new LinkedHashMap<>();
        ratingDist.put("5.0", all.stream().filter(f->Objects.equals(f.getRating(),5.0)).count());
        ratingDist.put("4.0-4.9", all.stream().filter(f->{ Double r=f.getRating(); return r!=null && r>=4.0 && r<5.0;}).count());
        ratingDist.put("3.0-3.9", all.stream().filter(f->{ Double r=f.getRating(); return r!=null && r>=3.0 && r<4.0;}).count());
        ratingDist.put("2.0-2.9", all.stream().filter(f->{ Double r=f.getRating(); return r!=null && r>=2.0 && r<3.0;}).count());
        ratingDist.put("1.0-1.9", all.stream().filter(f->{ Double r=f.getRating(); return r!=null && r>=1.0 && r<2.0;}).count());

        FeedbackAnalyticsResponse res = new FeedbackAnalyticsResponse();
        res.setTotalFeedback(total);
        res.setAverageRating(Math.round(average*10.0)/10.0);
        res.setFeedbackByType(byType);
        res.setRatingDistribution(ratingDist);
        return res;
    }
}
