package org.zeock.myapp.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zeock.myapp.domain.PersonDto;

import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

@Slf4j
@NoArgsConstructor

@RequestMapping("/syntax/*")
@Controller
public class SyntaxController {

    @GetMapping("/base")
    String base(PersonDto dto, Model model){
        log.debug("base() invoked.");

        model.addAttribute("message", "Tae");
        model.addAttribute("message2", "<h1>Tae</h1>");
        model.addAttribute("person", dto);
        model.addAttribute("isMale", true);

//        model.addAttribute("role", "admin");
//        model.addAttribute("role", "member");
        model.addAttribute("role", "ceo");

        List<PersonDto> list = new Vector<>();

        IntStream.rangeClosed(1,7).forEachOrdered(seq -> {
            PersonDto person = new PersonDto();
            person.setName("NAME-"+seq);
            person.setAge(23+seq);

            list.add(person);
        });

        model.addAttribute("persons", list);

        model.addAttribute("namePlaceHolder", "Enter your name in english..");
        model.addAttribute("agePlaceHolder", "Enter your age as number");
        model.addAttribute("imgPath", "https://picsum.photos/id/684/600/400");
        model.addAttribute("style1", "font-size:40px");
        model.addAttribute("class3", "color:purple");

        return "base"; // view 이름
    }

    @GetMapping("builtin")
    String builtin(){
        log.debug("builtin() invoked.");

        return "builtin";
    }

}// end class
