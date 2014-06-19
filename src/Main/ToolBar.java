package Main;

import Elements.LazyPage;
import Elements.PageChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesper on 18-06-2014.
 */
public class ToolBar extends JToolBar {

    private List<LazyPage> pages = new ArrayList<LazyPage>();
    private Saver saver;
    private PagePanel pagePanel;
    private SelectionManager manager;
    private PageChooser pageChooser;
    private PDDocument doc;

    public ToolBar(PagePanel pagePanel, SelectionManager manager) {
        this.saver = new Saver();
        this.pagePanel = pagePanel;
        this.manager = manager;
        setupSaveButton();
        setupLoadButton();
        setupPageChooser();
        //setupZoom();
        add(Box.createHorizontalGlue());
        add(new JPanel());
    }

/*    private void setupZoom() {
        Double[] d = new Double[]{50.0, 60.0, 75.0, 80.0, 90.0, 100.0, 200.0};
        final JComboBox<Double> comboBox = new JComboBox<Double>(d);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagePanel.zoom((Double) comboBox.getSelectedItem());
            }
        });
        add(comboBox);
    }*/

    private void setupPageChooser() {
        pageChooser = new PageChooser();
        pageChooser.setListener(new PageChooser.PageChooserListener() {
            @Override
            public void onNewPage(int i) {
                pagePanel.setLazyPage(pages.get(i));
            }
        });
        add(pageChooser);
    }

    private void setupLoadButton() {
        JButton b = new JButton("Load");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileFilter() {

                    @Override
                    public String getDescription() {
                        return "Portable Document Format (*.pdf)";
                    }

                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        } else {
                            return f.getName().toLowerCase().endsWith(".pdf");
                        }
                    }
                });
                int res = fileChooser.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        doc = PDDocument.loadNonSeq(file, null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    List<PDPage> docPages = doc.getDocumentCatalog().getAllPages();
                    pages.clear();
                    for(PDPage p : docPages) {
                        pages.add(new LazyPage(p));
                    }
                    pageChooser.setMax(pages.size());
                    pagePanel.setLazyPage(pages.get(0));
                    preloadPages();
                }
            }
        });
        add(b);
    }

    private void preloadPages() {
        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {
                for(LazyPage p : pages) {
                    try {
                        p.preLoadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        d.start();
    }


    private void setupSaveButton() {
        JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser() {

                    @Override
                    public void approveSelection() {
                        File f = getSelectedFile();

                        if (f.exists()) {
                            int result = JOptionPane.showConfirmDialog(this, "Do you want to overwrite the existing file?", "File already exists", JOptionPane.YES_NO_CANCEL_OPTION);

                            switch (result) {
                                case JOptionPane.YES_OPTION:
                                    super.approveSelection();
                                    return;
                                case JOptionPane.NO_OPTION:
                                    return;
                                case JOptionPane.CLOSED_OPTION:
                                    cancelSelection();
                                    return;
                                case JOptionPane.CANCEL_OPTION:
                                    cancelSelection();
                                    return;
                            }
                        }

                        super.approveSelection();
                    }
                };
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileFilter() {

                    @Override
                    public String getDescription() {
                        return "Portable Document Format (*.pdf)";
                    }

                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        } else {
                            return f.getName().toLowerCase().endsWith(".pdf");
                        }
                    }
                });
                int res = fileChooser.showSaveDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if(!file.getAbsolutePath().endsWith(".pdf")) file = new File(file.getAbsolutePath().concat(".pdf"));
                    saver.save(pagePanel.getPage(), manager.getCurrentSelection(), file.getAbsolutePath());
                }

            }
        });
        add(button);
    }
}
