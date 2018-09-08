package org.ty.cloudCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ty.cloudCourse.dao.MessageDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Message;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.entity.StudentInfo;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.service.MessageService;

import java.util.Date;

/**
 * @author kangtaiyang
 * @date 2018/6/23
 */
@Controller
@RequestMapping("mess")
public class MessageController extends BaseController {

    @Autowired
    private CrudService crudService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value = "cMess", method = RequestMethod.POST)
    private String cMess(Message message, Integer senderId, Integer addrerId, Model model) {
        message.setSendTime(sdf.format(new Date()));
        message.setEnableStatus(1);
        message.setSender(new PersonInfo(senderId));
        message.setAddrer(new StudentInfo(addrerId));
        AllExecution execution = crudService.insertOne(message, messageDao);
        return execution.getState() == 1 ? "success" : "error";
    }

    @RequestMapping(value = "rdMess", method = RequestMethod.GET)
    private String rdMess(Integer messageId, Integer actionType, Model model) {
        AllExecution execution = getExecutionByActionType(new Message(messageId), actionType, messageDao, model);
        return execution.getState() == 1 ? actionType == 2 ? "message/message_show" : "success" : "error";
    }

    @RequestMapping(value = "queryMessByAddrer", method = RequestMethod.GET)
    private String queryMessByAddrer(Integer addrerId, Model model) {
        AllExecution execution = messageService.queryMessageByAddrer(new StudentInfo(addrerId));
        model.addAttribute("msg", execution.getAList());
        if (execution.getState() == 1) return "message/message_list";
        else return "error";
    }

    @RequestMapping(value = "queryMessageBySender", method = RequestMethod.GET)
    private String queryMessageBySender(Integer senderId, Model model) {
        AllExecution execution = messageService.queryMessageBySender(new PersonInfo(senderId));
        model.addAttribute("msg", execution.getAList());
        if (execution.getState() == 1) return "message/message_list";
        else return "error";
    }
}
