package org.ty.cloudCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ty.cloudCourse.dao.ColumnDao;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.entity.Column;
import org.ty.cloudCourse.entity.PersonInfo;
import org.ty.cloudCourse.service.CrudService;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * @author kangtaiyang
 * @date 2018/6/22
 */
@Controller
@RequestMapping("column")
public class ColumnController extends BaseController {
    @Autowired
    private CrudService crudService;
    @Autowired
    private ColumnDao columnDao;

    /**
     * 新建/修改栏目
     *
     * @param column
     * @param actionType(0添加,1修改)
     * @param model
     * @return
     */
    @RequestMapping(value = "cuColumn", method = RequestMethod.POST)
    private String cuColumn(Column column, Integer schoolId, Integer parentId, Integer actionType, Model model) {
        if (parentId != null) column.setParentColumn(new Column(parentId));
        if (schoolId != null) column.setPersonInfo(new PersonInfo(schoolId));
        AllExecution execution = getExecutionByActionType(column, actionType, columnDao, model);
        return execution.getState() == 1 ? "success" : "error";
    }

    /**
     * 查看/删除栏目
     *
     * @param columnId
     * @param actionType(2查看,3删除)
     * @param model
     * @return
     */
    @RequestMapping(value = "rdColumn", method = RequestMethod.GET)
    private String rdColumn(Integer columnId, Integer actionType, Model model) {
        AllExecution execution = getExecutionByActionType(new Column(columnId), actionType, columnDao, model);
        if (execution.getState() == 1) {
            if (actionType == 3) return "success";
            model.addAttribute("column", execution.getA());
            return "column/column_show";
        } else return "error";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    private String list(String url, HttpSession session, Model model) {
        List<Column> columnList = columnDao.queryAllColumnBySchool(new PersonInfo(Integer.parseInt("" + session.getAttribute("userId"))));
        model.addAttribute("columnList", columnList);
        List<Column> columnList2 = columnDao.queryAllColumnBySchool(new PersonInfo(Integer.parseInt(""+session.getAttribute("userId"))));
        model.addAttribute("columnList2",columnList2);
        return url;
    }

    @RequestMapping(value = "updt", method = RequestMethod.GET)
    private String updt(Integer columnId,HttpSession session, Model model) {
        Column column = columnDao.getOneById(new Column(columnId));
        model.addAttribute("column", column);
        List<Column> columnList = columnDao.queryAllColumnBySchool(new PersonInfo(Integer.parseInt(""+session.getAttribute("userId"))));
        Iterator iter = columnList.iterator();
        while (iter.hasNext()){
            Column c = (Column) iter.next();
            if (c.getColumnId()==columnId){
                iter.remove();
            }
        }
        model.addAttribute("columnList",columnList);
        return "column/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    private String update(Column column, Integer parentId, Model model) {
        column.setParentColumn(new Column(parentId));
        int i = columnDao.updateOne(column);
        return i == 0 ? "error" : "success";
    }
}
