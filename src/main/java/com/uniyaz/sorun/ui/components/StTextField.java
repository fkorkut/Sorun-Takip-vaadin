package com.uniyaz.sorun.ui.components;

import com.vaadin.ui.TextField;

/**
 * Created by AKARTAL on 18.12.2019.
 */
public class StTextField extends TextField {

    public StTextField() {
        addStyleName("st-text-field");
        setNullRepresentation("");
    }

    public StTextField(String caption) {
        this();
        setCaption(caption);
    }
}
