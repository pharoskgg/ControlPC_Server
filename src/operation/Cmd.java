package operation;

import config.ResultStatus;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.util.ArrayList;

public class Cmd extends BaseOperator {
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        String command = cmdBody.split("\\s")[0];
        String params = cmdBody.substring(command.length());
        Runtime.getRuntime().exec(String.format("cmd.exe /c %s %s", command, params));
        return ServiceResultInfoUtil.sendResultInfo("cmd", ResultStatus.OK, TempArrayList.getList("cmd:"+cmdBody));
    }
}
