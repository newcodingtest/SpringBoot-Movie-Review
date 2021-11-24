package com.yoon.guestbook.repository;

import com.yoon.guestbook.entity.Board;
import com.yoon.guestbook.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
