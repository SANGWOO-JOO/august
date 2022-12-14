package com.springboot.sbb.question;

import java.util.List;

import com.springboot.sbb.answer.AnswerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;


@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

//    private final QuestionRepository questionRepository;
      private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model) {
//      List<Question> questionList = this.questionRepository.findAll();
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        //        if (bindingResult.hasErrors()) {
        //            return "question_form";
        //        }
        //        this.questionService.create(questionForm.getSubject(), questionForm.getContent());

        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@RequestParam String subject, @RequestParam String content) {
        this.questionService.create(subject, content);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}