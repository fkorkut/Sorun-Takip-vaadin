package com.uniyaz.sorun.ui.components;

import com.vaadin.ui.VerticalLayout;

/**
 * Created by AKARTAL on 17.12.2019.
 */
public class General extends VerticalLayout {

    public General() {
        Header header = new Header();
        addComponent(header);

        Container container = new Container(header);
        addComponent(container);
    }
}