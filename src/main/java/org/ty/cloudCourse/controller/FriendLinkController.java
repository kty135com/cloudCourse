package org.ty.cloudCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ty.cloudCourse.dao.FriendLinkDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.FriendLink;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.CrudService;
import org.ty.cloudCourse.util.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/7/18
 */
@Controller
@RequestMapping("friendlk")
public class FriendLinkController extends BaseController {

    @Autowired
    private FriendLinkDao friendLinkDao;
    @Autowired
    private CrudService crudService;

    @RequestMapping(value = "frlist", method = RequestMethod.GET)
    private String list(Model model) {
        List<FriendLink> friendLinkList = friendLinkDao.queryFriendLink();
        model.addAttribute("flklist", friendLinkList);
        return "friendlk/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    private String add(FriendLink friendLink, Model model) {
        friendLink.setClickCount(0);
        AllExecution execution = crudService.insertOne(friendLink, friendLinkDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    @RequestMapping(value = "del", method = RequestMethod.GET)
    private String del(int linkId, Model model) {
        AllExecution execution = crudService.deleteOne(new FriendLink(linkId), friendLinkDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }

    @RequestMapping(value = "updt", method = RequestMethod.GET)
    private String updt(Integer linkId, Model model) {
        AllExecution execution = crudService.getOneById(new FriendLink(linkId), friendLinkDao);
        if (execution.getState() == 1) {
            model.addAttribute("flk", execution.getA());
            return "friendlk/update";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    private String update(FriendLink friendLink, Model model) {
        AllExecution execution = crudService.updateOne(friendLink, friendLinkDao);
        model.addAttribute("msg", execution.getStateInfo());
        return execution.getState() == 1 ? "success" : "error";
    }
}
