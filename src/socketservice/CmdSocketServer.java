package socketservice;

import config.ResultStatus;
import config.SocketConfig;
import operation.*;
import org.apache.log4j.Logger;
import utility.ServiceResultInfoUtil;
import utility.TempArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;


public class CmdSocketServer {

    //返回消息list
    private ArrayList<String> msgBackList;

    //配置日志
    Logger logger = Logger.getLogger(CmdSocketServer.class);

    public CmdSocketServer(){}

    //创建socket服务
    public void work(){
        try {
            ServerSocket serverSocket = new ServerSocket(SocketConfig.port);
            printLocalIp(serverSocket);
            while (true){
                logger.info("Waiting client to connect.....");
                Socket socketConnect = serverSocket.accept();
                logger.info("Client connected from: " + socketConnect.getRemoteSocketAddress().toString());
                ArrayList<String> cmdList = readSocketMsg(socketConnect);
                msgBackList = dealCmdList(cmdList);
                writeBackMsg(socketConnect);
                close(socketConnect);
            }
        } catch (IOException e) {
            e.printStackTrace();
            SocketConfig.port += 1;
            work();
        }
    }

    private ArrayList<String> dealCmdList(ArrayList<String> cmdList) {
        //返回给客户端的信息
        ArrayList<String> resultInfo = new ArrayList<String>();
        BaseOperator operator = null;
            for (String cmd : cmdList) {
                try {
                    String operation = cmd.split("\\:")[0];
                    String params = cmd.substring(4);
                    if ("dir".equals(operation))
                        operator = new Dir();
                    else if ("opn".equals(operation))
                        operator = new Opn();
                    else if ("key".equals(operation))
                        operator = new Key();
                    else if ("mov".equals(operation))
                        operator = new Mov();
                    else if ("mva".equals(operation))
                        operator = new Mva();
                    else if ("clk".equals(operation))
                        operator = new Clk();
                    else if ("rol".equals(operation))
                        operator = new Rol();
                    else if ("cps".equals(operation))
                        operator = new Cps();
                    else if ("cmd".equals(operation))
                        operator = new Cmd();
                    else if ("dlf".equals(operation))
                        operator = new Dlf();
                    else if ("ulf".equals(operation))
                        operator = new Ulf();
                    else {
                        return ServiceResultInfoUtil.sendResultInfo(cmd, ResultStatus.FAILURE, TempArrayList.getList("此功能暂未完成"));
                    }
                    resultInfo = operator.exe(params);
                    logger.info(String.format("命令：%s 结果：%s", cmd, resultInfo));
                } catch (Exception e) {
                    e.printStackTrace();
//                    用与捕获到错误时
                    resultInfo = ServiceResultInfoUtil.sendResultInfo(cmd, ResultStatus.FAILURE, TempArrayList.getList("服务端未知错误：" + e.toString()));
                }
            }
        return resultInfo;
    }
    //写回信息
    private void writeBackMsg(Socket socket) throws IOException {
        //输出流打包为打印流
        OutputStreamWriter pw = new OutputStreamWriter(socket.getOutputStream(), SocketConfig.stringEncode);
        for (String info : msgBackList) {
            pw.write(info + "\n");
            pw.flush();
        }
        logger.info(msgBackList);
    }

    private ArrayList<String> readSocketMsg(Socket socketConnect) throws IOException {
        ArrayList<String> client_msg = new ArrayList<String>();
        //获取socket的输入流并包装为bufferreader
        BufferedReader br = new BufferedReader(new InputStreamReader(socketConnect.getInputStream(), SocketConfig.stringEncode));

        //第一行为需执行的指令数目
        int cmdCount = Integer.parseInt(br.readLine());
        logger.info(String.format("cmdCount:%d",cmdCount));
        for (int i = 0; i < cmdCount; i++)
            client_msg.add(br.readLine());
        logger.info(client_msg);
        return client_msg;
    }

    //关闭socket连接
    private void close(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //打印服务端信息
    private void printLocalIp(ServerSocket serverSocket) {
        try {
            System.out.println("服务端命令端口prot=" + serverSocket.getLocalPort());
            Enumeration<NetworkInterface> interfaces = null;
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress next = address.nextElement();
                    String hostAddress = next.getHostAddress();
                    System.out.println("本机IP地址为：" + hostAddress);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            new CmdSocketServer().work();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
