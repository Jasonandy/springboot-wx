package cn.ucaner.wx.chat.bot.utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName：FileUtils
 * @Description： <p> FileUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/7 - 13:41
 * @Modify By：
 * @ModifyTime： 2019/8/7
 * @Modify marker：
 * @version V1.0
*/
public class FileUtils {

    /**
     * delete file
     * @param filePath filePath
     */
    public static void deleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * read file
     * @param filePath filePath
     * @return result
     */
    public static String readFile(String filePath) {
        File file = new File(filePath);
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
            int fileLen = (int) file.length();
            char[] chars = new char[fileLen];
            try {
                reader.read(chars);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String text = String.valueOf(chars);
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * writer file
     * @param filePath filePath
     * @param content file content
     */
    public static void writer(String filePath, String content) {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
