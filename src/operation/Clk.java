package operation;

import config.ResultStatus;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Clk extends BaseOperator {
    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        Robot robot = new Robot();
        if ("left".equalsIgnoreCase(cmdBody)){
            robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
        }else if ("right".equalsIgnoreCase(cmdBody)){
            robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
        }else if ("left_press".equalsIgnoreCase(cmdBody)){
            robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        }else if ("left_release".equalsIgnoreCase(cmdBody)){
            robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
        }else if ("right_press".equalsIgnoreCase(cmdBody)){
            robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
        }else if ("right_release".equalsIgnoreCase(cmdBody)){
            robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
        }
        return ServiceResultInfoUtil.sendResultInfo("clk", ResultStatus.OK, TempArrayList.getList("clk:" + cmdBody));
    }
}
