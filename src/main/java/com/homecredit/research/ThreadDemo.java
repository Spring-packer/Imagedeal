

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: Imagedeal
 * @Package: com.homecredit.research
 * @ClassName: ThreadDemo
 * @Description: 类作用描述
 * @Author: 作者：龙飞
 * @CreateDate: 2019/7/8 21:01
 * @UpdateUser: 更新者：龙飞
 * @UpdateDate: 2019/7/8 21:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
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
