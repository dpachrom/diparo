import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OccurrencesActionListener implements ActionListener {
    private final JTextField textField;
    public OccurrencesActionListener(JTextField textField) {
        this.textField = textField;
    }
    private int occurrencesInFile(File file, String token) {
        int count = 0;
        try {
            String fileContent = Files.readString(file.toPath());
            for (int i = 0 ; i < fileContent.length() ; i++) {
                String remains = fileContent.substring(i);
                if (remains.length() < token.length()) {
                    break;
                }
                boolean isContained = true;
                for (int j = 0 ; j < token.length() ; j++) {
                    if (token.charAt(j) != remains.charAt(j)) {
                        isContained = false;
                        break;
                    }
                }
                if (isContained)
                    count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textOccurrence = textField.getText();
        File[] files = Main.currentDirectory.listFiles(ExtensionFilter.textFileFilter);
        for (File file : files) {
            int occsPerFile = occurrencesInFile(file,textOccurrence);
            JOptionPane.showMessageDialog(null,"There are "+ occsPerFile + " coincidences in the file: " + file.getName());
        }
    }
}