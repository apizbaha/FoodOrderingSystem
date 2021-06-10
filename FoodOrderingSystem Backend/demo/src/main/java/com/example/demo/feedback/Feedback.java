package com.example.demo.feedback;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String comment;

    public Feedback() {
    }

    public Feedback(Long id,  String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Feedback(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
