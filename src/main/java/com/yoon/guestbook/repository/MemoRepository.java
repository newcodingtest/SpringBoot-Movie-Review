package com.yoon.guestbook.repository;

import com.yoon.guestbook.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    
    //Memo 객체의 mno 기준 사이값 구하기
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
    //Memo 객체의 mno 기준 사이값 구하기 정렬
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
    //파라미터 mno보다 작은 값들 삭제
    void deleteMemoByMnoLessThan(Long num);


}
