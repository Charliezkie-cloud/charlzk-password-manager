package org.charlzk.Events.LoginEvents;

import org.charlzk.Controllers.LoginController;
import org.charlzk.Views.RegistrationView;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class RegisterPaneHyperlinkListener implements HyperlinkListener {
  private final LoginController loginController;

  public RegisterPaneHyperlinkListener(LoginController loginController) {
    this.loginController = loginController;
  }

  @Override
  public void hyperlinkUpdate(HyperlinkEvent e) {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      if (e.getDescription().equals("register")) {
        loginController.getLoginView().dispose();
        RegistrationView registrationView = new RegistrationView();
        registrationView.setVisible(true);
      }
    }
  }
}
