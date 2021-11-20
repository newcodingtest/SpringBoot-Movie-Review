package com.yoon.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.yoon.guestbook.entity.Guestbook;
import com.yoon.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummiesTest(){

        IntStream.rangeClosed(1,300).forEach(i -> {

            Guestbook guestbook = Guestbook.builder()
                    .title("Title..."+i)
                    .content("Content..."+i)
                    .writer("User..."+(i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest(){

        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){

            Guestbook guestbook = result.get();

            guestbook.changeTitle("Change Title..12");
            guestbook.changeContent("Change Content.1212.");

            guestbookRepository.save(guestbook);
        }
    }

    //querydsl 검색 테스트
    @Test
    public void query1Test(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;//1: q도메인 클래스를 이용하여 엔티티 클래스에 선언된 title,content 같은 필드변수 활용가능

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();//2: where 문에 들어가는 조건들을 넣어주는 컨테이너

        BooleanExpression expression = qGuestbook.title.contains(keyword);//3:원하는 조건은 필드값과 결합해서 생성됨

        builder.and(expression);//4: 만들어진 조건은 where 문에 and 나 or 같은 키워드와 결합된다.

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);//5: BooleanBuilder는 GuestbookRepository에 추가된 QuerydslPredicateExcutor 인터페이스의 findAll(0을 사용할수있읍

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void queryTest2(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L)); //gno가 0보다 크다

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
        

    }

}
