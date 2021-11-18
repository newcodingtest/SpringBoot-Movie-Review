package com.yoon.ex1.repository;

import com.yoon.ex1.entity.Memo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void insertTest(){
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void insertDummyTest(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Memo memo = Memo.builder().memoText("Memo..."+i).build();

            memoRepository.save(memo);
        });
    }

    @Test
    public void updateTest(){
        Memo memo = Memo.builder().mno(100L).memoText("UPDATE....1").build();
        System.out.println(" ==========1 ");
        memoRepository.save(memo);
        System.out.println(" ==========2 ");
    }

    @Test
    public void deleteTest(){

        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void pageDefaultTest(){

        Pageable pageable = PageRequest.of(0,10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

        System.out.println(" ============================ ");

        System.out.println("총 몇 페이지? " + result.getTotalPages());

        System.out.println("전체 개수  " + result.getTotalElements());

        System.out.println("현재 페이지 번호 0 부터 시작" + result.getNumber());

        System.out.println("페이지 당 데이터 개수 " + result.getSize());

        System.out.println("다음 페이지 존재 여부 " + result.hasNext());

        System.out.println("시작 페이지 여부 " + result.isFirst());
    }

    @Test
    public void pagingTest(){

        Pageable pageable = PageRequest.of(0,10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("==============================");
        for (Memo memo : result.getContent()){
            System.out.println(memo);
        }
    }

    @Test
    public void sortTest(){

        Sort sort = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0,10, sort);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void queryMethodsTest(){

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L,80L);

        for(Memo memo : list){
            System.out.println(memo);
        }
    }

    @Test
    public void queryMethodWithPageableTest(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }

    @Test
    @Transactional
    @Commit
    public void deleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }
}
