//Adapted from the book, Ch. 15, Fig. 15.10

package app;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class GameScenes {
        @XmlElement(name="gameScene")
        private List<GameScene> gameScenes = new ArrayList<>();

        public List<GameScene> getAccounts() {return gameScenes;}
}
