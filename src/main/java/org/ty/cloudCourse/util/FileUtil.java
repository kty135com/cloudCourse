package org.ty.cloudCourse.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * Created by kangtaiyang on 2018/6/5.
 */
public class FileUtil {

    public static final String[] imgFormat = new String[]{".jpg", ".png", ".bmp", ".gif", ".jpeg"};

    /**
     * 传入文件和虚拟文件，进行文件的上传
     *
     * @param multipartFile
     * @param path
     * @throws IOException
     */
    public static void fileUpload(MultipartFile multipartFile, File path) throws IOException {
        if (!(multipartFile.getSize() > 0)) {
            throw new NullPointerException("上传文件的文件为空，请检查");
        }
        if (!path.isDirectory()) {
            System.out.println(path);
            path.setWritable(true);
            path.setExecutable(true);
            path.setReadable(true);
            Boolean bool = path.mkdirs();
            System.out.println(bool);
        }
        multipartFile.transferTo(new File(path, multipartFile.getOriginalFilename()));
    }

    /**
     * 传入完成文件路径+文件名格式来下载文件
     *
     * @param pathName
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> fileDownload(File pathName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(pathName.toString().getBytes("UTF-8"), "iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(pathName),
                headers, HttpStatus.CREATED);
    }

    /**
     * NIO
     *
     * @param path
     * @param fileName
     * @param path2
     * @param fileName2
     * @return
     * @throws IOException
     */
    public static String fileRead(File path, File fileName, File path2, File fileName2) throws IOException {
//        if (!path.isDirectory()) path.mkdirs();
//        if (!path2.isDirectory()) path2.mkdirs();
//        InputStream is = new FileInputStream(new File(path.toString(), fileName.toString()));
//        OutputStream os = new FileOutputStream(new File(path2.toString(), fileName2.toString()));
//        Channel channel = ((FileInputStream) is).getChannel();
//        Channel channel2 = ((FileOutputStream) os).getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(50);
//        ((FileChannel) channel).(buffer);
//        ((FileChannel) channel2).
//        channel.close();
//        channel2.close();
        return null;
    }

    /**
     * IO
     *
     * @param path
     * @param fileName
     * @param path2
     * @param fileName2
     * @return
     * @throws IOException
     */
    public static String fileRead2(String path, String fileName, String path2, String fileName2) throws IOException, InterruptedException {
        Reader r = new FileReader(new File(path, fileName));
        BufferedReader br = new BufferedReader(r);
        Writer w = new FileWriter(new File(path2, fileName2));
        BufferedWriter bw = new BufferedWriter(w);
        String str = "";
        while ((str =br.readLine())!=null) {
            System.out.println(str);
            bw.write(str);
            bw.write("\n");
         //   Thread.sleep(4000);
        }
        br.close();
        r.close();
        bw.close();
        w.close();
        return null;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // fileRead(new File(System.getProperty("user.dir")),new File("text.txt"),new File(System.getProperty("user.dir")),new File("text2.txt"));
        fileRead2(System.getProperty("user.dir"), "text.txt", System.getProperty("user.dir"), "text3.txt");
    }
}
