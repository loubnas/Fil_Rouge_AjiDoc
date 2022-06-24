package com.example.filrouge_loubna.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "MedicalOfficeOnlinePaymentInfo")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OnlinePaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "paypal_email")
    private String paypal_email;

    @Column(name = "paypal_APIkey")
    private String paypal_APIkey;

    @OneToOne(fetch=FetchType.LAZY)
    private MedicalOffice medicalOffice;

}
