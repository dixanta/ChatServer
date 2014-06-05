/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.ui;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;

/**
 *
 * @author Anil
 */
public class FixedSizePlainDocument extends PlainDocument {

	private static final long serialVersionUID = 5017482169716902038L;

	private int maxCharacterSize;

	public FixedSizePlainDocument( int maxCharacterSize ) {
		super();
		this.maxCharacterSize = maxCharacterSize;
	}

	public void insertString( int offs, String str, AttributeSet a) throws BadLocationException {
		if( null != str ) {
			if( (this.getLength() + str.length() ) < this.maxCharacterSize ) {
				super.insertString( offs, str, a);
			} else {
				int diff = this.maxCharacterSize - this.getLength();
				//this may happen if they paste info...only allow a portion of it to be inserted upto the max
				if( diff > 0 ) {
					super.insertString( offs, str.substring(0, diff), a);
				}
			}
		}
	}

	public int getMaximumCharacterLength() {
		return this.maxCharacterSize;
	}

	public void setMaxuimumCharacterLength( int size ) {
		this.maxCharacterSize = size;
	}
}
