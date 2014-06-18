package Tools;

import Elements.PDSelection;
import Main.SelectionManager;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by Jesper on 17-06-2014.
 */
public class MarkerTool extends AbstractTool {

    private int x_anchor;
    private int y_anchor;

    private int x_lead;
    private int y_lead;

    private PDSelection selection;
    private SelectionManager manager;

    public MarkerTool(SelectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        x_anchor = e.getX();
        y_anchor = e.getY();
        selection = new PDSelection();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        x_lead = e.getX();
        y_lead = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x_lead = e.getX();
        y_lead = e.getY();
        selection.setLowerLeftY(axis.yToFloat(y_lead));
        selection.setLowerLeftX(axis.xToFloat(x_anchor));
        selection.setUpperRightY(axis.yToFloat(y_anchor));
        selection.setUpperRightX(axis.xToFloat(x_lead));
        manager.add(selection);
    }

    @Override
    public void drawTool(Graphics2D g) {
        g.drawRect(x_anchor, y_anchor, x_lead - x_anchor, y_lead - y_anchor);
    }
}
