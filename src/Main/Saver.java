package Main;

import Elements.Selection;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jesper on 18-06-2014.
 */
public class Saver {


    public Saver() {
    }

    public void save(PDPage p, final Selection selection, String dest) {

        PDDocument doc = null;
        p = selection.crop(p);
        try {
            doc = new PDDocument();
            doc.addPage(p);
            doc.save(dest);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
    }

}

