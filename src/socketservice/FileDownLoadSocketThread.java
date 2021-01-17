package socketservice;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileDownLoadSocketThread extends Thread {
    private ServerSocket serverSocket;
    private long filePos=0l;
    private File file;

    public FileDownLoadSocketThread(File file, long filePos) {
        // TODO Auto-generated constructor stub
        try {
            serverSocket = new ServerSocket(0);//动态分配可用端口
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.file=file;
        this.filePos=filePos;
    }

    @Override
    public void run() {
        try {
            download();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download() throws IOException {
        byte[] buffer = new byte[1024 * 8];
        int length = 0;

        Socket accept = serverSocket.accept();

        //文件的输出流
        FileInputStream fis = new FileInputStream(file);
        fis.skip(filePos);
        //socket的输出流
        DataOutputStream dos = new DataOutputStream(accept.getOutputStream());

        while ((length = fis.read(buffer)) != -1){
            dos.write(buffer, 0, length);
            dos.flush();
        }

        fis.close();
        dos.close();
        accept.close();
    }

    public int getSocketPort(){
        return this.serverSocket.getLocalPort();
    }
}
