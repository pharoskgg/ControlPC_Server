package operation;

import config.ResultStatus;
import org.junit.jupiter.api.Test;
import socketservice.FileDownLoadSocketThread;
import socketservice.FileUpLoadSocketThread;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.io.File;
import java.util.ArrayList;

public class Ulf extends BaseOperator{
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        int splitIdx = cmdBody.indexOf("?");
        File file;
        FileUpLoadSocketThread fileUpLoadSocketThread;
//        if (splitIdx < 1){
            file = new File("./upload/" + cmdBody);
            fileUpLoadSocketThread = new FileUpLoadSocketThread(file);
//        }
        fileUpLoadSocketThread.start();
        ArrayList<String> list = TempArrayList.getList(fileUpLoadSocketThread.getSocketPort() + "");
        ArrayList<String> resultInfo = ServiceResultInfoUtil.sendResultInfo("ulf", ResultStatus.OK, list);

        return resultInfo;
    }

    @Test
    public void test(){
        System.out.println(new File("upload").getAbsoluteFile());
    }

}
