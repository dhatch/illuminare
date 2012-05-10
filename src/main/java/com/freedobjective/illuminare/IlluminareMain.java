package com.freedobjective.illuminare;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;

public class IlluminareMain {
    
    public static IlluminareMain app;
    
    public IlluminareMain() throws LWJGLException {
        // Initialize display
        DisplayMode m = Display.getDesktopDisplayMode();
        for (DisplayMode mode: Display.getAvailableDisplayModes()) {
            // Select this mode to start with
            if (mode.getWidth() == 1024 && mode.getHeight() == 768) {
                m = mode;
                break;
            }
        }
        Display.setDisplayMode(m);
        Display.setTitle("Illuminare");
        Display.create();
    }
    
    public static void main(String[] args) throws LWJGLException {
        IlluminareMain app = getApp();
    }
    
    public static IlluminareMain getApp() throws LWJGLException {
        if (app == null) {
            app = new IlluminareMain();
        }
        return app;
    }
    
}
