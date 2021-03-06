package server.gui.actions;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import server.gui.panels.AboutPanel;
import server.gui.panels.ComboControl;
import server.gui.panels.HamburgerMenu;
import server.gui.panels.MenuBarPanel;
import server.gui.panels.SignalMenu;

/**
 * This class is responsible for handling Mouse Events triggered from the server
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 04APR2018
 *
 */
public class MouseEvents implements MouseListener {
  Component actionClass;
  String switcher;

  public MouseEvents(Component actionClass) {
    this.switcher = "";
    this.actionClass = actionClass;
  }

  public MouseEvents(Component actionClass, String switcher) {
    this.switcher = switcher;
    this.actionClass = actionClass;
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    JLabel label = null;
    if (arg0.getSource() instanceof JLabel) {
      label = (JLabel) arg0.getSource();
    }

    if (actionClass instanceof ComboControl) {
      if (label.getText().trim() == "v")
        ((ComboControl) actionClass).decrementOutputText(switcher);
      else
        ((ComboControl) actionClass).incrementOutputText(switcher);
    } else if (actionClass instanceof HamburgerMenu) {
      ((HamburgerMenu) actionClass).triggerActionEvent(arg0.getY());
    } else if (actionClass instanceof SignalMenu) {
      ((SignalMenu) actionClass).triggerActionEvent(arg0.getY());
    } else if (actionClass instanceof MenuBarPanel) {
      String icon = label.getIcon().toString();
      if (icon.contains("menu.png")) {
        ((MenuBarPanel) actionClass).showExitMenu();
      } else if (icon.contains("strong.png") || icon.contains("weak.png")) {
        ((MenuBarPanel) actionClass).showSignalMenu();
      }
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    JLabel label = null;
    if (arg0.getSource() instanceof JLabel) {
      label = (JLabel) arg0.getSource();
    }

    if (actionClass instanceof ComboControl) {
      ((ComboControl) actionClass).showHideBorder(label, true);
    } else if (actionClass instanceof MenuBarPanel) {
      ((MenuBarPanel) actionClass).showHideBorder(label, true);
    }
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    JLabel label = null;
    if (arg0.getSource() instanceof JLabel) {
      label = (JLabel) arg0.getSource();
    }

    if (actionClass instanceof ComboControl) {
      ((ComboControl) actionClass).showHideBorder(label, false);
    } else if (actionClass instanceof HamburgerMenu) {
      ((HamburgerMenu) actionClass).setVisibleFalse(arg0.getComponent());
    } else if (actionClass instanceof SignalMenu && switcher == "signal") {
      ((SignalMenu) actionClass).setVisibleFalse(arg0.getComponent());
    } else if (actionClass instanceof MenuBarPanel && switcher == "hamburger") {
      ((MenuBarPanel) actionClass).showHideBorder(label, false);
    } else if (actionClass instanceof AboutPanel && switcher == "about") {
      ((AboutPanel) actionClass).disposeScrollPane();
    }
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
  }
}
