package org.ty.cloudCourse.controller;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ty.cloudCourse.dao.*;
import org.ty.cloudCourse.dto.AllExecution;
import org.ty.cloudCourse.service.CrudService;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author kangtaiyang
 * @date 2018/6/21
 */
@Controller
@RequestMapping("base")
public class BaseController {
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private CrudService crudService;

    /**
     * 退出账号
     *
     * @param session
     * @return
     */
    protected String exit(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userName");
        return "login";
    }

    @RequestMapping(value = "checkCode", method = RequestMethod.GET)
    private String checkCode(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        Random random = new Random();
        response.setContentType("image/jpeg");
        int width = 70, height = 20;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(getRandColor(200, 255));
        g.fillRect(0, 0, width, height);

        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String strRandom = "";
        String generateRandom = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
        for (int i = 0; i < 4; i++) {
            int intRandom = random.nextInt(55);
            String numRandom = generateRandom.charAt(intRandom) + "";
            strRandom += numRandom;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.setFont(new Font("Time NewRoman", Font.BOLD, 14 + random.nextInt(6)));
            g.drawString(numRandom, 6 + 13 * i, 16);
        }
        session.setAttribute("checkcode", strRandom);
        g.dispose();
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
            encoder.encode(bi);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bi = null;
        return null;
    }

    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    protected <A> AllExecution getExecutionByActionType(A a, Integer actionType, BaseDao dao) {
        AllExecution execution = null;
        if (actionType == 0) execution = crudService.insertOne(a, dao);
        if (actionType == 1) execution = crudService.updateOne(a, dao);
        if (actionType == 2) execution = crudService.getOneById(a, dao);
        if (actionType == 3) execution = crudService.deleteOne(a, dao);
        System.out.println(execution);
        return execution;
    }

    protected <A> AllExecution getExecutionByActionType(A a, Integer actionType, BaseDao dao, Model model) {
        AllExecution execution = null;
        System.out.println(a + "  actionType=" + actionType);
        if (actionType == 0) execution = crudService.insertOne(a, dao);
        if (actionType == 1) execution = crudService.updateOne(a, dao);
        if (actionType == 2) execution = crudService.getOneById(a, dao);
        if (actionType == 3) execution = crudService.deleteOne(a, dao);
        System.out.println(execution);
        model.addAttribute("msg", execution.getStateInfo());
        return execution;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        URL classpath = BaseController.class.getClassLoader().getResource("");
        Document document = db.parse(classpath.getPath() + "user.xml");
        NodeList nodeList = document.getElementsByTagName("users");
        System.out.println(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getNodeName());
            NamedNodeMap nodeMap = node.getAttributes();
            for (int j = 0; j < nodeMap.getLength(); j++) {
                Node node2 = nodeMap.item(j);
                System.out.println(node2);
                System.out.print("    ");
                System.out.println(node2.getNodeName());
            }
        }
    }
}
