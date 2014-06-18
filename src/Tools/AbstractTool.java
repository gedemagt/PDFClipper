package Tools;

import Elements.Axis;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Jesper on 18-06-2014.
 */
public class AbstractTool implements Tool {

    protected Axis axis;

    @Override
    public void drawTool(Graphics2D g) {

    }

    @Override
    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
