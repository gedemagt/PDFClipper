package Main;

import Elements.Axis;
import Elements.DefaultAxis;
import Tools.NullTool;
import Tools.Tool;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Jesper on 17-06-2014.
 */
public class ToolLayer extends JPanel {

    private Tool tool = new NullTool();
    private Tool nullTool = tool;
    private Axis axis;
    private JPanel tl = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            tool.drawTool(g2);
        }
    };

    public ToolLayer(PagePanel panel) {
        JLayeredPane layered = new JLayeredPane();
        layered.setLayout(new OverlayLayout(layered));

        panel.setListener(new PagePanelListener() {
            @Override
            public void onNewPage(PagePanel panel, PDPage page) {
                axis = new DefaultAxis(panel.getPreferredSize(), page.getMediaBox());
                tool.setAxis(axis);
            }
        });
        tl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tool.mouseClicked(e);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                tool.mousePressed(e);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                tool.mouseReleased(e);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                tool.mouseEntered(e);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tool.mouseExited(e);
                repaint();
            }
        });

        tl.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                tool.mouseDragged(e);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                tool.mouseMoved(e);
                repaint();
            }
        });
        tl.setOpaque(false);
        layered.add(panel, Integer.valueOf(0), 0);
        layered.add(tl, Integer.valueOf(1), 0);
        add(layered);
    }

    public void setTool(Tool tool) {
        if(tool == null) this.tool = nullTool;
        else this.tool = tool;
        tool.setAxis(axis);
    }

}
