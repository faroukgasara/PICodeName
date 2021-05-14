/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
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
public class EventDetails extends Form{
    
    private TextField tfPrenom;
    private TextField tfNom;
    private Resources theme;
    Form current;

    private Button clear;
    private Button submit;
    //Image icon = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, "Button", 3.0f);

    public SwipeableContainer createRankWidget(String title, String type, Evenement s, String id) {
        MultiButton button = new MultiButton(title);
        //button.setIcon(icon);
        current = this;

        //button.setTextLine1(id);
        //button.setPressedIcon(icon);
        button.addLongPressListener(e
                -> ServiceEvent.getInstance().deleteEvent(Integer.parseInt(id))
        );

        button.addLongPressListener(e
                -> Dialog.show("Success", "Event Deleted", new Command("OK"))
        );

        button.addLongPressListener(e
                -> new ListEvents(current).show()
        );

        button.addActionListener(e
                -> new UpdateEvent(s, id, current).show()
        );
        button.setTextLine2(type);
        button.setName("Label_3_3");
        button.setUIID("SmallFontLabel");

        return new SwipeableContainer(null,button);
    }



    public EventDetails(Form previous,String id) {
        setTitle("Event Deatils");
        current = this;

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);
        

        ArrayList<Evenement> ev = new ArrayList<Evenement>();
        ev = ServiceEvent.getInstance().getEventDetails(Integer.parseInt(id));
        MultiButton button = new MultiButton();

        for (Evenement s : ev) {
            button.setText(s.getTitle());
            list.add(button);
        }
        addAll(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}
    

