/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

import java.awt.AWTEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JMenuItem;

/**
 *
 * @author Anil
 */
public class CustomMenuItem extends JMenuItem {

    private static final Font MENU_FONT = new Font("Tahoma1", Font.PLAIN, 12);
    private static final Font HEADER_FONT = new Font("Tahoma1", Font.BOLD, 12);
    private boolean  isMainMenu = false;
    public final int ACTIVE=1,IDLE=0,PRESSED=2;
    public int state;
    private ActionListener lst;

    public CustomMenuItem(String text, boolean  isMainMenu)
    {
        super(text);
        if(isMainMenu)
            this.setEnabled(false);        
        this.isMainMenu = isMainMenu;
        this.enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    @Override
    protected final void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.drawImage(getBackgroundImage(this.getWidth(), this.getHeight()), 0, 0, null);

        /*graphics.drawImage(ImageCreator.getGradientCubesImage(
            this.getWidth(), this.getHeight(),
            ImageCreator.mainMidColor,
            ImageCreator.mainUltraDarkColor,
            (int) (0.7 * this.getWidth()),
            (int) (0.9 * this.getWidth())), 0, 0, null);*/
        
        /*graphics.setColor(Color.black);
        graphics.setFont(MENU_FONT);
        graphics.drawString(this.getText(), 1, 1);*/

        
        // place the text slightly to the left of the center
        //int x = (this.getWidth() - graphics.getFontMetrics().stringWidth(this.getText())) / 4;
        int y = (int)(graphics.getFontMetrics().getLineMetrics(
        this.getText(), graphics).getHeight());

        // paint the text with black shadow
        graphics.setColor(Color.black);
        if(isMainMenu)
        {
            graphics.setFont(HEADER_FONT);
            graphics.drawString(this.getText(), (this.getWidth() - graphics.getFontMetrics().stringWidth(
                            this.getText())) / 2, y+1);
        }
        else
        {
            if(state == ACTIVE)
                graphics.setColor(Color.WHITE);
            graphics.setFont(MENU_FONT);
            graphics.drawString(this.getText(), 5, y+1);
        }
        //graphics.setColor(Color.white);
        //graphics.drawString(this.getText(), x, y);

        //super.paintComponent(graphics);
    }

    private BufferedImage getBackgroundImage(int width, int height)
    {
        BufferedImage image = new BufferedImage(width, height,
                                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(state == ACTIVE)
            graphics.setColor(Color.BLUE);
        else
            graphics.setColor(new Color(143, 209, 231));
        graphics.fillRect(0, 0, width, height);
        return image;
    }

    public void released()
    {
        int a=state;
        state=ACTIVE;
        if(a != state) repaint();
        postAction();
    }

    public void pressed()
    {
        int a=state;
        state=PRESSED;
        if(a != state) repaint();
    }

    public void entered()
    {
        int a=state;
        state=ACTIVE;
        if(a != state) repaint();
    }

    public void exited()
    {
        int a=state;
        state=IDLE;
        if(a != state) repaint();
    }

    public void processEvent(AWTEvent e)
    {
        if(e.getID() == MouseEvent.MOUSE_PRESSED) pressed();
        else if(e.getID() == MouseEvent.MOUSE_ENTERED) entered();
        else if(e.getID() == MouseEvent.MOUSE_EXITED) exited();
        else if(e.getID() == MouseEvent.MOUSE_RELEASED) released();
        super.processEvent(e);
    }

    private void postAction()
    {
        if(lst != null)
        {
            ActionEvent event = new
            ActionEvent(this,ActionEvent.ACTION_PERFORMED, getText()) ;
            lst.actionPerformed(event);
        }
    }

    public void addActionListener(ActionListener l)
    {
        lst=AWTEventMulticaster.add(lst,l);
    }

    public void removeActionListener(ActionListener l)
    {
        lst=AWTEventMulticaster.remove(lst,l);
    }
}
