package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SavedGame {
    private String name;
    private int sceneNo;
    private String dateSaved;

    public SavedGame(String name, int sceneNo) {
        this.name = name;
        this.sceneNo = sceneNo;

        //code to get current date & time and format it to string and then SimpleStringProperty
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy  HH:mm a");
        String formattedDateTime = currentTime.format(formatter);
        this.dateSaved = formattedDateTime;

        System.out.println("new SavedGame object created.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public int getSceneNo() {
        return sceneNo;
    }

    public void setSceneNo(int sceneNo) {
        this.sceneNo = sceneNo;
    }

    public String getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(String dateSaved) {
        this.dateSaved = dateSaved;
    }
}