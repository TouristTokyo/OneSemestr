import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameLoadingOnDropbox extends JFrame {
    private JPanel panelWindow;
    private JButton buttonLoadingStart;
    private JLabel labelImage;
    private JLabel labelLoadingCountScreen;
    private JLabel labelCountScreen;
    private int countScreen = 0;
    private int countLoadingScreen = 0;

    public FrameLoadingOnDropbox() {
        this.setTitle("ScreenshotOnDropbox");
        this.setContentPane(panelWindow);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/пользователь/Desktop/Taski (не все)/Screenshot/Dropbox.jpg"));
            labelImage.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }


        buttonLoadingStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ACCESS_TOKEN = "JMdytIirz6MAAAAAAAAAAabTeepo8vKRu-be7v2GFf8CDRUmsAWFKNWvLLmNegC7";
                DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
                DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
                Date date = new Date();
                SimpleDateFormat formatDateNowOutputInFile = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");
                BufferedImage image;
                File file;
                Rectangle screenshot = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null,"Сохранить в файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                }else {
                    return;
                }
                JOptionPane.showMessageDialog(null, "Скриншот сделается через 5 секунд.\n" +
                                "Нажмите ОК, чтобы начать процесс.", "ScreenshotOnDropbox", JOptionPane.INFORMATION_MESSAGE);
                try {
                    Thread.sleep(5000);
                    image = new Robot().createScreenCapture(screenshot);
                    countScreen++;
                    labelCountScreen.setText("" + countScreen);
                    ImageIO.write(image, "png", file);
                    LoadingOnDropbox loading = new LoadingOnDropbox(client, file, countLoadingScreen,
                            formatDateNowOutputInFile.format(date),labelLoadingCountScreen);
                    loading.start();
                    while (!loading.loadingScreenSuccessful()){
                        JOptionPane.showMessageDialog(null, "Скриншот загружается на Dropbox.\n"+
                                "Ожидайте.", "ScreenshotOnDropbox", JOptionPane.INFORMATION_MESSAGE);
                    }
                    countLoadingScreen = loading.getCountLoadingScreenSuccessful();
                    labelLoadingCountScreen.setText(""+countLoadingScreen);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
