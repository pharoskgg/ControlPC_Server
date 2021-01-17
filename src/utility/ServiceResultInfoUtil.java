package utility;

import java.util.ArrayList;

public class ServiceResultInfoUtil {
    public static ArrayList<String> sendResultInfo(String cmd,String status, ArrayList<String> resultInfo){
        ArrayList<String> infoList = new ArrayList<>();
        infoList.add(2 + resultInfo.size() + "");
        infoList.add(status);
        infoList.add(cmd);
        if (resultInfo != null)
            infoList.addAll(resultInfo);
        return infoList;
    }
}
