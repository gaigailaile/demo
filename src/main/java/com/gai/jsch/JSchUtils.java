package com.gai.jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JSchUtils {
    private static Logger logger = LoggerFactory.getLogger(JSchUtils.class);

    public static List<String> remoteExecute(Session session,String command){
        logger.debug(">>{}",command);
        List<String> resultLines = new ArrayList<>();
        ChannelExec channelExec = null;
        InputStream inputStream = null;
        BufferedReader inputReader = null;
        try {
            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);
            inputStream = channelExec.getInputStream();
            channelExec.connect();
            inputReader = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine = null;
            while ((inputLine = inputReader.readLine()) != null){
                logger.debug("{}",inputLine);
                resultLines.add(inputLine);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("JSch channel inputStream close error:",e);
                }
            }
            if(inputReader != null){
                try {
                    inputReader.close();
                } catch (IOException e) {
                    logger.error("inputReader close error:",e);
                }
            }
            if(channelExec != null){
                try {
                    channelExec.disconnect();
                }catch (Exception e){
                    logger.error("JSch channel disconnect error:",e);
                }
            }
        }
        return resultLines;
    }

    public static long scpTo(Session session, String source, String destination) {
        FileInputStream fileInputStream = null;
        try {
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();
            boolean ptimestamp = false;
            String command = "scp";
            if (ptimestamp) {
                command += " -p";
            }
            command += " -t " + destination;
            channel.setCommand(command);
            channel.connect();
            if (checkAck(in) != 0) {
                return -1;
            }
            File _lfile = new File(source);
            if (ptimestamp) {
                command = "T " + (_lfile.lastModified() / 1000) + " 0";
                // The access time should be sent here,
                // but it is not accessible with JavaAPI ;-<
                command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
                out.write(command.getBytes());
                out.flush();
                if (checkAck(in) != 0) {
                    return -1;
                }
            }
            //send "C0644 filesize filename", where filename should not include '/'
            long fileSize = _lfile.length();
            command = "C0644 " + fileSize + " ";
            if (source.lastIndexOf('/') > 0) {
                command += source.substring(source.lastIndexOf('/') + 1);
            } else {
                command += source;
            }
            command += "\n";
            out.write(command.getBytes());
            out.flush();
            if (checkAck(in) != 0) {
                return -1;
            }
            //send content of file
            fileInputStream = new FileInputStream(source);
            byte[] buf = new byte[1024];
            long sum = 0;
            while (true) {
                int len = fileInputStream.read(buf, 0, buf.length);
                if (len <= 0) {
                    break;
                }
                out.write(buf, 0, len);
                sum += len;
            }
            //send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            if (checkAck(in) != 0) {
                return -1;
            }
            return sum;
        } catch(JSchException e) {
            logger.error("scp to catched jsch exception, ", e);
        } catch(IOException e) {
            logger.error("scp to catched io exception, ", e);
        } catch(Exception e) {
            logger.error("scp to error, ", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    logger.error("File input stream close error, ", e);
                }
            }
        }
        return -1;
    }

    public static long scpFrom(Session session, String source, String destination) {
        FileOutputStream fileOutputStream = null;
        try {
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("scp -f " + source);
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] buf = new byte[1024];
            //send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            while(true) {
                if (checkAck(in) != 'C') {
                    break;
                }
            }
            //read '644 '
            in.read(buf, 0, 4);
            long fileSize = 0;
            while (true) {
                if (in.read(buf, 0, 1) < 0) {
                    break;
                }
                if (buf[0] == ' ') {
                    break;
                }
                fileSize = fileSize * 10L + (long)(buf[0] - '0');
            }
            String file = null;
            for (int i = 0; ; i++) {
                in.read(buf, i, 1);
                if (buf[i] == (byte) 0x0a) {
                    file = new String(buf, 0, i);
                    break;
                }
            }
            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            // read a content of lfile
            if (Files.isDirectory(Paths.get(destination))) {
                fileOutputStream = new FileOutputStream(destination + File.separator +file);
            } else {
                fileOutputStream = new FileOutputStream(destination);
            }
            long sum = 0;
            while (true) {
                int len = in.read(buf, 0 , buf.length);
                if (len <= 0) {
                    break;
                }
                sum += len;
                if (len >= fileSize) {
                    fileOutputStream.write(buf, 0, (int)fileSize);
                    break;
                }
                fileOutputStream.write(buf, 0, len);
                fileSize -= len;
            }
            return sum;
        } catch(JSchException e) {
            logger.error("scp to catched jsch exception, ", e);
        } catch(IOException e) {
            logger.error("scp to catched io exception, ", e);
        } catch(Exception e) {
            logger.error("scp to error, ", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    logger.error("File output stream close error, ", e);
                }
            }
        }
        return -1;
    }

    public static int checkAck(InputStream inputStream) throws IOException {
        int b = inputStream.read();
        /*
        * 0 成功
        * 1 错误
        * 2 严重错误
        * -1 失败
        * */
        if(b == 0 || b == -1)
            return b;
        if(b == 1 || b == 2){
            StringBuffer stringBuffer = new StringBuffer();
            int c;
            do{
                c = inputStream.read();
                stringBuffer.append((char)c);
            }while (c != '\n');
            logger.debug(stringBuffer.toString());
        }
        return b;
    }

    private static boolean remoteEdit(Session session, String source, Function<List<String>, List<String>> process) {
        InputStream in = null;
        OutputStream out = null;
        try {
            String fileName = source;
            int index = source.lastIndexOf('/');
            if (index >= 0) {
                fileName = source.substring(index + 1);
            }
            //backup source
            remoteExecute(session, String.format("cp %s %s", source, source + ".bak." +System.currentTimeMillis()));
            //scp from remote
            String tmpSource = System.getProperty("java.io.tmpdir") + session.getHost() +"-" + fileName;
            scpFrom(session, source, tmpSource);
            in = new FileInputStream(tmpSource);
            //edit file according function process
            String tmpDestination = tmpSource + ".des";
            out = new FileOutputStream(tmpDestination);
            List<String> inputLines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                inputLines.add(inputLine);
            }
            List<String> outputLines = process.apply(inputLines);
            for (String outputLine : outputLines) {
                out.write((outputLine + "\n").getBytes());
                out.flush();
            }
            //scp to remote
            scpTo(session,tmpDestination, source);
            return true;
        } catch (Exception e) {
            logger.error("remote edit error, ", e);
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    logger.error("input stream close error", e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    logger.error("output stream close error", e);
                }
            }
        }
    }
}
