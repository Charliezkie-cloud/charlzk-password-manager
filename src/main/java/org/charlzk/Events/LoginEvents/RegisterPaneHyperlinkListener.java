package org.charlzk.Events.LoginEvents;

import org.charlzk.Views.RegistrationView;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class RegisterPaneHyperlinkListener implements HyperlinkListener {
  private final JFrame loginView;

  public RegisterPaneHyperlinkListener(JFrame loginView) {
    this.loginView = loginView;
  }

  @Override
  public void hyperlinkUpdate(HyperlinkEvent e) {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      switch (e.getDescription()) {
        case "register":
          loginView.dispose();
          RegistrationView registrationView = new RegistrationView();
          registrationView.setVisible(true);
          break;
      }
    }
  }
}
