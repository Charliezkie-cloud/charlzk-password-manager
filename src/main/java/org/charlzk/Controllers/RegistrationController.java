package org.charlzk.Controllers;

import javax.swing.*;

import org.charlzk.Events.*;

public class RegistrationController {
  public RegistrationController(
          // Legal components
          JButton termsOfUseButton
  ) {
    termsOfUseButton.addActionListener(new termsOfUseButtonOnAction());
  }
}
