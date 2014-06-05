/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Admin
 */
public class MessageHistory extends JFrame{
    JTextArea ta;
    
    public MessageHistory() {
        super("Message history");
        setSize(500,400);
        addWindowListener(new BasicWindowMonitor());
        getContentPane().add(
            new JLabel("Message History"),
            BorderLayout.NORTH);
        ta = new JTextArea();
        ta.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(ta);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    
    public void addText(String text)
    {
        if(ta.getText().trim().equals(""))
            ta.setText(text);
        else
            ta.setText(ta.getText() + "\n" + text);
    }
}
