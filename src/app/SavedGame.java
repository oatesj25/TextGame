package app;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SavedGame {
    private SimpleStringProperty name;
    private SimpleIntegerProperty sceneNo;
    private SimpleStringProperty dateSaved;

    public SavedGame(String name, int sceneNo) {
        this.name = new SimpleStringProperty(name);
        this.sceneNo = new SimpleIntegerProperty(sceneNo);

        //code to get current date & time and format it to string and then SimpleStringProperty
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy  HH:mm a");
        String formattedDateTime = currentTime.format(formatter);
        this.dateSaved = new SimpleStringProperty(formattedDateTime);

        System.out.println("new SavedGame object created.");
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getSceneNo() {
        return sceneNo.get();
    }

    public SimpleIntegerProperty sceneNoProperty() {
        return sceneNo;
    }

    public void setSceneNo(int sceneNo) {
        this.sceneNo.set(sceneNo);
    }

    public String getDateSaved() {
        return dateSaved.get();
    }

    public void setDateSaved(String dateSaved) {
        this.dateSaved.set(dateSaved);
    }
}