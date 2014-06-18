import Elements.DefaultAxis;
import Main.*;
import Tools.MarkerTool;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jesper on 17-06-2014.
 */
public class Launcher {

    static PDDocument doc;
    public static void main(String[] args) throws IOException, COSVisitorException {



        JPanel panel = new JPanel();

        PagePanel page = new PagePanel();
        ToolLayer toolLayer = new ToolLayer(page);
        SelectionManager m = new SelectionManager(page, new Saver());
        toolLayer.setTool(new MarkerTool(m));
        panel.add(toolLayer);
        JScrollPane scrollPane = new JScrollPane(panel);

        JFrame frame = new JFrame();
        frame.getContentPane().add(new ToolBar(page, m), BorderLayout.NORTH);
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
