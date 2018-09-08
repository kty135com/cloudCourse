package org.ty.cloudCourse.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kangtaiyang on 2018/6/2.
 */
public class PathUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static String seperator = System.getProperty("file.separator"); //系统分隔符

    /**
     * 根据操作系统类型获取图片基类路径
     *
     * @return
     */
    public static String getImgBasePath() {

        String os = System.getProperty("os.name");  //获取系统名
        System.out.println(os);
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {    //如果系统名带有"win"字样
            basePath = "C://cloud_course/image/";
        }else{
            basePath = "/etc/cloud_course/image/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    /**
     * 获取后端用户头像相对路径
     *
     * @param userId
     * @return
     */
    public static String getHeadImgPath(Integer userId) {
        String imagePath = "headImg/" + userId + "/"+sdf.format(new Date())+"/";
        return imagePath.replace("/", seperator);
    }

    /**
     * 获取学生头像相对路径
     *
     * @param studentId
     * @return
     */
    public static String getStudentHeadImgPath(Integer studentId) {
        String imagePath = "studentHeadImg/" + studentId + "/"+sdf.format(new Date())+"/";
        return imagePath.replace("/", seperator);
    }

    /**
     * 获取课程头像相对路径
     *
     * @param courseId
     * @return
     */
    public static String getCoursePath(Integer courseId) {
        String imagePath = "courseImg/" + courseId + "/"+sdf.format(new Date())+"/";
        return imagePath.replace("/", seperator);
    }
}
