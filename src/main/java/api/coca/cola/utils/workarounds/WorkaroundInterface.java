package api.coca.cola.utils.workarounds;

import java.io.FileNotFoundException;

public interface WorkaroundInterface {

    void putAppInBackground(int seconds) throws FileNotFoundException;

    void closeApp() throws FileNotFoundException, InterruptedException;

    void reviveApp() throws FileNotFoundException;
}
