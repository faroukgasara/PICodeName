/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

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

        Button btnListEvents = new Button("List Events");
        btnListEvents.addActionListener(e-> new ListEventsClient(current).show());

        Button btnAddEvent = new Button("Add Event");
        Button btnAddoffre = new Button("Add Offre");
    
        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnAddoffre.addActionListener(e-> new addoffre().show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
        addAll(btnAddEvent,btnListEvents,btnAddoffre);
        



        Button btnAddFormation = new Button("Add Formation");

        Button btnAddrdv = new Button("Add Rendez_vous");
        Button btnListrdv = new Button("List Rendez_vous");
        btnAddrdv.addActionListener(e-> new Addrdv(current).show());
        btnListrdv.addActionListener(e-> new Listrdv(current).show());

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
