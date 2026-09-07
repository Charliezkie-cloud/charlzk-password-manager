package org.charlzk.Events.RegistrationEvents;

import org.charlzk.Views.TermsOfUseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TermsOfUseButtonOnAction implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    TermsOfUseView termsOfUseView = new TermsOfUseView();
    termsOfUseView.setVisible(true);
  }
}
