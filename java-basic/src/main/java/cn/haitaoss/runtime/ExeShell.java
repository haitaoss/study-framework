package cn.haitaoss.runtime;

import java.io.IOException;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2022-06-19 17:36
 *
 */
public class ExeShell {
    public static void main(String[] args) {

        try {
            new ProcessBuilder("/bin/sh", "-c", "kill -9 `lsof -i :8080 | awk 'NR==2 {print $2}'`\n").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) throws Exception {
        String s = null;
        try {
            // Process p = Runtime.getRuntime().exec("lsof -i :8080");
            Process p = Runtime.getRuntime().exec(" lsof -i :8080 | awk '{print $2}' ");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            //从命令行打印出输出结果
            System.out.println("标准输出命令\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            System.out.println("标准错误的输出命令(如果存在):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            System.exit(0);
        } catch (IOException e) {
            System.out.println("异常发生: ");
            e.printStackTrace();
            System.exit(1);
        }
    }*/

    /*public static void main(String[] args) throws Exception {
        // 创建命令集合,放入执行的命令
        *//*List<String> commands = new ArrayList<String>();
        commands.add("/bin/sh");
        commands.add("-c");
        commands.add("ps -ef | grep chrome");
        // ProcessBuilder builder = new ProcessBuilder(commands);*//*

        // 构建ProcessBuilder
        // 构建ProcessBuilder也可以不适用list,直接在括号里写命令,每个命令是一个String字符串
        ProcessBuilder builder = new ProcessBuilder("/bin/sh", "-c", "lsof -i :8080 | awk ' NR==2 {print $2}'");
        Process start = builder.start();

        try (Scanner scanner = new Scanner(start.getInputStream());
             Scanner errorScanner = new Scanner(start.getErrorStream());) {
            System.out.println("命令执行结果为: \n");
            while (scanner.hasNextLine()) {
                String pid = scanner.nextLine();
                System.out.println(String.format("杀掉进程 PID{%s}", pid));

                Runtime.getRuntime().exec(String.format("kill -9 %s", pid));
            }
            System.out.println("命令执行错误结果为: \n");
            while (errorScanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/


}
