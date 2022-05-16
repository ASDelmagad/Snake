package snake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;

public class FileManager
{
    // URL musicLocation = getClass().getResource(fileName);
    // there's a saved_games folder

    private File rankingsFile;// = "rankings.ser";
    private File settingsFile;// = "settings.ser";

    private Game game;

    public FileManager(Game game)
    {
        this.game = game;
    }

    // - - - - - [Load Functions]

    /**
     * Loads settings data into a settings object from settings.ser and returns that object
     * @return
     */
    @SuppressWarnings("unchecked")
    public Settings loadSettings()
    {
        Settings settings = new Settings();

        if(settingsFile == null)
        {
            settingsFile = getFile("data/settings.ser");
        
            if(settingsFile == null)
                return settings;
        }

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        try
        {
            fileInputStream = new FileInputStream(settingsFile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            settings.setSettings((HashMap<String, Object>)objectInputStream.readObject());

            objectInputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return settings;
    }

    /**
     * Loads rankings data into a rankings object from rankings.ser and returns that object
     * @return rankings
     */
    @SuppressWarnings("unchecked")
    public Rankings loadRankings()
    {
        Rankings rankings = new Rankings();

        if(rankingsFile == null)
        {
            rankingsFile = getFile("data/rankings.ser");
        
            if(rankingsFile == null)
                return rankings;
        }

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        try
        {
            fileInputStream = new FileInputStream(rankingsFile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            rankings.setRankings((Collection<Rank>)objectInputStream.readObject());

            objectInputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return rankings;
    }

    /**
     * Returns loadable map arraylist with file objects in /saved_games folder
     * @return String array
     */
    public ArrayList<GameMap> getLoadableMaps()
    {
        File directory;
        ArrayList<GameMap> mapObjects = new ArrayList<GameMap>();

        try
        {
            directory = new File("saved_games");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ArrayList<GameMap>();
        }

        for(File file : directory.listFiles())
        {
            if(!file.getName().startsWith("saveGame"))
                continue;
            
            GameMap loadedMap = (GameMap)loadMap(file);
            
            if(loadedMap == null)
                continue;

            mapObjects.add(loadedMap);
        }
            
        return mapObjects;
    }

    /**
     * Returns a map object if found on the mapName param given
     * @param mapName
     */
    public GameMap loadMap(File mapFile)
    {
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        GameMap loadedMap;

        try
        {
            fileInputStream = new FileInputStream(mapFile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            loadedMap = (GameMap)objectInputStream.readObject();

            objectInputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        loadedMap.game = this.game;
        return loadedMap;
    }

    /**
     * Return a File object found at the filePath param
     * @param filePath
     * @return File if found, null otherwise
     */
    public File getFile(String filePath)
    {
        File file = new File(filePath);

        if(file.exists())
            return file;
        
        return null;
    }

    // - - - - - [Save Functions]

    /**
     * Saves settings data into the /data/settings.ser file
     * @param settings
     */
    public void saveSettings(Settings settings)
    {
        if(settingsFile == null)
            settingsFile = new File("data/settings.ser");

        if(!settingsFile.exists())
            try
            {
                settingsFile.mkdirs();
                if(!settingsFile.createNewFile())
                    return;
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }

        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        try
        {
            fileOutputStream = new FileOutputStream(settingsFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(settings);

            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Saves rankings data into the data/rankings.ser file
     * @param rankings
     */
    public void saveRankings(Rankings rankings)
    {
        if(rankingsFile == null)
            rankingsFile = new File("data/rankings.ser");
        
        if(!rankingsFile.exists())
            try
            {
                rankingsFile.mkdirs();
                if(!rankingsFile.createNewFile())
                    return;
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }

        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        try
        {
            fileOutputStream = new FileOutputStream(rankingsFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(rankings);

            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Saves a map into the /saved_games/ directory
     * @param map   The map to be saved
     * @param saveName  The name of the map to be saved as
     * @return false if file's already exists or exception connected to the file occures
     */
    public boolean saveMap(GameMap map, String saveName)
    {
        File file = new File("saved_games/saveGame_" + saveName + ".ser");
        
        try
        {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.useProtocolVersion(ObjectOutputStream.PROTOCOL_VERSION_2);

            objectOutputStream.writeObject(map);

            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
        return true;
    }
}