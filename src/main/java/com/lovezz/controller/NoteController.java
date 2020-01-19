package com.lovezz.controller;


import com.lovezz.annotation.OperationEmailDetail;
import com.lovezz.constant.OperationModule;
import com.lovezz.entity.TbNote;
import com.lovezz.service.TbNoteService;
import com.lovezz.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liweijian123
 * @since 2019-10-09
 */
@Controller
@RequestMapping("/noteController")
@CrossOrigin("*")
public class NoteController {

    @Autowired
    private TbNoteService noteService;

    @RequestMapping("/toNote")
    public ModelAndView toNote(ModelAndView modelAndView, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit){
        modelAndView.addObject("contentList", noteService.getNoteList(offset,limit));
        modelAndView.setViewName("note/ceshi");
        return modelAndView;
    }

    @RequestMapping("/getNoteList")
    @ResponseBody
    public List<TbNote> getNoteList(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
        return noteService.getNoteList(offset, limit);
    }

    @RequestMapping("/savaContent")
    @ResponseBody
    @OperationEmailDetail(content = "新添加了一条【小约定】啦，快打开App查看吧", operationClass = OperationModule.NOTE)
    public Boolean savaOrUpdateContent(TbNote note){
        note.setUserId(new RequestUtils().getLoginUserId());

        //更新
        if (note != null && note.getId() != null){
            return noteService.updateById(note);

        }else{
            note.setDate(new Date());
            return noteService.insert(note);
        }
    }

}

