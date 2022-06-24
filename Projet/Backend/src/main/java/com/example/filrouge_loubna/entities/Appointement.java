package com.example.filrouge_loubna.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "Appointement")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointement  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "DateTimeAppointement")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime DateTimeAppointement;

    @Column(name = "acceptanceMode")
    private String acceptanceMode;
    @Nullable()

    @UpdateTimestamp
    @Column(name = "acceptanceDate")
    private LocalDateTime acceptanceDate;

    @Nullable()
    @UpdateTimestamp
    @Column(name = "validationDate")
    private LocalDateTime validationDate;

    @Column(name = "validationMode")
    private String validationMode;



    private Long userId;

    private Long medicalOfficeId;

    @OneToOne(mappedBy = "appointement")
    private Payment payment;


   }
