package com.uniyaz.sorun.ui.components;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by AKARTAL on 17.12.2019.
 */
public class Content extends VerticalLayout {

    public Content() {
    }

    public void setContent(Component component) {
        this.removeAllComponents();
        addComponent(component);
    }
}