package com.yoon.guestbook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")//toString은 항상 exclude
public class Board extends  BaseEntity{ // Board 테이블은 Member테이블을 참조하는 테이블이다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  //Lazy 로딩 적용
    private Member writer; //연관관계 지정
}
