/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author farou
 */
public class Home extends Form{
    Form current;
    public Home(){
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose"));
        
        Toolbar tb = getToolbar();


        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this).show());
        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this).show());
        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this).show());
        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this).show());

        Button btnListEvents = new Button("List Events");
        btnListEvents.addActionListener(e-> new ListEventsClient(current).show());

        Button btnAddEvent = new Button("Add Event");
        Button btnAddoffre = new Button("Add Offre");

        Button btnListEvents = new Button("List Events");

        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnAddoffre.addActionListener(e-> new addoffre().show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
        addAll(btnAddEvent,btnListEvents,btnAddoffre);
        



        Button btnAddFormation = new Button("Add Formation");
        Button btnAddrdv = new Button("Add Rendez_vous");
        Button btnListrdv = new Button("List Rendez_vous");
        btnAddrdv.addActionListener(e-> new Addrdv(current).show());
        btnListrdv.addActionListener(e-> new Listrdv(current).show());

        btnAddoffre.addActionListener(e-> new addoffre().show());
        btnListEvents.addActionListener(e-> new ListEventsClient(current).show());

        addAll(btnAddEvent,btnListEvents,btnAddrdv,btnListrdv);


        addAll(btnListEvents,btnAddrdv,btnListrdv);


        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnAddoffre.addActionListener(e-> new addoffre().show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
        btnAddFormation.addActionListener(e-> new addformation().show());
        addAll(btnAddEvent,btnListEvents,btnAddFormation);
        

        
        addAll(btnAddEvent,btnListEvents,btnAddoffre,btnAddrdv,btnListrdv);
        
        
    }
    
}
