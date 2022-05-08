package snake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;

public class FileManager
{
    // URL musicLocation = getClass().getResource(fileName);
    // there's a saved_games folder

    private File rankingsFile;// = "rankings.ser";
    private File settingsFile;// = "settings.ser";

    // - - - - - [Load Functions]

    /**
     * Loads settings data into a settings object from /data/settings.ser and returns that object
     * @return
     */
    @SuppressWarnings("unchecked")
    public Settings loadSettings()
    {
        Settings settings = new Settings();

        if(settingsFile == null)
        {
            settingsFile = getFile("/data/settings.ser");
        
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
     * Loads rankings data into a rankings object from /data/rankings.ser and returns that object
     * @return rankings
     */
    @SuppressWarnings("unchecked")
    public Rankings loadRankings()
    {
        Rankings rankings = new Rankings();

        if(rankingsFile == null)
        {
            rankingsFile = getFile("/data/rankings.ser");
        
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
     * Returns loadable map string array with the names of found files in /saved_games folder
     * @return String array
     */
    public String[] getLoadableMaps()
    {
        File directory;
        File[] mapFiles;
        List<String> mapFileNames = new ArrayList<String>();

        try
        {
            directory = new File(getClass().getResource("/saved_games").toURI());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        mapFiles = directory.listFiles();

        for(int i = 0; i < mapFiles.length; i++)
        {
            mapFileNames.add(mapFiles[i].toString());
        }
            
        return mapFileNames.toArray(new String[0]);
    }

    /**
     * Returns a map object if found on the mapName param given
     * @param mapName
     */
    public GameMap loadMap(String mapName)
    {
        GameMap map = new GameMap();

        return map;
    }

    /**
     * Return a File object found at the filePath param
     * @param filePath
     * @return File if found, null otherwise
     */
    public File getFile(String filePath)
    {
        File file;

        try
        {
            file = new File(getClass().getResource(filePath).toURI());
    
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return file;
    }

    // - - - - - [Save Functions]

    /**
     * Saves settings data into the /data/settings.ser file
     * @param settings
     */
    public void saveSettings(Settings settings)
    {
        if(settingsFile == null)
            settingsFile = new File("/data/settings.ser");

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
     * Saves rankings data into the /data/rankings.ser file
     * @param rankings
     */
    public void saveRankings(Rankings rankings)
    {
        if(rankingsFile == null)
            rankingsFile = new File("/data/rankings.ser");

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
        File file;

        try
        {
            file = new File(getClass().getResource("/saved_games/" + saveName + ".ser").toURI());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        if(file.exists())
            return false;

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

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