package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

@Slf4j
@NoArgsConstructor

@Repository     // means DAO ( Data Access Object)
public class BoardRepositoryImpl implements BoardRepository{



    @Override
    public List<BoardVO> selectList() {
        log.debug("selectList() invoked.");

        List<BoardVO> list = new Vector<>();

        IntStream.rangeClosed(1,30).forEach(seq -> {
            BoardVO vo = new BoardVO(seq, "TITLE-"+seq, "CONTENT-"+seq, "TAE", new Date(), 0);
            list.add(vo);
        }); // forEach

        list.forEach(vo -> log.info(vo.toString()));

        return list;
    }// selectList

    @Override
    public Integer insert(BoardDTO dto) {
        log.debug("insert({}) invoked.", dto);
        return 1;
    }// insert

    @Override
    public Integer update(BoardDTO dto) {
        log.debug("update({}) invoked.", dto);
        return 1;
    }// update

    @Override
    public Integer delete(BoardDTO dto) {
        log.debug("delete({}) invoked.", dto);

        return 1;
    }// delete

    @Override
    public BoardVO select(BoardDTO dto) {
        log.debug("select({}) invoked.", dto);

        BoardVO vo = new BoardVO(dto.getSeq(), "TITLE-"+dto.getSeq(), "CONTENT-"+dto.getSeq(), "TAE",
                dto.getCreateDate(), dto.getCnt());

        return vo;
    }// select

}// end class
