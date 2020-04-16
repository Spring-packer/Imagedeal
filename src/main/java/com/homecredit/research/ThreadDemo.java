package com.homecredit.research;

/*
    姓黑的，听好了，操你妈！不解释
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ThreadDemo {
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
        read read = new read();
        new Thread(read, "线程1").start();
        new Thread(read, "线程2").start();
        new Thread(read, "线程3").start();
        new Thread(read, "线程4").start();
        new Thread(read, "线程5").start();
//        long end = System.currentTimeMillis();
//        System.out.println((end - start)/1000.0);

    }
}
class read implements Runnable{
    List<File> filePathsList = new ArrayList<File>();
    int index = 0 ;
    public read(){
        File f = new File("d:" + File.separator + "data" + File.separator + "Blurry_trainA");
        getFileList(f);

    }

    public void run() {
        File file = null;
        while (index < filePathsList.size()) {
            synchronized (this) {
                if (index >= filePathsList.size()) {
                    continue;
                }
                file = filePathsList.get(index);
                index++;
            }

        try {
            for ( Map.Entry<String, byte[]> entry : ReadFile.readFile(file.getAbsolutePath()).entrySet()) {
                System.out.println(entry.getKey() + "   " + entry.getValue())  ;
            }
//            ReadFile.readFile(file.getAbsolutePath())

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

    private void getFileList(File f){
        File[] filePaths = f.listFiles();
        for (File s: filePaths){
            if ( s.isDirectory()){
                getFileList(s);
            } else {
                if ( -1 != s.getName().lastIndexOf(".jpg")){
                    filePathsList.add(s);
                }
            }
        }

//        ReadFile.readFile()



    }

}
