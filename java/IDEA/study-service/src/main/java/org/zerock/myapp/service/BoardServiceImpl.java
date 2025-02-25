package org.zerock.myapp.service;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.persistence.BoardRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

@Slf4j
@NoArgsConstructor

// 아래 어노테이션을 통해서, 이 클래스가 비지니스 계층의 서비스에 해당하고(역할 부여)
// 자동으로 Spring Context 에 빈으로 등록(스테레오타입 어노테이션의 기본기능)
@Service
public class BoardServiceImpl implements  BoardService{
    @Autowired private BoardRepository dao;

    @PostConstruct
    void postConstruct(){
        log.debug("postConstruct() invoked.");
        log.info("\t + this.dao:{} ", dao);
    }

    @Override
    public List<BoardVO> getBoardList() {
        log. debug("getBoardList() invoked.");

        return this.dao.selectList();
    }// getBoardList

    @Override
    public Boolean create(BoardDTO dto) {
        log.debug("create({}) invoked.", dto);

        int affectedRows = this.dao.insert(dto);
        return affectedRows == 1;
    }// create

    @Override
    public BoardVO findById(BoardDTO dto) {
        log.debug("findById({}) invoked.", dto);

        return this.dao.select(dto);
    }// findById

    @Override
    public Boolean modify(BoardDTO dto) {
        log.debug("modify({}) invoked.", dto);

        int affectedRows = this.dao.update(dto);
        return affectedRows == 1;
    }// modify

    @Override
    public Boolean removeById(BoardDTO dto) {
        log.debug("removeById({}) invoked.", dto);

        return this.dao.delete(dto)==1;
    }// removeById

}// end class
