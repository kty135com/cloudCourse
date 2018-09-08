package org.ty.cloudCourse.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by kangtaiyang on 2018/6/4.
 */
public class ImageUtil {
    //从类加载器获取当前线程运行时候代码文件所在的位置,即是classPath
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    //定义日期格式
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random r = new Random();

    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 处理缩略图，并返回新生成图片的相对值路径
     *
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {
        //文件名
        String realFileName = getRandomFileName();
        //文件后缀
        String extension = getFileExtension(thumbnail);
        //路径不存在则创建文件夹
        makeDirPath(targetAddr);
        //相对路径+文件名+后缀
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        //生成文件（相对路径+文件名）
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        //图片处理
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            System.out.println(dest);
            Thumbnails.of(thumbnail).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")), 0.8f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param cFile
     * @return
     */
    private static String getFileExtension(File cFile) {
        String originalFileName = cFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     *
     * @return
     */
    private static String getRandomFileName() {
        //获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 将CommonsMultipartFile类转换为File类
     *
     * @param commonsMultipartFile
     * @return
     */
    public static File transferCommonsMutipartFileToFile(CommonsMultipartFile commonsMultipartFile) {
        //用cFile的文件名创建新文件
        File newFile = new File(commonsMultipartFile.getOriginalFilename());
        try {
            //将cFile中的文件流写入新创建的文件里
            commonsMultipartFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    public static void main(String[] args) throws IOException {
        /**
         Thumbnails.of(new File("path/to/directory").listFiles())
         .size(640, 480)
         .outputFormat("jpg")
         .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
         */
        Thumbnails.of(new File("/Users/kangtaiyang/壁纸/北极星/123.jpg")) //输入文件
                .size(200, 200)  //大小
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)   //水印，三个参数，分别是水印位置，图片io流，透明度
                .outputQuality(0.8f)     //压缩度
                .toFile("/Users/kangtaiyang/壁纸/北极星/北极星小姐姐.jpg"); //输出文件
    }
}
