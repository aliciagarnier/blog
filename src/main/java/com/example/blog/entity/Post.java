package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(schema = "blog", name = "post")
public class Post implements Serializable {

        @Id
        private Integer id;

        private String title;

        private String body;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @OneToMany(mappedBy = "post")
        private List<Comment> comments;


    }

