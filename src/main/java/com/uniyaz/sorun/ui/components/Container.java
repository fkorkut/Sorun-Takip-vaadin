package com.uniyaz.sorun.ui.components;

import com.uniyaz.sorun.ui.views.AddCategoryView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by AKARTAL on 17.12.2019.
 */
public class Container extends HorizontalLayout {

    private Header header;

    public Container(Header header) {
        this.header = header;
        setWidth(100, Unit.PERCENTAGE);

        Content content = new Content();
        SideBar sideBar = new SideBar(header, content);

        addComponent(sideBar);
        addComponent(content);

        setExpandRatio(sideBar, 2f);
        setExpandRatio(content, 8f);
    }
}
