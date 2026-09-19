package org.charlzk.Events.RegistrationEvents;

import org.charlzk.Views.NoAuthViews.PrivacyView;
import org.charlzk.Views.NoAuthViews.TermsOfUseView;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class TermsOfUsePaneHyperlinkListener implements HyperlinkListener {
  @Override
  public void hyperlinkUpdate(HyperlinkEvent e) {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      switch (e.getDescription()) {
        case "terms":
          new TermsOfUseView().setVisible(true);
          break;
        case "privacy":
          new PrivacyView().setVisible(true);
          break;
      }
    }
  }
}
