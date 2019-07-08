package com.homecredit.research;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageBinary {
    //将图片信息转化成二进制数组
    public static BASE64Encoder encoder = new BASE64Encoder();
    public static BASE64Decoder decoder = new BASE64Decoder();


    public static byte[] getImageBinary(File f){
//        File f = new File(Imgpath);
        BufferedImage bi ;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();  // 二进制数组
            // 返回 bytes 的 Hashcode码
            return bytes;
        } catch (IOException e) {
            System.out.println("IOException: Image path ERROR!");
            e.printStackTrace();
        }
        return null;
    }
    public static void bytesToImage(String savePath, byte[] bytes){
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            BufferedImage bi1 = ImageIO.read(bais);
            File fw = new File(savePath);
            ImageIO.write(bi1, "jpg", fw);
            System.out.println("图片已保存");
        } catch (IOException e) {
            System.out.println("IOException: Image Print Out ERROR!");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        byte[] bytes = getImageBinary(new File("D:\\data\\img\\000070.jpg"));
        for(byte b: bytes){
            System.out.print(b + " ");
        }
        System.out.println("长度：" + bytes.length);

        bytesToImage("D:\\data\\img1\\2.jpg", bytes);

        // 将二进制数组转化为 base64  编码的 String格式。
        // encoder.encodeBuffer(bytes).trim();

        // base64 转化为 byte数组
        //byte[] bytes1 = decoder.decodeBuffer(base64String);

    }
}
