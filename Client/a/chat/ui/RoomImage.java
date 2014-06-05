/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

import chat.avatar.*;
import chat.constants.ChatConstant;
import chat.data.object.Command;
import chat.message.Responder;
import chat.userData.UserData;
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
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class RoomImage extends JPanel {
    
    private Image img;

    public RoomImage(String img) {
        try
        {
            if(!img.equals(""))                
                setImage(ImageIO.read(new URL(img)));
            addMouseMotionListener(new MouseMotionHandler());
            //Added on 5th March 09
            addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                    if(e.getButton()==e.BUTTON1)
                    {
                        LayoutManager display = LayoutManager.getInstance();

                        if(!UserData.getInstance().getIsKickedOut())
                        {
                            display.checkTimer();
                            display.changePosition(UserData.getInstance().getAvatarName(), e.getX(), e.getY());
                            display.refreshDisplay();
                            Responder.getInstance().sendMessage(Command.MOVEAVATAR, e.getX() + ChatConstant.FIRST_SEPARATOR + e.getY());
                        }
                        else
                        {
                            display.refreshDisplay();
                        }
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    Responder.getInstance().sendMessage(Command.MOVEAVATAR, e.getX() + ChatConstant.FIRST_SEPARATOR + e.getY());
                }

                public void mouseEntered(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
                }

                public void mouseExited(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet.");
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
        if(img!=null)
            g.drawImage(img, 0, 0, null);

        if(UserData.getInstance().getIsKickedOut())
        {
            try
            {
                ImagePanel kickPanel = new ImagePanel(ImageIO.read(getClass().getResource("/chat/resources/kickout.gif")));
                this.add(kickPanel);
                Dimension size = this.getPreferredSize();
                Dimension imageSize= kickPanel.getPreferredSize();
                kickPanel.setBounds((size.width - imageSize.width)/2, (size.height-imageSize.height)/2,imageSize.width, imageSize.height);
                this.setComponentZOrder(kickPanel, 0);
            }
            catch(IOException ex)
            {}
        }
        else
        {
            this.removeAll();
            AvatarHandler avatars =AvatarHandler.getInstance();
            avatars.beginIterrate();
            Avatar avatar = avatars.nextAvatar();
            AvatarImage avatarImage;
            while(avatar!=null)
            {
                avatarImage=avatar.getImage();
                this.add(avatarImage);
                Dimension size= avatarImage.getPreferredSize();
                int x = avatar.getX() - (size.width/2);

                Dimension roomSize = this.getPreferredSize();
                //Dimension roomSize = new Dimension(img.getWidth(null), img.getHeight(null));
                if(x<0)
                    x=0;
                else if(avatar.getX()>(roomSize.width-(size.width/2)))
                    x=roomSize.width - size.width;

                int y = avatar.getY() -(size.height/2);
                if(y<0)
                    y=0;
                else if(avatar.getY()>(roomSize.height-(size.height/2)))
                    y=roomSize.height - size.height-4;

                avatarImage.setBounds(x, y , size.width, size.height);
                avatar = avatars.nextAvatar();
            }
        }
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

    @Override
    public void update(Graphics g)
    {
        paintComponent(g);
    }
    
    public void refreshDisplay()
    {
        SwingUtilities.invokeLater(
            new Runnable(){
                public void run(){
                    validate();
                    repaint();
                }
            }
        );        
        //repaint();
        //SwingUtilities.updateComponentTreeUI(this);
        
        //revalidate();
    }

    class MouseMotionHandler extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            LayoutManager display = LayoutManager.getInstance();
            display.changePosition(UserData.getInstance().getAvatarName(), e.getX(), e.getY());
            display.refreshDisplay();
        }
    }
}
