package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


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

        // TODO: Add a list of comments

    }

