package com.uniyaz;

import com.uniyaz.sorun.ui.components.General;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        General general = new General();
        setContent(general);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }


//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//
//
//        final VerticalLayout layout = new VerticalLayout();
//
//        final TextField name = new TextField();
//        name.setCaption("Type your name here:");
//
//        Button button = new Button("Click Me");
//        button.addClickListener( e -> {
//
//            Session sessionEx = null;
//            try(Session session = sessionFactory.openSession();) {
//                sessionEx = session;
//                session.getTransaction().begin();
//                String value = name.getValue();
//                Category category = new Category();
//                category.setName(value);
//                session.save(category);
//
//                Category category2 = new Category();
//                category2.setName("CATE2");
//                session.save(category2);
//                session.getTransaction().commit();
//            }
//            catch (Exception ex) {
//                sessionEx.getTransaction().rollback();
//                System.out.println(ex.getMessage());
//            }
//        });
//
//        layout.addComponents(name, button);
//        layout.setMargin(true);
//        layout.setSpacing(true);
//
//        setContent(layout);
//    }
}
