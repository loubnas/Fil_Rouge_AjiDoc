package com.example.filrouge_loubna.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "UserExtraInfo")
@AllArgsConstructor
@NoArgsConstructor

public class UserExtraInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @Column(name = "specialite")
    private String specialite;

    @Column(name = "diplome")
    private String diplome;
    @Column(name = "description")
    private String description;

    @Column(name = "specialiteImage")
    private String specialiteImage;

    @Column(name = "diplomeImage")
    private String diplomeImage;

    @Column(name = "cinImage")
    private String cinImage;







}
