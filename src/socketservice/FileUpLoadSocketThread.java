package socketservice;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class FileUpLoadSocketThread extends Thread{
    private ServerSocket serverSocket;

    private File file;


    public FileUpLoadSocketThread(File file) {
        // TODO Auto-generated constructor stub
        try {
            serverSocket = new ServerSocket(0);//动态分配可用端口
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.file=file;
//        this.filePos=filePos;
    }


    @Override
    public void run() {
        try {
            upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upload() throws Exception{
        int length = 0;

        final byte[] bytes = new byte[1024 * 8];
        Socket accept = serverSocket.accept();
        try {
            DataInputStream dis = new DataInputStream(accept.getInputStream());
            FileOutputStream fos = new FileOutputStream(file);
            System.out.println("开始接收文件");
            while ((length = dis.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
                fos.flush();
            }
            dis.close();
            fos.close();
            accept.close();
            System.out.println("文件接收成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("连接成功");
    }

    public int getSocketPort(){
        return this.serverSocket.getLocalPort();
    }
}
