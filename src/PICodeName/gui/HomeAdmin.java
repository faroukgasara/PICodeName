/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author farou
 */
public class HomeAdmin extends Form{
    Form current; 
    public HomeAdmin(){
                current = this;
        setTitle("Admin Home");
        setLayout(BoxLayout.y());
                Button btnAddEvent = new Button("Add Event");
        Button btnListEvents = new Button("List Events");
        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
        addAll(btnAddEvent,btnListEvents);
    }
    
}
