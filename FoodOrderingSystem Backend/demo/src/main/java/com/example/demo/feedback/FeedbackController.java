package com.example.demo.feedback;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(path = "api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService service){
        feedbackService = service;
    }

    @GetMapping("/all")
    public List<Feedback> getAllFeedback(){
        return feedbackService.getAllFeedback();
    }

    @GetMapping("{id}")
    public Feedback getFeedback(@PathVariable("id") Long id){
        return feedbackService.getFeedback(id);
    }

    @PostMapping(path = "/add")
    public void addFeedback(@RequestBody Feedback feedback){
        feedbackService.addFeedback(feedback);
    }
}
