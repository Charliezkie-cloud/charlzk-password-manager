package org.charlzk.Events.SettingsEvents;

import org.charlzk.Controllers.SettingsController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveThemeButtonOnAction implements ActionListener {
  private final SettingsController settingsController;

  public SaveThemeButtonOnAction(SettingsController settingsController) {
    this.settingsController = settingsController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    settingsController.saveTheme();
  }
}
