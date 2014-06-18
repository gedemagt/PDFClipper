package Tools;

import Elements.Axis;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Jesper on 17-06-2014.
 */
public interface Tool extends MouseListener, MouseMotionListener{

    public void drawTool(Graphics2D g);

    public void setAxis(Axis axis);

}
