/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

import chat.userData.UserData;
import java.awt.Point;
import java.awt.Rectangle;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.positioners.BalloonTipPositioner;

/**
 *
 * @author Anil
 */
public class SimpleTipPositioner extends BalloonTipPositioner {
	int x = 0; // Current X-coordinate of the top-left point of the bounding box around the balloon tip
	int y = 0; // Current Y-coordinate of the top-left point of the bounding box around the balloon tip

	/* A balloon tip will first call this method and pass his this-pointer, such that this positioner
	 * can access the balloon tip's information and set its location. */
	public void setBalloonTip(BalloonTip bT) {
		this.balloonTip = bT;
		// Set the horizontal and vertical offset of the balloon tip's style
		balloonTip.getStyle().setHorizontalOffset(3);
		balloonTip.getStyle().setVerticalOffset(7);
		// The balloon tip's style should not flip/mirror itself
		balloonTip.getStyle().flipX(false);
		balloonTip.getStyle().flipY(true);
	}

	/* The balloon tip will call this method every time it wants to re-draw itself.
	 * The parameter of this function, attached, is the Rectangle that the balloon tip should attach itself to. */
	public void determineAndSetLocation(Rectangle attached) {
		/* Calculate the coordinates of the top-left corner of the bounding box around the balloon tip
		 * This positioner will place the balloon tip above the attached Rectangle. */
		x = attached.x + (UserData.getInstance().getNamePlateSize()/2) - 4;
		y = attached.y + 22;

		// Now move the balloon tip to the position we've just calculated
		balloonTip.setBounds(x, y, balloonTip.getPreferredSize().width, balloonTip.getPreferredSize().height);
		balloonTip.validate();
	}

	// This method should return the location of the balloon's tip
	public Point getTipLocation() {
		/* You may use the last position calculated in determineAndSetLocation to calculate the tip's location.
		 * The fields x and y now contain the position of the top-left corner of the bounding box of the balloon tip. */
		return new Point(x + 20, y + balloonTip.getPreferredSize().height);
	}
}
