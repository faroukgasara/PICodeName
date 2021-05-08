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
        addAll(btnAddEvent,btnListEvents,btnAddoffre);
        
        
        
    }
    
}
