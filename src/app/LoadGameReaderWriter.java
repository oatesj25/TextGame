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

public class LoadGameReaderWriter {

    private SavedGame sessionToLoad;

    public SavedGame loadGameSession() throws IOException {
        //reading saved games from json file
        Type typeToken = new TypeToken<SavedGame>(){}.getType();
        String json = new String(Files.readAllBytes(Paths.get("src/app/gameSessionToLoad.json")), StandardCharsets.UTF_8);
        sessionToLoad = new Gson().fromJson(json, typeToken);
        return sessionToLoad;
    }

    public void updateSessionToLoad(SavedGame sg) {
        //writing to json file. saves user progress.
        Gson gson = new Gson();
        String json = gson.toJson(sg);
        try(FileWriter fileWriter = new FileWriter("src/app/gameSessionToLoad.json")) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
