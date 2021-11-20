package packtpub.automation.managers;
import packtpub.automation.util.ConfigFileReader;

//This class contains methods related to file operations and creates singleton instance of file reader manager
public class FileReaderManager {

    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigFileReader configFileReader;
    private FileReaderManager() {
    }

    public static FileReaderManager getInstance( ) {
        return fileReaderManager;
    }

    public ConfigFileReader getConfigReader() {
        return (configFileReader == null) ? configFileReader = new ConfigFileReader() : configFileReader;
    }


}
