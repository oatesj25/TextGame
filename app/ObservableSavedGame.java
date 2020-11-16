package app;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ObservableSavedGame {
    private SimpleStringProperty observableName;
    private SimpleIntegerProperty observableSceneNo;
    private SimpleStringProperty observableDateSaved;

    public ObservableSavedGame(SavedGame sg) {
        this.observableName = new SimpleStringProperty(sg.getName());
        this.observableSceneNo = new SimpleIntegerProperty(sg.getSceneNo());
        this.observableDateSaved = new SimpleStringProperty(sg.getDateSaved());
    }

    public void setObservableName(String observableName) {
        this.observableName.set(observableName);
    }

    public String getObservableName() {
        return observableName.get();
    }

    public void setObservableSceneNo(int observableSceneNo) {
        this.observableSceneNo.set(observableSceneNo);
    }

    public int getObservableSceneNo() {
        return observableSceneNo.get();
    }

    public void setObservableDateSaved(String observableDateSaved) {
        this.observableDateSaved.set(observableDateSaved);
    }

    public String getObservableDateSaved() {
        return observableDateSaved.get();
    }
}

