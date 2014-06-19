package Elements;

import org.apache.pdfbox.pdmodel.PDPage;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jesper on 6/19/2014.
 */
public class LazyPage {

    private BufferedImage image;
    private PDPage page;

    public LazyPage(PDPage page) {
        this.page = page;
    }

    public BufferedImage getImage() throws IOException {
        if(image == null) image = page.convertToImage();
        return image;
    }

    public void preLoadImage() throws IOException {
        if(image == null) image = page.convertToImage();
    }

    public PDPage getPage() {
        return page;
    }
}
