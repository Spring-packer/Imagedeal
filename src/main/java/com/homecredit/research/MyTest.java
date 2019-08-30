

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

public class MyTest {

    public static BASE64Encoder encoder = new BASE64Encoder();
    public static BASE64Decoder decoder = new BASE64Decoder();
    public static Map<String, byte[]> readFileImage(String filePath) throws Exception {
        Map map = new HashMap<String, byte[]>();
        try {
            File file = new File(filePath);
                File[] filelist = file.listFiles();
                for (File readfile: filelist) {
//                    File readfile = new File(filePath + File.separator + filelist[i]);
                    BufferedImage bi = ImageIO.read(readfile);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bi, "jpg", baos);
                    byte[] bytes = baos.toByteArray();
//                    String bts = encoder.encodeBuffer(bytes).trim();
                    map.put(readfile.getName(), bytes);
                }
        } catch (FileNotFoundException e) {
            System.out.println("文件目录：" + e.getMessage());
            e.getStackTrace();
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String path = "d:" + File.separator + "data" + File.separator + "Blurry_trainA";
        Map<String, byte[]> m = readFileImage(path);
        for ( Map.Entry<String, byte[]> entry : m.entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000.0);

    }


}
