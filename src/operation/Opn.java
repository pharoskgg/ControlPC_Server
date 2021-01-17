package operation;

import config.ResultStatus;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Opn extends BaseOperator{
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(cmdBody);
        desktop.open(file);
        return ServiceResultInfoUtil.sendResultInfo("opn", ResultStatus.OK, TempArrayList.getList(String.format("打开文件：%s 成功", cmdBody)));
    }
}
