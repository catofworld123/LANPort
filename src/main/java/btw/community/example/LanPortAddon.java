package btw.community.example;

import btw.AddonHandler;
import btw.BTWAddon;

public class LanPortAddon extends BTWAddon {
    private static LanPortAddon instance;

    public LanPortAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}