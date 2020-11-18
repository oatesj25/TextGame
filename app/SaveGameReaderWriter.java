package app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveGameReaderWriter {

    private List<SavedGame> savedGamesList = new ArrayList<SavedGame>();

    public SaveGameReaderWriter() throws IOException {
        savedGamesList = loadSavedGamesList();
    }

    public List<SavedGame> loadSavedGamesList() throws IOException {
        //reading saved games from json file
        Type typeToken = new TypeToken<ArrayList<SavedGame>>(){}.getType();
        String json = new String(Files.readAllBytes(Paths.get("src/app/savedGames.json")), StandardCharsets.UTF_8);
        savedGamesList = new Gson().fromJson(json, typeToken);
        return savedGamesList;
    }

    public void saveGameData (List<SavedGame> savedGameList) {
        //writing to json file. saves user progress.
        Gson gson = new Gson();
        String json = gson.toJson(savedGameList);
        try(FileWriter fileWriter = new FileWriter("src/app/savedGames.json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNewGame(SavedGame sg) {
        savedGamesList.add(0, sg);
        saveGameData(savedGamesList);
    }

    public void overwriteSavedGame(SavedGame updatedGame, int overwriteIndex) {
        savedGamesList.remove(overwriteIndex);
        saveNewGame(updatedGame);
    }

}
