package org.charlzk.Events.PasswordGeneratorEvents;

import org.charlzk.Controllers.PasswordGeneratorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateButtonOnAction implements ActionListener {
  private final PasswordGeneratorController passwordGeneratorController;

  public GenerateButtonOnAction(PasswordGeneratorController passwordGeneratorController) {
    this.passwordGeneratorController = passwordGeneratorController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    passwordGeneratorController.generatePassword();
  }
}
