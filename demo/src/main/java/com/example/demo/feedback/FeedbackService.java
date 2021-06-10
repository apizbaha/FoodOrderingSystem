package com.example.demo.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    public Feedback getFeedback(Long id){
        return feedbackRepository.findById(id).orElse(
                null
        );
    }

    public void addFeedback(Feedback feedback){
        feedbackRepository.save(feedback);
        System.out.println("Feedback Comment: "+ feedback.getComment() );
    }
}
