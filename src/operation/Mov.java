package operation;

import config.ResultStatus;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.awt.*;
import java.util.ArrayList;

//相对移动
public class Mov extends BaseOperator {
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        String[] split = cmdBody.split(",");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);

        Point location = MouseInfo.getPointerInfo().getLocation();

        Robot robot = new Robot();
        robot.mouseMove(location.x + x, location.y + y);
        return ServiceResultInfoUtil.sendResultInfo("mov", ResultStatus.OK, TempArrayList.getList("mov:" + MouseInfo.getPointerInfo().getLocation()));
    }
}
