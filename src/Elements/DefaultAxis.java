package Elements;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.*;

/**
 * Created by Jesper on 18-06-2014.
 */
public class DefaultAxis implements Axis {

    private Dimension dim;
    private PDRectangle rect;

    public DefaultAxis(Dimension dim, PDRectangle rect) {
        this.rect = rect;
        this.dim = dim;
    }

    @Override
    public float xToFloat(int x) {
        return ((float) x / (float) dim.getWidth())*rect.getWidth();
    }

    @Override
    public float yToFloat(int y) {
        return ((float) (dim.height - y) / (float) dim.getHeight())*rect.getHeight();
    }

    @Override
    public int xToPixel(float x) {
        return 0;
    }

    @Override
    public int yToPixel(float y) {
        return 0;
    }
}
