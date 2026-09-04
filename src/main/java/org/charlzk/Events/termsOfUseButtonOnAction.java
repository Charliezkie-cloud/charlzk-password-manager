package org.charlzk.Events;

import org.charlzk.Views.TermsOfUseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class termsOfUseButtonOnAction implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    TermsOfUseView termsOfUseView = new TermsOfUseView();
    termsOfUseView.setVisible(true);
  }
}
