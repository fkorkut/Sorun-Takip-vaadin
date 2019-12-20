package com.uniyaz.sorun.ui.views;

import com.uniyaz.sorun.dao.CategoryDao;
import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.ui.components.SaveButton;
import com.uniyaz.sorun.ui.components.StTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by AKARTAL on 17.12.2019.
 */
public class AddCategoryView extends VerticalLayout {

    private StTextField idField;
    private StTextField nameField;
    private FormLayout mainLayout;

    public AddCategoryView() {
        buildMainLayout();
    }

    public void fillViewByCategory(Category category) {
        idField.setValue(category.getId().toString());
        nameField.setValue(category.getName());
    }

    private void buildMainLayout() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new StTextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        nameField = new StTextField("Name");
        mainLayout.addComponent(nameField);

        SaveButton saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Long idFieldValue = null;
                if (idField.getValue() != "") {
                    idFieldValue = Long.parseLong(idField.getValue());
                }
                String nameFieldValue = nameField.getValue();

                Category category = new Category();
                category.setId(idFieldValue);
                category.setName(nameFieldValue);

                CategoryDao categoryDao = new CategoryDao();
                category = categoryDao.saveCategory(category);
                idField.setValue(category.getId().toString());
            }
        });
        mainLayout.addComponent(saveButton);
    }
}
