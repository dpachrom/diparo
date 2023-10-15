import javax.swing.*;
import java.io.File;

public class Main {
    static public File currentDirectory = null;
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Explorer");
        frame.setSize(1280,768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultListModel<String> model = new DefaultListModel<>();

        JList<String> fileList = new JList<>(model);
        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JTextField textCoinc = new JTextField(20);
        //textCoinc.getText();
        JButton buttonExplorer = new JButton("Select File");
        JButton buttonSortAsc = new JButton(("Sort Ascendant"));
        JButton buttonSortDesc = new JButton(("Sort Descendent"));
        JButton buttonSizeSort = new JButton("Sort by Size");
        JButton buttonDateSort = new JButton("Sort by Date");
        JButton searchCoincidences = new JButton("Buscar coincidencias");
        JPanel panel = new JPanel();
        buttonExplorer.addActionListener(new FindFileActionListener(frame, model));
        searchCoincidences.addActionListener(new OccurrencesActionListener(textCoinc));
        buttonSortAsc.addActionListener(new SortListener(model, SortListener.sortAscComparator));
        buttonSortDesc.addActionListener(new SortListener(model, SortListener.sortDescComparator));
        buttonSizeSort.addActionListener(new SortListener(model, SortListener.sortSizeComparator));
        buttonDateSort.addActionListener(new SortListener(model, SortListener.sortDateComparator));

        panel.add(fileList);
        panel.add(textCoinc);
        panel.add(searchCoincidences);
        panel.add(buttonExplorer);
        panel.add(buttonSortAsc);
        panel.add(buttonSortDesc);
        panel.add(buttonSizeSort);
        panel.add(buttonDateSort);

        frame.add(panel);
        frame.setVisible(true);
    }
}