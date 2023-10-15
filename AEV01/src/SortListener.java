import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class SortListener implements ActionListener {
    public static final Comparator<String> sortAscComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };
    public static final Comparator<String> sortDescComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return -o1.compareTo(o2);
        }
    };
    public static final Comparator<String> sortSizeComparator = new Comparator<String>() {
        @Override

        public int compare(String o1, String o2) {

            o1 = o1.replaceAll("\\s+", "");
            o2 = o2.replaceAll("\\s+", "");

            Scanner sizeScanner1st = new Scanner(o1).useDelimiter("\\|");
            Scanner sizeScanner2nd = new Scanner(o2).useDelimiter("\\|");

            //Scan hasta el valor necesario o1: Size
            sizeScanner1st.next();
            sizeScanner1st.next();
            sizeScanner1st.next();
            int size1st = sizeScanner1st.nextInt();

            //Scan hasta el valor necesario de o2: Size
            sizeScanner2nd.next();
            sizeScanner2nd.next();
            sizeScanner2nd.next();
            int size2nd = sizeScanner2nd.nextInt();
            return size1st-size2nd;
        }
    };
    public static final Comparator<String> sortDateComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {

            Date date1;
            Date date2;
            o1 = o1.replaceAll("\\s+", "");
            o2 = o2.replaceAll("\\s+", "");

            Scanner dateScanner1st = new Scanner(o1).useDelimiter("\\|");
            Scanner dateScanner2nd = new Scanner(o2).useDelimiter("\\|");

            //Scan hasta el valor necesario o1: Date
            dateScanner1st.next();
            dateScanner1st.next();
            String date1st = dateScanner1st.next();

            //Scan hasta el valor necesario de o2: Date
            dateScanner2nd.next();
            dateScanner2nd.next();
            String date2nd = dateScanner2nd.next();

            SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyyHH:mm:ss");
            try {
                date1 = parser.parse(date1st);
                date2 = parser.parse(date2nd);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return date1.compareTo(date2);
        }
    };

    private final DefaultListModel<String> model;
    private final Comparator<String> comparator;
    public SortListener(DefaultListModel<String> model, Comparator<String> comparator) {
        this.model = model;
        this.comparator = comparator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] elements = new String[model.size()];
        for (int i = 0 ; i < model.size() ; i++) {
            elements[i] = model.get(i);
        }
        Arrays.sort(elements, comparator);
        model.clear();

        for (String element: elements) {
            model.addElement(element);
        }
    }
}