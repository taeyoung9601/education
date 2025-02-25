package org.zerock.myapp.persistence;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;

import java.util.List;

public interface BoardRepository {     // DAO : Data Access Object
    public abstract List<BoardVO> selectList();
    public abstract Integer insert(BoardDTO dto);
    public abstract Integer update(BoardDTO dto);
    public abstract Integer delete(BoardDTO dto);
    public abstract BoardVO select(BoardDTO dto);


}// end interface
