package operation;

import config.ResultStatus;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.awt.*;
import java.util.ArrayList;

public class Rol extends BaseOperator {
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        Robot robot = new Robot();
        int value = Integer.parseInt(cmdBody);
        robot.mouseWheel(value);
        return ServiceResultInfoUtil.sendResultInfo("rol", ResultStatus.OK, TempArrayList.getList("rol:" + value));
    }
}
