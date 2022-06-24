package com.example.filrouge_loubna.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Reviews")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "reviews")
    private String review;

    @Column(name = "score")
    private float score;

    @UpdateTimestamp
    @Column(name = "DateTime")
    private LocalDateTime DateTime;

    private Long userId;


    private Long medicalOfficeId;



}
