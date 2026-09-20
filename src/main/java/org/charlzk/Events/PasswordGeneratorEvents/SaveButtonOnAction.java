package org.charlzk.Events.PasswordGeneratorEvents;

import org.charlzk.Controllers.PasswordGeneratorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButtonOnAction implements ActionListener {
  private final PasswordGeneratorController passwordGeneratorController;

  public SaveButtonOnAction(PasswordGeneratorController passwordGeneratorController) {
    this.passwordGeneratorController = passwordGeneratorController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    passwordGeneratorController.saveGeneratedPassword();
  }
}
