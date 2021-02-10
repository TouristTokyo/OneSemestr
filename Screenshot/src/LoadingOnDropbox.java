import com.dropbox.core.v2.DbxClientV2;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class LoadingOnDropbox extends Thread {
    private DbxClientV2 clientV2;
    private File fileInput;
    private String nameOutputFile;
    private int countLoadingScreen;
    private JLabel labelLoadingCountScreen;
    private boolean loadingScreenSuccessful = false;


    public LoadingOnDropbox(DbxClientV2 clientV2, File fileInput, int countLoadingScreen,String nameOutputFile,
                            JLabel labelLoadingCountScreen) {
        this.clientV2 = clientV2;
        this.fileInput = fileInput;
        this.countLoadingScreen = countLoadingScreen;
        this.nameOutputFile = nameOutputFile;
        this.labelLoadingCountScreen = labelLoadingCountScreen;
    }

    @Override
    public void run() {
        try {
            InputStream in = new FileInputStream(fileInput);
            clientV2.files().uploadBuilder("/" + nameOutputFile + ".png").uploadAndFinish(in);
            countLoadingScreen++;
            labelLoadingCountScreen.setText(""+countLoadingScreen);
            loadingScreenSuccessful = true;
            labelLoadingCountScreen.setText(""+countLoadingScreen);
            JOptionPane.showMessageDialog(null, "Скриншот успешно загружен.",
                    "ScreenshotOnDropbox", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Не удалось загрузить скриншот на Dropbox.\n" +
                            "Проверьте подключение к интернету.",
                    "ScreenshotOnDropbox", JOptionPane.INFORMATION_MESSAGE);
            loadingScreenSuccessful = true;
            ex.printStackTrace();
        }
    }
    public boolean loadingScreenSuccessful(){
        return loadingScreenSuccessful;
    }
    public int getCountLoadingScreenSuccessful(){
        return countLoadingScreen;
    }
}
