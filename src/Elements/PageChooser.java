package Elements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jesper on 18-06-2014.
 */
public class PageChooser extends JPanel {

    private JButton left, right;
    private JTextField text;

    private int number = 0;
    private int max_number = 0;
    private PageChooserListener listener;

    public PageChooser() {
        left = new JButton("<");
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(number>1) {
                    number--;
                    listener.onNewPage(number);
                    text.setText(number+"");
                }
            }
        });
        right = new JButton(">");
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(number<max_number-1) {
                    number++;
                    listener.onNewPage(number);
                    text.setText(number+"");
                }
            }
        });
        text = new JTextField();
        text.setColumns(2);
        add(left);
        add(text);
        add(right);
        text.setEditable(false);
        text.setText(number + "");
    }

    public void setMax(int max){
        max_number = max;
        if(number >= max) {
            number = max;
            listener.onNewPage(number);
            text.setText(number+"");
        }
        repaint();
    }

    public void setListener(PageChooserListener listener) {
        this.listener = listener;
    }

    public interface PageChooserListener {
        public void onNewPage(int i);
    }

}
