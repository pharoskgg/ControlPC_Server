package operation;



import config.ResultStatus;
import data.VisualKeyMap;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.awt.*;
import java.util.ArrayList;

public class Key extends BaseOperator{
    VisualKeyMap keyMap = VisualKeyMap.getInstancei();
    private Robot robot;

    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        robot = new Robot();

        int splitIdx = cmdBody.indexOf(",");
        //先判断键类型有无带","的，若有，则按下键的顺序和释放键的顺序由逗号前的“+”和逗号后的“+”来设置顺序
        if (splitIdx < 1) {
            int splitIdx2 = cmdBody.indexOf("+");//靠“+”来分割组合键
            if(splitIdx2<1){
                singleKeyPress(cmdBody);//没有“+”号表示单按键
            }else{
                simpleComboKeyPress(cmdBody);//组合键
            }
        }else{
            String keyPressStr=cmdBody.substring(0, splitIdx);//取0-（spisplitIdx-1）索引构成的子字符串
            String keyReleaseStr=cmdBody.substring(splitIdx+1);//取splitIdx+1到结尾的子字符串
            comboKeyPress(keyPressStr,keyReleaseStr);
        }
        return ServiceResultInfoUtil.sendResultInfo("key", ResultStatus.OK, TempArrayList.getList("key:" + cmdBody));
    }

    private void simpleComboKeyPress(String keyPressStr){
        String[] keyPressArray = keyPressStr.split("\\+");

        for(int i=0;i<keyPressArray.length;i++){//按“+”的顺序按下按键
            int keycode = VisualKeyMap.getVisualKey(keyPressArray[i]);
            robot.keyPress(keycode);
        }
        for(int i=keyPressArray.length-1;i>=0;i--){//反序释放按键
            int keycode = VisualKeyMap.getVisualKey(keyPressArray[i]);
            robot.keyRelease(keycode);
        }
    }

    private  void comboKeyPress(String keyPressStr, String keyReleaseStr) {
        String[] keyPressArray = keyPressStr.split("\\+");
        String[] keyReleaseArray = keyReleaseStr.split("\\+");
        for(int i=0;i<keyPressArray.length;i++){
            int keycode = VisualKeyMap.getVisualKey(keyPressArray[i]);
            robot.keyPress(keycode);

        }
        for(int i=0;i<keyReleaseArray.length;i++){
            int keycode = VisualKeyMap.getVisualKey(keyReleaseArray[i]);
            robot.keyRelease(keycode);
        }
    }

    private void singleKeyPress(String cmdBody) {
        int keycode = VisualKeyMap.getVisualKey(cmdBody);
        robot.keyPress(keycode);
        robot.keyRelease(keycode);

    }
}