/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;


import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Dialog;

import PICodeName.entities.Evenement;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import PICodeName.gui.SignInForm;
import PICodeName.gui.SignUpForm;

import java.util.ArrayList;
import services.ServiceEvent;


/**
 *
 * @author farou
 */
public class Home extends Form{
    Form current;
     private Form current1;
     private Resources theme;
    public Home(){
     
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose"));
        Toolbar tb = getToolbar();
        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this,ServiceEvent.getInstance().getAllEvents()).show());

        Button btnListEvents = new Button("List Events");

     
        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
      
        addAll(btnAddEvent,btnListEvents);
   
     
  

        btnListEvents.addActionListener(e-> new ListEventsClient(current,ServiceEvent.getInstance().getAllEvents()).show());

        Button btnAddEvent = new Button("Add Event");
        Button btnAddoffre = new Button("Add Offre");

        Button btnAddParticipantf = new Button("Add Participant");
        

        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnAddoffre.addActionListener(e-> new addoffre().show());

       
        




        Button btnAddFormation = new Button("Add Formation");

        Button btnAddrdv = new Button("Add Rendez_vous");
        Button btnListrdv = new Button("List Rendez_vous");
        btnAddrdv.addActionListener(e-> new Addrdv(current).show());
        btnListrdv.addActionListener(e-> new Listrdv(current).show());

        


        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnAddoffre.addActionListener(e-> new afficheformation().show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
        btnAddFormation.addActionListener(e-> new addformation().show());
        btnAddFormation.addActionListener(e-> new addformation().show());




        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnAddoffre.addActionListener(e-> new addoffre().show());
        btnAddFormation.addActionListener(e-> new addformation().show());


        
        


         addAll(btnAddEvent,btnAddFormation,btnListEvents,btnAddoffre,btnAddrdv,btnListrdv);

        addAll(btnAddEvent,btnListEvents,btnAddoffre,btnAddrdv,btnListrdv);


        
        
    }
    

}
