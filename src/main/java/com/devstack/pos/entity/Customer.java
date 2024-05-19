package com.devstack.pos.entity;


import com.devstack.pos.entity.process.FileResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(unique = true,nullable = false)
    private long publicId;

    private String name;

    private String address;

    private double salary;

    @Column(columnDefinition = "TINYINT")
    private boolean activeState;


    @Embedded
    private FileResource fileResource;

}
