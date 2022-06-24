package com.example.filrouge_loubna.entities;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Payment")
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "appointement_id")
    @MapsId
    private Appointement appointement;

    private boolean isOnline;
    private String onlineReference;
}
