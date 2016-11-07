import java.io.*;
import java.util.Properties;
import java.util.TimerTask;

/**
 * Created by Fairy_LFen on 2016/11/7.
 */
public class ExecutBatTimerTask extends TimerTask {
    private String batFilePath = null;
    private String weiboPropFilePath = null;
    private String weiboDataPath = null;
    public ExecutBatTimerTask(){
        try {
            InputStream inputStream = ExecutBatTimerTask.class.getClassLoader().getResourceAsStream("conf.properties");
            Properties prop = new Properties();
            prop.load(inputStream);

            batFilePath = prop.getProperty("batFilePath");
            weiboPropFilePath = prop.getProperty("weiboPropFilePath");
            weiboDataPath = prop.getProperty("weiboDataPath");

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            updateStartTime();
            Process ps = Runtime.getRuntime().exec(batFilePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStartTime() {
        try {
            File dataFile = new File(weiboDataPath);
            File[] dataFiles = dataFile.listFiles();
            File last_File = dataFiles[dataFiles.length - 1];
            String last_FileName = last_File.getName();

            System.out.println("last_FileName : " + last_FileName);

            String[] str_arry = last_FileName.split("-|\\.| ");
            String startTime = str_arry[1] +"-"+ str_arry[2] +"-"+str_arry[3] +" "+ str_arry[4] +":"+str_arry[5] +":"+str_arry[6];

            InputStream inputStream = new FileInputStream(weiboPropFilePath);
            Properties prop = new Properties();
            prop.load(inputStream);

            OutputStream outputStream = new FileOutputStream(weiboPropFilePath);
            prop.setProperty("startTime", startTime);
            prop.store(outputStream, "modify startTime value");
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
