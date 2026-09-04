package org.charlzk.Services;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class ApplicationService {
  public static void setGlobalFont(Font font) {
    Enumeration<Object> keys = UIManager.getDefaults().keys();

    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);

      if (value instanceof FontUIResource || value instanceof Font)
        UIManager.put(key, font);
    }
  }
}
