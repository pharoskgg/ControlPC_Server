import org.apache.log4j.Logger;
import org.junit.Test;
import utility.CheckFileExists;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class test {
    @Test
    public void temp(){
        ArrayList<String> list = new ArrayList<>();
//        ArrayList<String> temp =
        list.add("adfa");
        list.addAll(null);
        System.out.println(list);
    }

    @Test
    public void testCheckFIleExists(){
        System.out.println(CheckFileExists.check(""));
    }

    @Test
    public void testArraListAddByIndex(){
        ArrayList<String> list = new ArrayList<>();
        list.add("afsdf");
        list.add("afsdf");
        list.add("afsdf");
        list.add("afsdf");
        list.add(0, "kggggg");
        for (String str : list){
            System.out.println(str);
        }
    }

    @Test
    public void testCmdOutputInfo(){

        try {
//            String cmd = "cmd /c start www.wzu.edu.cn";
            String cmd = "cmd /c dir G:";
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            InputStream is = proc.getInputStream();
            InputStream es = proc.getErrorStream();
            String line;
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) {
                System.out.println(">>>{}"+line);
            }
            br = new BufferedReader(new InputStreamReader(es, "UTF-8"));
            while ((line = br.readLine()) != null) {
                System.out.println(">>>{}"+line);
            }
        } catch (Exception e) {
            System.out.println(">>>{}"+e);
        }
    }

    @Test
    public void testSplitGetEnd(){
        if("2" == "2"){
            System.out.println("ok");
        }
    }
}
