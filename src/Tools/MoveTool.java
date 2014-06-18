package Tools;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Jesper on 17-06-2014.
 */
public class MoveTool extends AbstractTool{

    int last_x;
    int last_y;
    private Rectangle rect;

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        last_x = e.getX();
        last_y = e.getY();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        rect.translate(x-last_x, y-last_y);
        last_x = x;
        last_y = y;
    }

}
