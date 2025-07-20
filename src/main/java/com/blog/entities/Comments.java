package com.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="comments")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comments {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="comment_id")
private Long id;

@Column(name="content")
private String content;

@ManyToOne
@JoinColumn(name="post_id")
private Post post;

}
