package com.saurabh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "program_date")
    private LocalDate playDate;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "duration")
    private int duration;

    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "radio_station_id")
//    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RadioStation broadcastedOn;

    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnoreProperties("program")
    @JsonIgnore
    private List<RadioJockey> hostedByid;
    @OneToMany(mappedBy = "playedDuring", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Advertisement> advertisements;


    public void addJockey(RadioJockey radioJockey) {
        if (hostedByid == null) {
            hostedByid = new ArrayList<RadioJockey>();
        }
        hostedByid.add(radioJockey);
        radioJockey.setProgram(this);
    }

    public void addAdd(Advertisement advertisement) {
        if (advertisements == null) {
            advertisements = new ArrayList<Advertisement>();
        }
        advertisements.add(advertisement);
        advertisement.setPlayedDuring(this);
    }
}
