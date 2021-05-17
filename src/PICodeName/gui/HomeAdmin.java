/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
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
import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class HomeAdmin extends Form {

    Form current;

    public HomeAdmin() {
        current = this;
        setTitle("Admin Home");

        Toolbar tb = getToolbar();



        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEvents(this,ServiceEvent.getInstance().getAllEvents()).show());

        tb.addMaterialCommandToSideMenu("Meeting List", FontImage.MATERIAL_UPDATE, e -> new Listrdv(this).show());
        tb.addMaterialCommandToSideMenu("Add a Meet", FontImage.MATERIAL_UPDATE, e -> new Addrdv(this).show());

        setLayout(BoxLayout.y());
        Button btnAddEvent = new Button("Add Event");
        Button btnListEvents = new Button("List Events");
        btnAddEvent.addActionListener(e -> new AddEvent(current).show());
        btnListEvents.addActionListener(e -> new ListEvents(current,ServiceEvent.getInstance().getAllEvents()).show());
        addAll(btnAddEvent, btnListEvents);
    }

}
