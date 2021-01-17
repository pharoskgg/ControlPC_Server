package operation;

import config.ResultStatus;
import socketservice.FileDownLoadSocketThread;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.io.File;
import java.util.ArrayList;

public class Dlf extends BaseOperator {
    private FileDownLoadSocketThread downLoadSocketThread;
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        int splitIdx = cmdBody.indexOf("?");
        File file;
        if (splitIdx < 1){
             file = new File(cmdBody);
            downLoadSocketThread = new FileDownLoadSocketThread(file, 0);
        }else {
            int filePos = Integer.parseInt(cmdBody.substring(splitIdx + 1));
            file = new File(cmdBody.substring(0, filePos));
            downLoadSocketThread = new FileDownLoadSocketThread(file, filePos);
        }
        downLoadSocketThread.start();
        ArrayList<String> list = TempArrayList.getList(downLoadSocketThread.getSocketPort() + "");
        list.add(file.length()+"");
        ArrayList<String> resultInfo = ServiceResultInfoUtil.sendResultInfo("dlf", ResultStatus.OK, list);
        return resultInfo;
    }
}
