package operation;

import config.ResultStatus;
import data.NetFileData;
import utility.CheckFileExists;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Dir extends BaseOperator{
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        ArrayList<String> resultInfoList;

        if (!CheckFileExists.check(cmdBody)){
            return ServiceResultInfoUtil.sendResultInfo("dir", ResultStatus.FAILURE, TempArrayList.getList("不存在此文件夹，请重新输入路径"));
        }else if ("...".equals(cmdBody)){
            resultInfoList = ServiceResultInfoUtil.sendResultInfo("dir", ResultStatus.OK, dirDrive(cmdBody));
        } else if ("..".equals(cmdBody)){
            resultInfoList = ServiceResultInfoUtil.sendResultInfo("dir", ResultStatus.OK, dirAllFile("./.."));
        }else{
            resultInfoList = ServiceResultInfoUtil.sendResultInfo("dir", ResultStatus.OK, dirAllFile(cmdBody));
        }
        return resultInfoList;
    }

    //列出所有文件
    private ArrayList<String> dirAllFile(String path) throws Exception{
        File dir = new File(path);
        NetFileData fileAttribute = null;
        ArrayList<String> resultInfoList = new ArrayList<String>();
        //添加绝对路径
        resultInfoList.add(getAbsolotePath(path));
        //固定信息输入
        File [] files = dir.listFiles();
        for (File file : files) {
            Long date = file.lastModified();
            if (file.isDirectory())
                fileAttribute = new NetFileData(date, file.getName(), 1);
             else
                fileAttribute = new NetFileData(date, file.getName(), file.length(), 0);

            resultInfoList.add(fileAttribute.toString());
        }
        return resultInfoList;
    }

    //处理特殊命令：列出盘符
    private ArrayList<String> dirDrive(String path){
        ArrayList<String> resultInfoList = new ArrayList<String>();
        resultInfoList.add("root");//填充一行，使客户端便于处理
        File[] files = File.listRoots();
        for (File root : files) {
            String rootName = root.toString();
            long rootDate = root.lastModified();
            resultInfoList.add(new NetFileData(rootDate, rootName, 2).toString());
        }
        return resultInfoList;
    }

    private String getAbsolotePath(String path) throws Exception{
        File file = new File(path);
        return file.getCanonicalPath();
    }
}
