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




import com.codename1.ui.Button;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import services.ServiceEvent;




import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import PICodeName.gui.SignInForm;
import PICodeName.gui.SignUpForm;
import com.codename1.components.ImageViewer;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;



import java.util.ArrayList;
import services.ServiceEvent;
import services.ServiceReclamation;




import com.codename1.ui.util.Resources;



import services.ServiceEvent;



/**
 *
 * @author farou
 */
public class Home extends Form {

    Form current;
    private Form current1;
    private Resources theme;

    public Home() {

        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());

        
        Toolbar tb = getToolbar();
        tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this, ServiceEvent.getInstance().getAllEvents()).show());
        tb.addMaterialCommandToSideMenu("Offer", FontImage.MATERIAL_UPDATE, e -> new frontoffre().show());
        tb.addMaterialCommandToSideMenu("Formation", FontImage.MATERIAL_UPDATE, e -> new ListformationC().show());

        add(new Label("Choose"));
        Toolbar tb = getToolbar();
        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this,ServiceEvent.getInstance().getAllEvents()).show());

        Button btnListEvents = new Button("List Events");


        btnListEvents.addActionListener(e-> new ListEventsClient(current,ServiceEvent.getInstance().getAllEvents()).show());

        Button btnAddEvent = new Button("Add Event");
        Button btnAddoffre = new Button("Add Offre");


        Button btnAddParticipantf = new Button("Add Participant");
        

        btnAddEvent.addActionListener(e-> new AddEvent(current).show());

       
        tb.addMaterialCommandToSideMenu("complaint", FontImage.MATERIAL_UPDATE, e -> {
            try {
                new LIstReclamations(this,ServiceReclamation.getInstance().affichageReclamationsa()).show();
            } catch (Exception ex) {
                System.out.println("PICodeName.gui.HomeAdmin.<init>()");
            }
        });




        Button btnAddFormation = new Button("Add Formation");
         Button btnAfficheformation = new Button("Affiche Formation");
        

        Button btnAddrdv = new Button("Add Rendez_vous");
        Button btnListrdv = new Button("List Rendez_vous");
        btnAddrdv.addActionListener(e-> new Addrdv(current).show());
        btnListrdv.addActionListener(e-> new Listrdv(current).show());


        

        




        Button btnAddReclamation = new Button("Add complaint");
        btnAddReclamation.addActionListener(e-> new AddReclamation(current).show());
     

        
        
        //addAll(btnValider);

    }
