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
        Button btnAddEvent = new Button("Add Event");
        Button btnAddoffre = new Button("Add Offre");
        Button btnListEvents = new Button("List Events");
        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnAddoffre.addActionListener(e-> new addoffre().show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
<<<<<<< HEAD
        addAll(btnAddEvent,btnListEvents,btnAddoffre);
        
=======
        Button btnAddrdv = new Button("Add Rendez_vous");
        Button btnListrdv = new Button("List Rendez_vous");
        btnAddrdv.addActionListener(e-> new Addrdv(current).show());
        btnListrdv.addActionListener(e-> new Listrdv(current).show());
        addAll(btnAddEvent,btnListEvents,btnAddrdv,btnListrdv);
>>>>>>> b46165eeb2ca92034c254cf0f340377547b896e8
        
        
    }
    
}
