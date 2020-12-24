package com.gai.python;

import java.io.*;

public class PythonDemo {
    public static void main(String[] args) throws IOException {
        Process process = null;
        BufferedReader in = null;
        FileOutputStream f = null;
        BufferedWriter out = null;
        try {
            String[] command = {"python","/Users/gaidongxu/a.py"};
//            process = Runtime.getRuntime().exec("python /Users/gaidongxu/a.py");
            process = Runtime.getRuntime().exec(command);
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            File file = new File("/Users/gaidongxu/log/all.log");
            String line = null;
            f = new FileOutputStream(file);
            out = new BufferedWriter(new OutputStreamWriter(f));
            while ((line = in.readLine()) != null) {
                out.write(line);
                out.write("\n");
                System.out.println(line);
            }
            int i = process.waitFor();
            if(i == 0){
                System.out.println("运行成功!!");
            }else {
                BufferedReader in1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line1 = null;
                while ((line1 = in1.readLine()) != null) {
                    out.write(line1);
                    out.write("\n");
                    System.out.println(line1);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            out.close();
            f.close();
            process.destroy();
            in.close();
        }
    }
}
