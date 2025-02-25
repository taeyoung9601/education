package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor

@RequestMapping("/board/*")

@Controller
public class BoardController implements InitializingBean {
//    @Autowired
    @Setter(onMethod_ = @Autowired(required = false))   // Optional
    private BoardService service;

    @Override
    public void afterPropertiesSet() throws Exception { // 1회성 전처리
        log.debug("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.service);
        log.info("\t + this.service: {}", this.service);

    }// afterPropertiesSet

    @GetMapping(path = "/getBoardList")
    String getBoardList(Model model){
        log.debug("getBoardList({}) invoked.", model);

        // Execute Business Logic with Service Object in Business Layer.
        // And Return Models (Business Data)
        List<BoardVO> list = this.service.getBoardList();      // Model

        //Request Scope's Shared Attributes
        model.addAttribute("_BOARD_LIST_",list);

        return "board/boardList";
    }// getBoardList

    @PostMapping(path = "/createBoard", params ={"title","content","writer"})
    String createBoard(BoardDTO dto, Model model){          // dto: Spring Command Object
        log.debug("createBoard({},{}) invoked.", dto,model);

        Boolean isSucceed = this.service.create(dto);
        model.addAttribute("isRegistered", isSucceed);

        return "redirect:getBoardList";
    } // createBoard

    @GetMapping(value = "/removeBoard", params = "seq")
    String removeBoard(BoardDTO dto, RedirectAttributes rttrs){
        log.debug("removeBoard() invoked.");

        Boolean isSucceed = this.service.removeById(dto);   // Model
        rttrs.addAttribute("isRemoved", isSucceed);         // Transfer shared attributes to the View

        return "redirect:getBoardList";
    }// removeBoard

    @GetMapping(value = "/findBoard", params= "seq")
    String findBoard(BoardDTO dto, Model model){
        log.debug("findBoard({},{}) invoked.", dto, model);

        BoardVO vo = this.service.findById(dto);
        log.info("\t + vo: {}", vo);

        model.addAttribute("_BOARD_VO_",vo);

        return "board/findBoard";       // View Name
    }// findBoard

    @GetMapping(value = "/modifyBoard")
    String modifyBoard(BoardDTO dto, RedirectAttributes rttrs){
        log.debug("modifyBoard({},{}) invoked.", dto, rttrs);

        Boolean isSucceed = this.service.modify(dto);
        rttrs.addAttribute("isModified", isSucceed);

        return "redirect:getBoardList";
    }// modifyBoard

}// end class
