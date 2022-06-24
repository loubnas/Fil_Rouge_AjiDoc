package com.example.filrouge_loubna.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "MedicalOfficeStaff")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalOfficeStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
    private User medicalOfficeStaffUser;

    private Long medicalOfficeId;
}
