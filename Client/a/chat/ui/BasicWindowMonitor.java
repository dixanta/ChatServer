/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

/**
 *
 * @author Admin
 */
import java.awt.event.*;
import java.awt.Window;

public class BasicWindowMonitor extends WindowAdapter {

  public void windowClosing(WindowEvent e) {
    Window w = e.getWindow();
    w.setVisible(false);
    w.dispose();
    System.exit(0);
  }
}

