package Elements;

import org.apache.pdfbox.pdmodel.PDPage;

import java.awt.*;

/**
 * Created by Jesper on 17-06-2014.
 */
public interface Selection {

    public PDPage crop(PDPage page);

    public void draw(Graphics g);
}
