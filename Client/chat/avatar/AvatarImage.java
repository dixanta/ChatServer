/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.avatar;

import chat.constants.ChatConstant;
import chat.data.object.Command;
import chat.message.Responder;
import chat.ui.LayoutManager;
import chat.userData.UserData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.FontMetrics;

/**
 *
 * @author Admin
 */
public class AvatarImage extends JPanel {
    private Image img;
    private String name;
    private AvatarType type;
    private boolean isBlocked = false;
    private boolean isIgnored = false;
    private boolean isAway = false;
    private int diffX = 0;
    private int diffY = 0;
    private int x=-1;
    private int y=-1;
    
    public AvatarImage(String img, String name,AvatarType type) {
        try
        {
            if(!img.equals(""))
                setImage(ImageIO.read(new URL(img)));
            this.name=name;
            this.type=type;
            
            addMouseMotionListener(new MouseMotionHandler());
            addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent e) {                    
                }

                public void mousePressed(MouseEvent e) {
                    diffX = e.getX();
                    diffY = e.getY();
                }

                public void mouseReleased(MouseEvent e) {
                    if(x!=-1 && y!=-1)
                        Responder.getInstance().sendMessage(Command.MOVEAVATAR, x + ChatConstant.FIRST_SEPARATOR + y);
                }

                public void mouseEntered(MouseEvent e) {
                    //diffX = e.getX();
                    //diffY = e.getY();
                }

                public void mouseExited(MouseEvent e) {
                }
            });
        }
        catch(IOException ex){
        }
    }

    private void setImage(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g) {                
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null)); //this.getPreferredSize();
        FontMetrics FM = g.getFontMetrics();
        int addedValue=4;

        if(size.width<=FM.stringWidth(name))
            size.width=FM.stringWidth(name)+addedValue;
        else if((size.width-FM.stringWidth(name))<addedValue)
        {
            size.width=FM.stringWidth(name)+addedValue;
        }
        size.height += FM.getHeight()-3;

        UserData.getInstance().setNamePlateSize(size.width);
        this.setPreferredSize(size);
        
        if(img!=null)
        {
            int x = (int)(size.getWidth()/2- img.getWidth(null)/2);
            g.drawImage(img, x, 0, null);
        }

        //------------------------------------------------------------------------------------
        //Name palate
        //------------------------------------------------------------------------------------
        if(type==AvatarType.GENERAL)
            g.setColor(new Color(249, 155, 155));
        else if(type==AvatarType.MEMBER)
            g.setColor(new Color(237, 239, 104));
        else if(type==AvatarType.MODERATOR)
            g.setColor(new Color(143, 209, 231));
        else if(type==AvatarType.SENIOR_MODERATOR)
            g.setColor(new Color(143, 209, 231));
        else if(type==AvatarType.ADMIN)
            g.setColor(Color.WHITE);        
        g.fillRoundRect((size.width-FM.stringWidth(name))/2 - 2, img.getHeight(null), FM.stringWidth(name) + 3, FM.getHeight()-4, 10, 10);
        g.setColor(Color.BLACK);
        g.drawRoundRect((size.width-FM.stringWidth(name))/2 - 2, img.getHeight(null), FM.stringWidth(name) + 3, FM.getHeight()-4, 10, 10);
        //g.setFont(new Font("Tahoma", 0, 8));
        g.drawString(name, (size.width-FM.stringWidth(name))/2, img.getHeight(null)+1+(g.getFontMetrics().getHeight())/2 + 2);
        //------------------------------------------------------------------------------------

        if(isAway)
        {
            g.setColor(Color.WHITE);
            g.fillRect((size.width-FM.stringWidth("away"))/2 - 4, img.getHeight(null)-FM.getHeight()+3,  FM.stringWidth("away") + 6, g.getFontMetrics().getHeight()-4);
            g.setColor(new Color(98, 122, 255));
            g.fillRect((size.width-FM.stringWidth("away"))/2 - 3, img.getHeight(null)-FM.getHeight()+4,  FM.stringWidth("away") + 6, g.getFontMetrics().getHeight()-4);
            g.setColor(Color.BLACK);            
            g.drawString("away", (size.width-FM.stringWidth("away"))/2, img.getHeight(null)-FM.getHeight()+13);
        }
        
        if(isBlocked)
        {
            g.setColor(Color.WHITE);
            g.fillRect((size.width-FM.stringWidth("ignored"))/2 - 4, 20, FM.stringWidth("ignored") + 6, g.getFontMetrics().getHeight()-2);
            g.setColor(Color.RED);
            g.fillRect((size.width-FM.stringWidth("ignored"))/2 - 3, 21, FM.stringWidth("ignored") + 6, g.getFontMetrics().getHeight()-2);
            g.setColor(Color.BLACK);
            g.drawString("ignored", (size.width-FM.stringWidth("ignored"))/2, 31);
        }
        
        if(isIgnored)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, (size.height/2)-13, size.width, g.getFontMetrics().getHeight()-2);
            g.setColor(Color.BLACK);            
            g.drawString("Ignore", 1, (size.height/2)-13+(g.getFontMetrics().getHeight())/2 + 3);
        }
    }
    
    public void update(Graphics g)
     {
          paintComponent(g);
     }

    public void setType(AvatarType type)
    {
        this.type = type;
        repaint();
    }

    public boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean isIsIgnored() {
        return isIgnored;
    }

    public void setIsIgnored(boolean isIgnored) {
        this.isIgnored = isIgnored;        
    }

    public boolean isIsAway() {
        return isAway;
    }

    public void setIsAway(boolean isAway) {
        this.isAway = isAway;
    }
    
    public void changePicture(String img)
    {
        try
        {
            setImage(ImageIO.read(new URL(img)));
            repaint();
        }
        catch(IOException ex){}
    }
    
    class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if(UserData.getInstance().getAvatarName().equals(name))
            {
                x = (int)getParent().getMousePosition().getX();
                y = (int)getParent().getMousePosition().getY();
                x -= diffX;
                y -= diffY;
                LayoutManager display = LayoutManager.getInstance();
                display.checkTimer();
                display.changePosition(name, x + (img.getWidth(null)/2), y + (img.getHeight(null)/2));
                display.refreshDisplay();
            }
        }
    }
}
