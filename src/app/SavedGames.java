//Adapted from the book, Ch. 15, Fig. 15.10

package app;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class SavedGames {
    @XmlElement(name="savedGames")
    private List<SavedGame> savedGames = new ArrayList<>();

    public List<SavedGame> getSavedGames() {return savedGames;}
}
