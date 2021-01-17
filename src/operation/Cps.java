package operation;

import config.ResultStatus;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;

public class Cps extends BaseOperator{

    @Override
    public ArrayList<String> exe(String cmdBody) throws Exception {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable text = new StringSelection(cmdBody);
        clipboard.setContents(text, null);
        new Key().exe("VK_CONTROL+VK_V");
        return ServiceResultInfoUtil.sendResultInfo("cps", ResultStatus.OK, TempArrayList.getList("复制成功"));
    }
}
