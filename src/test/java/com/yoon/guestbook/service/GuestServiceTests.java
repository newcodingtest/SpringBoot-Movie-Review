package com.yoon.guestbook.service;

import com.yoon.guestbook.dto.GuestbookDTO;
import com.yoon.guestbook.dto.PageRequestDTO;
import com.yoon.guestbook.dto.PageResultDTO;
import com.yoon.guestbook.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class GuestServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    public void registerTest(){

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void listTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

      /*  for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }*/

        System.out.println(resultDTO);
    }

    @Test
    public void searchTest(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("한글")
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println(resultDTO.isPrev());
        System.out.println(resultDTO.isNext());
        System.out.println(resultDTO.getTotalPage());

        System.out.println(" ======================= ");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }

        System.out.println(" ====================== ");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }

}
