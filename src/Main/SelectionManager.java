package Main;

import Elements.Selection;
import org.apache.pdfbox.pdmodel.PDPage;

import java.util.HashMap;

/**
 * Created by Jesper on 18-06-2014.
 */
public class SelectionManager {

    private HashMap<Selection, PDPage> map = new HashMap<Selection, PDPage>();
    private PagePanel page;
    private Selection currentSelection;

    public SelectionManager(PagePanel page, Saver saver) {
        this.page = page;
    }

    public void add(final Selection selection) {
        currentSelection = selection;
        map.put(selection, page.getPage());
    }

    public Selection getCurrentSelection() {
        return currentSelection;
    }
}
