package org.charlzk.Services;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

public class ApplicationService {
  private static final String APP_FOLDER_NAME = "Charlzk Password Manager";

  public static void setGlobalFont(Font font) {
    Enumeration<Object> keys = UIManager.getDefaults().keys();

    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);

      if (value instanceof FontUIResource || value instanceof Font)
        UIManager.put(key, font);
    }
  }

  public static Path getAppDataDir() {
    String appData = System.getenv("APPDATA");

    if (appData == null)
      appData = System.getProperty("user.home");

    return Paths.get(appData, APP_FOLDER_NAME);
  }

  public static Path ensureAppDataDir() throws IOException {
    Path dir = getAppDataDir();

    if (!Files.exists(dir))
      Files.createDirectories(dir);

    return dir;
  }
}
