package Main;

import Elements.LazyPage;
import org.apache.pdfbox.pdfviewer.PDFPagePanel;
import org.apache.pdfbox.pdfviewer.PageDrawer;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by Jesper on 17-06-2014.
 */
public class PagePanel extends JPanel {

    private PDPage page;
    private BufferedImage image;
    private Image showImage;

    public void setListener(PagePanelListener listener) {
        this.listener = listener;
    }

    private PagePanelListener listener;

    public PagePanel() {

        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.BLACK));
        setPreferredSize(PDPage.PAGE_SIZE_A4.createDimension());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(page != null) {
            g.drawImage(showImage, 0, 0, null);
        }

    }

    public void setPage(PDPage page) {
        this.page = page;
        try {
            image = page.convertToImage();
            showImage = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        listener.onNewPage(this, page);
        revalidate();
    }

    public void setLazyPage(LazyPage page) {
        this.page = page.getPage();
        try {
            image = page.getImage();
            showImage = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        listener.onNewPage(this, this.page);
        revalidate();
        repaint();
    }

/*    public void zoom(double x) {
        int newHeight = (int) Math.floor((double) image.getHeight()*x/100.0);
        int newWidth = (int) Math.floor((double) image.getWidth()*x/100.0);
        showImage = image.getScaledInstance(newWidth, newHeight,2);
        setPreferredSize(new Dimension(newWidth, newHeight));
        repaint();
    }*/

    public PDPage getPage() {
        return page;
    }
}
