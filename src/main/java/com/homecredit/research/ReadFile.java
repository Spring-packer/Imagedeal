package com.homecredit.research;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ReadFile {

    public static BASE64Encoder encoder = new BASE64Encoder();
    public static Map<String, byte[]> readFile(String filePath) throws IOException {
//        ArrayList<String> strings = new ArrayList<String>();
        Map map = new HashMap<String, byte[]>();
        try {
            File file = new File(filePath);
            if (!file.isDirectory() && file.getName().contains(".jpg")) {
                System.out.println("当前路径是一个文件，而非目录");
//                System.out.println("path=" + file.getPath());
//                System.out.println("absolutepath=" + file.getAbsolutePath());
//                System.out.println("name=" + file.getName());
                map.put(file.getName(), ImageBinary.getImageBinary(file));
//                strings.add(file.getPath());
            } else {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filePath + File.separator + filelist[i]);
                    // -1 != s.getName().lastIndexOf(".xml")
                    if (readfile.isFile() && -1 != readfile.getName().lastIndexOf(".jpg")) {
                        map.put(readfile.getName(), ImageBinary.getImageBinary(readfile));

                    } else {
                        readFile(filePath + File.separator + filelist[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件目录：" + e.getMessage());
            e.getStackTrace();
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        for ( Map.Entry<String, byte[]> entry : readFile("D:\\data\\Blurry_trainA").entrySet()) {
//            System.out.println(entry.getKey() + "   " + encoder.encodeBuffer(entry.getValue()).trim())  ;
            System.out.println(entry.getKey() + "   " + entry.getValue())  ;
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000.0);

    }
}
