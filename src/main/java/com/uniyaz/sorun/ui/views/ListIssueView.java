package com.uniyaz.sorun.ui.views;

import com.google.gwt.user.datepicker.client.DatePicker;
import com.uniyaz.sorun.dao.IssueDao;
import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.domain.EnumIssueState;
import com.uniyaz.sorun.domain.Issue;
import com.uniyaz.sorun.ui.components.SaveButton;
import com.uniyaz.sorun.utils.HibernateUtil;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ListIssueView extends VerticalLayout {
    private Table table;
    private TextField idField;
    private ComboBox categoryIdField;
    private TextField addressField;
    private DateField dateField;
    private TextField topicField;
    private TextField contentField;
    private ComboBox issueComboBox;
    private SaveButton saveButton;

    private IndexedContainer indexedContainer;
    private  AddIssueView addIssueView;
    private Layout layout;

    public ListIssueView() {
        createTableContainer();
        createTable();
        addComponent(table);
        addIssueView=new AddIssueView();
        addComponent(addIssueView);
        fillTable();
    }


    private void createTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("content", String.class, null);
        indexedContainer.addContainerProperty("address", String.class, null);
        indexedContainer.addContainerProperty("category", String.class, null);
        indexedContainer.addContainerProperty("date", Date.class, null);
        indexedContainer.addContainerProperty("issueState", String.class, null);
        indexedContainer.addContainerProperty("topic", String.class, null);


    }

    private void createTable(){
        table = new Table();
        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("NO", "içerik","adres","categori","tarih","durum","başlık");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Issue issue = (Issue) itemClickEvent.getItemId();
                addIssueView.fillViewByIssue(issue);
                fieldDoldur(issue);


            }
        });
    }

    private void fieldDoldur( Issue issue ) {
        idField.setValue(String.valueOf(issue.getId()));
        categoryIdField.setValue(issue.getCategory());
        addressField.setValue(issue.getAddress());
        dateField.setValue(issue.getDate());
        topicField.setValue(issue.getTopic());
        contentField.setValue(issue.getContent());
        issueComboBox.setValue(issue.getIssueState());
    }

    private void fillTable(){
        IssueDao issueDao=new IssueDao();
        List<Issue> issueList=issueDao.findAllissue();

        for (Issue issue : issueList) {
            Item item = indexedContainer.addItem(issue);
            item.getItemProperty("id").setValue(issue.getId());
            if (issue.getCategory() != null){
                item.getItemProperty("category").setValue(issue.getCategory().getName());
            }
            item.getItemProperty("address").setValue(issue.getAddress());
            item.getItemProperty("date").setValue(issue.getDate());
            item.getItemProperty("topic").setValue(issue.getTopic());
            item.getItemProperty("content").setValue(issue.getContent());
            item.getItemProperty("issueState").setValue(issue.getIssueState().name());

        }


    }

}



