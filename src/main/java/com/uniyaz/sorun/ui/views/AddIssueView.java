package com.uniyaz.sorun.ui.views;

import com.uniyaz.sorun.dao.IssueDao;
import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.domain.EnumIssueState;
import com.uniyaz.sorun.domain.Issue;
import com.uniyaz.sorun.ui.components.SaveButton;
import com.uniyaz.sorun.utils.HibernateUtil;
import com.vaadin.ui.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class AddIssueView extends VerticalLayout {

    private FormLayout formLayout;
    List<Category> categoryList;
    TextField idField,addressField,topicField,contentField;
    ComboBox categoryField,issueComboBox;
    DateField dateField;

    String addressFieldValue,contentFieldValue,topicFieldValue;
    Category categoryFieldValue;
    Date dateFieldValue;
    EnumIssueState issueStateFieldValue;

    Issue issue;
    IssueDao issueDoa;

    public void categories(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("Select category From Category category");
        categoryList = query.list();
    }

    public AddIssueView() {
        categories();
        mainLayout();
    }
    public void fillViewByIssue(Issue issue) {
        idField.setValue(issue.getId().toString());
        categoryField.setValue(issue.getCategory().toString());
        addressField.setValue(issue.getAddress());
        contentField.setValue(issue.getContent());
        dateField.setValue(issue.getDate());
        issueComboBox.setValue(issue.getIssueState().toString());
        topicField.setValue(issue.getTopic());

    }

    private void mainLayout() {

        formLayout =new FormLayout();
        addComponent(formLayout);

        idField=new TextField("Id");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        categoryField=new ComboBox("Kategoriler",categoryList );
        formLayout.addComponent(categoryField);


        addressField=new TextField("address");
        formLayout.addComponent(addressField);

        dateField=new DateField("Tarih");
        formLayout.addComponent((Component) dateField);


         topicField=new TextField("topic");
        formLayout.addComponent(topicField);

        contentField=new TextField("content");
        formLayout.addComponent(contentField);


        issueComboBox=new ComboBox("Durum");
        for (EnumIssueState state : EnumIssueState.values()) {
            issueComboBox.addItem(state);
        }

        formLayout.addComponent(issueComboBox);

        SaveButton saveButton=new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Long idFieldValue = null;
                if (idField.getValue() != "") {
                    idFieldValue = Long.parseLong(idField.getValue());
                }

              /*  String addressFieldValue=addressField.getValue();
                String topicFieldValue=topicField.getValue();
                String contentFieldValue=contentField.getValue();
                Date dateFieldValue=(Date)dateField.getValue();
                Category categoryFieldValue= (Category) categoryField.getValue();
                EnumIssueState issueStateFieldValue= (EnumIssueState) issueComboBox.getValue();*/
               getFieldValue();//fieldlerden verileri al bir değişkene ata
               createIssue();//alınan değerlerle bir issue nesne oluştur
               /* issue=new Issue();
                issue.setAddress(addressFieldValue);
                issue.setCategory(categoryFieldValue);//combo
                issue.setContent(contentFieldValue);
                issue.setDate(dateFieldValue);//date
                issue.setIssueState(issueStateFieldValue);//combo
                issue.setTopic(topicFieldValue);*/
                saveDB();//issue nesnesini veritabanına kaydet doa


            }
        });
        formLayout.addComponent(saveButton);
    }
    private void getFieldValue() {
        addressFieldValue=addressField.getValue();
        topicFieldValue=topicField.getValue();
        contentFieldValue=contentField.getValue();
        dateFieldValue=(Date)dateField.getValue();
        categoryFieldValue= (Category) categoryField.getValue();
        issueStateFieldValue= (EnumIssueState) issueComboBox.getValue();
    }

    private void createIssue() {
        issue=new Issue();
        issue.setAddress(addressFieldValue);
        issue.setCategory(categoryFieldValue);//combo
        issue.setContent(contentFieldValue);
        issue.setDate(dateFieldValue);//date
        issue.setIssueState(issueStateFieldValue);//combo
        issue.setTopic(topicFieldValue);
    }

    private void saveDB() {
        issueDoa=new IssueDao();
        issue=issueDoa.saveIssue(issue);
        idField.setValue(issue.getId().toString());
    }

}




