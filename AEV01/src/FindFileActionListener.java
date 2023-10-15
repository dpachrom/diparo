import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;

public class FindFileActionListener implements ActionListener {
    private final JFrame parentFrame;
    private final DefaultListModel<String> model;
    public FindFileActionListener(JFrame parentFrame, DefaultListModel<String> model) {
        this.parentFrame = parentFrame;
        this.model = model;
    }
    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
    }
    private String format(File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return file.getName() + " | " + getExtension(file.toString()) + " | " + sdf.format(file.lastModified()) + " | " + file.length();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(parentFrame) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        Main.currentDirectory = chooser.getCurrentDirectory();
        //CurrentDirectory()
        File[] files = chooser.getCurrentDirectory().listFiles(ExtensionFilter.textFileFilter);
        for (File file : files) {
            model.addElement(format(file));
        }
    }
}