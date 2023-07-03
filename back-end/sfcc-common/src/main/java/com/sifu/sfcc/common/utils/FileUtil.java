package com.sifu.sfcc.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 自定义文件上传工具类，文件存放的位置为project目录
 *
 * @date 2020.06.04
 */
@Slf4j
public class FileUtil {

    /**
     * 系统文件分隔符 File.separator
     */
    private static final String SEPARATOR = "/";

    /**
     * 这里上传指定的文件到此module所属的project目录
     */
    private static final String BASE_DIR = System.getProperty("user.dir") + SEPARATOR;

    /**
     * 一级目录
     */
    private static final String FIRST_LEVEL_DIR = "uploads";

    /**
     * 默认二级目录
     */
    private static final String SECOND_LEVEL_DIR_DEFAULT = "default";

    /**
     * @param file file
     * @return relative path
     * @throws IOException IOException
     */
    public static String upload(MultipartFile file) throws IOException {
        return upload(file, SECOND_LEVEL_DIR_DEFAULT);
    }

    /**
     * 按相对路径删除文件
     * @param relativePath relative path
     * @return status
     */
    public static boolean delete(String relativePath) {
        File file = new File(BASE_DIR + relativePath);
        return file.delete();
    }

    /**
     * <p>
     * 一级目录: {@code upload}
     * 二级目录: {@code 请提供}
     * </p><p>
     * 命名格式 {@link FileUtil#fullFilename(String)}
     * <pre>
     * {@code 20200603142533_84678.jpg}
     * </pre>
     * </p>
     *
     * @param file        file
     * @param secondLPath second level directory
     * @return relative path
     * @throws IOException IOException
     */
    public static String upload(MultipartFile file, String secondLPath) throws IOException {
        String filename = fullFilename(file.getOriginalFilename());
        String compressedFilename = fullFilename(file.getOriginalFilename());

        // 保存文件的路径，后跟full filename即可
        String savePath = BASE_DIR + FIRST_LEVEL_DIR + SEPARATOR + secondLPath + SEPARATOR;

        // 文件和枷锁文件
        String fileSavePath = savePath + filename;
        String compressedFileSavePath = savePath + compressedFilename;

        File originFile = new File(fileSavePath);
        if (!originFile.getParentFile().exists()) {
            originFile.getParentFile().mkdirs();
        }
        // 写入，multipartFile只能写一次，流就关闭
        file.transferTo(originFile);

        // 压缩选择，返回最终的filepath
        String relativePath = zipFile(file, originFile, compressedFileSavePath, fileSavePath);
        relativePath = relativePath.replace(BASE_DIR, "");
        log.info("writing a file: " + fileSavePath + " | " + "its relative path to BASE DIR: " + relativePath);
        return relativePath;
    }

    /**
     * 文件删除
     *
     * @param url  url
     * @param path path
     * @return code
     */
    public static boolean delete(String url, String path) {
        //TODO 文件删除
        return false;
    }

    public static String zipFile(MultipartFile file, File tempFile, String zipFileSavePath, String tempFileSavePath) {
        try {
            // 转KB
            long size = file.getSize() / 1024;
            // 判断文件大小对图片质量进行压缩,尺寸不变，
            // 范围0.01~1.0,值越低压缩效率越高。图片低于600K不进行压缩
            if (size >= 7380) {
                Thumbnails.of(tempFileSavePath).scale(1f).outputQuality(0.01f).toFile(zipFileSavePath);
                tempFile.delete();
                return zipFileSavePath;
            } else if (size >= 4096) {
                Thumbnails.of(tempFileSavePath).scale(1f).outputQuality(0.2f).toFile(zipFileSavePath);
                tempFile.delete();
                return zipFileSavePath;
            } else if (size >= 1024) {
                Thumbnails.of(tempFileSavePath).scale(1f).outputQuality(0.3f).toFile(zipFileSavePath);
                tempFile.delete();
                return zipFileSavePath;
            } else if (size >= 600) {
                Thumbnails.of(tempFileSavePath).scale(1f).outputQuality(1f).toFile(zipFileSavePath);
                tempFile.delete();
                return zipFileSavePath;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        // 低于600K不进行压缩
        return tempFileSavePath;
    }

    /**
     * 按照文件本身的扩展名，生成满足格式的新文件名
     *
     * @param originalFilename originalFilename
     * @return full filename
     */
    public static String fullFilename(String originalFilename) {
        // 当前毫秒数
        long currentMillions = DateUtil.current(false);
        // 前后缀
        String prefix = DateUtil.format(new Date(currentMillions), "yyyyMMddHHmmss");
        String suffix = RandomUtil.randomNumbers(5);
        String newFileName = prefix + "_" + suffix;
        String extensionName = FilenameUtils.getExtension(originalFilename);
        return newFileName + "." + extensionName;
    }
    
    public static String getUploadResource(String fileName) {
        //返回读取指定资源的输入流
        InputStream is = FileUtil.class.getResourceAsStream("/" + fileName);

        //若文件已存在，则返回的filePath中含有"EXIST"，则不需再重写文件
        String filePath  = createFile(fileName);

        //文件不存在，则创建流输入默认数据到新文件
        if (!filePath.contains("EXIST")) {
            File file = new File(filePath);
            inputStreamToFile(is, file);
            return filePath;
        }
        String path = System.getProperty("user.dir");
        System.out.println("path = " + path);
        return filePath.substring(5 + path.length() + 1);//去掉系统的根目录
    }
    
    public static String createFile(String filename) {
        String path = System.getProperty("user.dir");
        //create folder
        String dirPath = path + File.separator + FIRST_LEVEL_DIR + SEPARATOR + "sfcc";
        File dir = new File(dirPath);
        dir.mkdirs();

        //create file
        String filePath = dirPath + File.separator + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return filePath;
        }
        return "EXIST" + filePath;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
