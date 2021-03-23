package cdu.linmu.knight.util;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/23 2:51 下午
 * @description：
 * @modified By：
 * @version:
 */
public class UploadUtil {

    private static String basePath;


    private static String imagePath;


    static{
        try {
            basePath = ResourceUtils.getURL("classpath:").getPath() + "static";
            imagePath = "/image/";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    /**
     * 存储图片 并返回一个相对路径
     * @param file
     * @return
     */
    public static String saveFile(MultipartFile file) throws IOException {
        if(file != null){
            String url = System.currentTimeMillis() + ".jpg";
            File target = createFile(basePath + imagePath + url);
            file.transferTo(target);

            return url;
        }
        return null;
    }




    public static File createFile(String path) throws IOException {
        File file = new File(path);

        if(file.exists()){
            file.delete();
        }
        return file;
    }

}
