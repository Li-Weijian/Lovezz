package com.lovezz.controller;


import com.lovezz.annotation.OperationEmailDetail;
import com.lovezz.constant.MsgCommon;
import com.lovezz.constant.OperationModule;
import com.lovezz.dto.BaseResult;
import com.lovezz.entity.TbNote;
import com.lovezz.entity.TbUser;
import com.lovezz.exception.CommonException;
import com.lovezz.service.TbNoteService;
import com.lovezz.service.TbUserService;
import com.lovezz.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
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
@Slf4j
public class NoteController {

    @Autowired
    private TbNoteService noteService;

    @Autowired
    private TbUserService userService;

    @RequestMapping("/toNote")
    public ModelAndView toNote(ModelAndView modelAndView, @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit){
        TbUser user = new RequestUtils().getLoginUser();
        modelAndView.addObject("contentList", noteService.getNoteList(offset,limit, Arrays.asList(user.getId(), user.getHelfId())));
        modelAndView.setViewName("note/ceshi");
        return modelAndView;
    }

    @RequestMapping("/getNoteList")
    @ResponseBody
    public List<TbNote> getNoteList(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
        Integer userId = new RequestUtils().getLoginUserId();
        try {
            log.info("进入 约定列表 :{}", userId);
            return noteService.getNoteList(offset, limit,userService.selectAllIds(userId));
        } catch (CommonException e) {
            log.error("【获取约定列表】: {}", e);
            return null;
        }
    }

    @RequestMapping("/savaContent")
    @ResponseBody
    @OperationEmailDetail(content = "新添加了一条【小约定】啦，快打开App查看吧", operationClass = OperationModule.NOTE)
    public Boolean savaOrUpdateContent(TbNote note){

        //更新
        if (note != null && note.getId() != null){
            return noteService.updateById(note);

        }else{
            note.setUserId(new RequestUtils().getLoginUserId());
            note.setDate(new Date());
            return noteService.insert(note);
        }
    }

    @GetMapping("/selectNote/{id}")
    @ResponseBody
    public BaseResult selectNote(@PathVariable("id") Integer id){
        return BaseResult.success(MsgCommon.SUCCESS.getMessage(), noteService.selectById(id));
    }

}

