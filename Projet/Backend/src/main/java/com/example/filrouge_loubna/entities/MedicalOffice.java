package com.example.filrouge_loubna.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "MedicalOffice")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalOffice  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "adress")
    private String adress;

    @Column(name="image")
    private String image;


    @ManyToOne(fetch=FetchType.LAZY)
    private User administrator;

    @OneToMany(mappedBy = "medicalOfficeId",cascade = CascadeType.ALL)
    private List<MedicalOfficeStaff> medicalOfficeStaffList=new ArrayList<>();


    @OneToOne(fetch=FetchType.LAZY)
    private OnlinePaymentInfo OnlinePaymentInfo;

    @OneToMany(mappedBy = "medicalOfficeId",cascade = CascadeType.ALL)
    private List<Review> reviewsMedicalOfficeList;

}
