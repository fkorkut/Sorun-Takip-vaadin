package com.uniyaz.sorun.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

/**
 * Created by AKARTAL on 18.12.2019.
 */
public class SaveButton extends Button {

    public SaveButton() {
        setIcon(FontAwesome.CHECK);
        setCaption("Kaydet");
    }
}
