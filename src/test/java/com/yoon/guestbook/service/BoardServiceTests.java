package com.yoon.guestbook.service;

import com.yoon.guestbook.dto.BoardDTO;
import com.yoon.guestbook.dto.PageRequestDTO;
import com.yoon.guestbook.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void registerTest(){

        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user55@aaa.com")
                .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void listTest(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
        for(BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    @Test
    public void getTest(){
        Long bno = 1L;
        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO);
    }

    @Test
    public void removeTest(){
        Long bno = 1L;
        boardService.removeWithReplies(bno);
    }

    @Test
    public void modifyTest(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경합니다")
                .content("내용 변경합니다.")
                .build();

        boardService.modify(boardDTO);
    }


}
