package Main;

import org.apache.pdfbox.pdmodel.PDPage;

/**
 * Created by Jesper on 18-06-2014.
 */
public interface PagePanelListener {

    public void onNewPage(PagePanel panel, PDPage page);
}
