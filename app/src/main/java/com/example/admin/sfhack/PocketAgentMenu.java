package com.example.admin.sfhack;

import android.graphics.Bitmap;

/**
 * Created by SF on 6/14/2017.
 */

public class PocketAgentMenu {
    private Bitmap buttonIcon;
    private String menuName;

    public PocketAgentMenu(Bitmap buttonIcon, String menuName){
        this.buttonIcon = buttonIcon;
        this.menuName = menuName;
    }

    public void setButtonIcon(Bitmap buttonIcon) {
        this.buttonIcon = buttonIcon;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Bitmap getButtonIcon() {
        return buttonIcon;
    }

    public String getMenuName() {
        return menuName;
    }

}
