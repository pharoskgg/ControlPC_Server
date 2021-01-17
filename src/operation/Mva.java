package operation;

import config.ResultStatus;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.awt.*;
import java.util.ArrayList;

public class Mva extends BaseOperator {
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        Robot robot = new Robot();
        String[] split = cmdBody.split(",");

        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        int mov_x = (int) (x * width);
        int mov_y = (int) (y * height);
        robot.mouseMove(mov_x, mov_y);
        return ServiceResultInfoUtil.sendResultInfo("mov", ResultStatus.OK, TempArrayList.getList("mva:" + MouseInfo.getPointerInfo().getLocation()));
    }
}
