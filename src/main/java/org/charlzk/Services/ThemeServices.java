package org.charlzk.Services;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class ThemeServices {
  public static final String THEME_DARK = "Dark";
  public static final String THEME_LIGHT = "Light";

  private static final String SETTINGS_FILE_NAME = "settings.properties";
  private static final String THEME_PROPERTY_KEY = "theme";

  public static String getSavedTheme() {
    Path settingsFile = ApplicationServices.getAppDataDir().resolve(SETTINGS_FILE_NAME);

    if (Files.exists(settingsFile)) {
      Properties properties = new Properties();
      try (InputStream in = Files.newInputStream(settingsFile)) {
        properties.load(in);
        String theme = properties.getProperty(THEME_PROPERTY_KEY);
        if (theme != null && (theme.equalsIgnoreCase(THEME_LIGHT) || theme.equalsIgnoreCase(THEME_DARK))) {
          return theme.equalsIgnoreCase(THEME_LIGHT) ? THEME_LIGHT : THEME_DARK;
        }
      } catch (IOException ignored) {}
    }

    return THEME_DARK;
  }

  public static void saveTheme(String theme) throws IOException {
    Path appDataDir = ApplicationServices.ensureAppDataDir();
    Path settingsFile = appDataDir.resolve(SETTINGS_FILE_NAME);
    Properties properties = new Properties();

    if (Files.exists(settingsFile)) {
      try (InputStream in = Files.newInputStream(settingsFile)) {
        properties.load(in);
      } catch (IOException ignored) {}
    }

    properties.setProperty(THEME_PROPERTY_KEY, theme);

    try (OutputStream out = Files.newOutputStream(settingsFile)) {
      properties.store(out, null);
    }
  }

  public static void applyTheme(String theme) {
    if (THEME_LIGHT.equalsIgnoreCase(theme)) {
      FlatMacLightLaf.setup();
    } else {
      FlatMacDarkLaf.setup();
    }

    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));
    FlatLaf.updateUI();
  }
}
