package com.example.filrouge_loubna.entities;

import com.example.filrouge_loubna.serializers.UserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "adress")
    private String adress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "ville")
    private String ville;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    private User validatedBy;

    @UpdateTimestamp
    @Column(name = "validatedDate")
    private LocalDateTime validateDate;

    @Column(name = "type")
    private String type;

    @Column(name="image")
    private String image;


    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Column(name = "documents")
    private List<UserDocument> userDocumentList;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private UserExtraInfo userExtraInfo;

    @OneToMany(mappedBy = "administrator",fetch = FetchType.LAZY)
    private List<MedicalOffice> medicalOffices;

    @OneToOne(fetch=FetchType.LAZY)
    private MedicalOfficeStaff medicalOfficeStaff;

    @OneToMany(mappedBy="userId",cascade = CascadeType.ALL)
    private List<Review> reviewsList;


    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<Appointement> appointementUserList;
}
