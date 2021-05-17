/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class EventDetails extends Form {

    private TextField tfPrenom;
    private TextField tfNom;
    private Resources theme;
    Form current;

    private Button clear;
    private Button submit;
    //Image icon = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, "Button", 3.0f);

    public EventDetails(Form previous, String id) {
        setTitle("Event Deatils");
        current = this;

        setLayout(BoxLayout.y());
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);

        ArrayList<Evenement> ev = new ArrayList<Evenement>();
        ev = ServiceEvent.getInstance().getEventDetails(Integer.parseInt(id));
        MultiButton button = new MultiButton();
        MultiButton button1 = new MultiButton();
        MultiButton button2 = new MultiButton();
        MultiButton button3 = new MultiButton();
        MultiButton button4 = new MultiButton();
        MultiButton button5 = new MultiButton();

        for (Evenement s : ev) {

            button.setText("Event Title: " + s.getTitle());
            button1.setText("Event Type: " + s.getType());
            button2.setText("Event Description: " + s.getDescription());
            button3.setText("Event Localisation: " + s.getLocalitation());
            button4.setText("Viewed: " + s.getViewed().toString());

        }
        
        

        addAll(button, button1, button2, button3, button4);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}
