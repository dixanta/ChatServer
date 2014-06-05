/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db.bo;

/**
 *
 * @author Dixanta Bahadur Shrestha
 */
public class AvatarEntity {
    private String avatarName;
    private String avatarImage;
    private int avatarType;

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public int getAvatarType() {
        return avatarType;
    }

    public void setAvatarType(int avatarType) {
        this.avatarType = avatarType;
    }
}