package Elements;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.PDFCloneUtility;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Jesper on 17-06-2014.
 */
public class PDSelection extends PDRectangle implements Selection {
    @Override
    public PDPage crop(PDPage page) {
        PDPage re = new PDPage(page.getCOSDictionary());
        re.setCropBox(this);
        re.setTrimBox(this);
        re.setMediaBox(this);
        re.setBleedBox(this);
        re.setArtBox(this);
        return re;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle rect = new Rectangle(createDimension());
        g2.draw(rect);
    }
}
