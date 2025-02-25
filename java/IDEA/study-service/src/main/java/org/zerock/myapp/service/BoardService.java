package org.zerock.myapp.service;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;

import java.util.List;

public interface BoardService {

    public abstract List<BoardVO> getBoardList();   // List

    public abstract Boolean create(BoardDTO dto);          // CREATE
    public abstract BoardVO findById(BoardDTO dto);        // READ
    public abstract Boolean modify(BoardDTO dto);          // UPDATE
    public abstract Boolean removeById(BoardDTO dto);      // DELETE

}// end interface
