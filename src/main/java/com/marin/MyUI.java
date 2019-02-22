package com.marin;

import java.awt.List;
import java.util.*;

import javax.servlet.annotation.WebServlet;

import org.jsoup.Jsoup;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.GridLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout verLayout1 = new VerticalLayout();
        final VerticalLayout verLayout2 = new VerticalLayout();
        final HorizontalLayout horLayout1 = new HorizontalLayout();
        final HorizontalLayout horLayout2 = new HorizontalLayout();
        
        final TextField grocery = new TextField();
        grocery.setPlaceholder("Example Egg");
        grocery.setCaption("Enter grocery item:");

        final TextField quantity = new TextField();
        quantity.setPlaceholder("Number");
        quantity.setCaption("Enter item quantyty:");

       

        Button buttonClr = new Button("Clear entry");
        buttonClr.addClickListener(e -> {
            grocery.clear();
            grocery.setPlaceholder("Example Egg");
            quantity.clear();
            quantity.setPlaceholder("Number");
        });

        Button buttonSend = new Button("Send");
        buttonSend.addClickListener(e -> {
            verLayout1.addComponent(new Label("Thanks " + grocery.getValue() 
                    + ", it works!"));
        });

        ArrayList<item> itemList = new ArrayList<item>();
        
        Grid<item> itemGrid = new Grid<>();
        itemGrid.setItems(itemList);
        itemGrid.addColumn(item::getIname) .setCaption("Item");
        itemGrid.addColumn(item::getIquantity) .setCaption("Quantity");
        itemGrid.setSelectionMode(SelectionMode.MULTI);
        //itemGrid.setSizeFull();

        Button buttonAdd = new Button("Add to list");
        buttonAdd.addClickListener(e -> {
            itemList.add(new item(grocery.getValue(), quantity.getValue()));
            itemGrid.removeAllColumns();
            itemGrid.setItems(itemList);
            itemGrid.addColumn(item::getIname).setCaption("Item");
            itemGrid.addColumn(item::getIquantity).setCaption("Quantity");
            
        });

        Button buttonClrList = new Button("Reset list");
        buttonClrList.addClickListener(e -> {
            itemGrid.removeAllColumns();
            itemList.clear(); // Clear the list of people
            itemGrid.addColumn(item::getIname) .setCaption("Item");
            itemGrid.addColumn(item::getIquantity) .setCaption("Quantity");
            //grocery.setValue("");
            //quantity.setValue("");
        });
        
        horLayout1.addComponents(grocery,quantity);
        horLayout2.addComponents(buttonAdd,buttonClr,buttonClrList);
        verLayout1.addComponents(horLayout1, horLayout2, itemGrid);
        
        setContent(verLayout1);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
