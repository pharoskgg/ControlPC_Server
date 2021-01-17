package utility;

import java.util.ArrayList;

public class TempArrayList {
    private static ArrayList<String> list = new ArrayList<>();
    private TempArrayList(){}

    public static ArrayList<String> getList(String msg){
        list.clear();
        list.add(msg);
        return list;
    }
}
