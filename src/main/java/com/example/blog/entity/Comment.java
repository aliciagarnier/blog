package com.example.blog.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(schema = "blog", name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String body;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
