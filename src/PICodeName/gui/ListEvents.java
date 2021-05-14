/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class ListEvents extends Form {

    private TextField tfPrenom;
    private TextField tfNom;
    private Resources theme;
    Form current;

    private Button clear;
    private Button submit;
    //Image icon = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, "Button", 3.0f);

    public SwipeableContainer createRankWidget(String title, String type,Evenement s ,String id) {
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
        
        button.addActionListener(e //-> new ListParticipantE(Integer.parseInt(id),current).show()
                -> new UpdateEvent(s,id,current).show()
        );
        button.setTextLine2(type);
        button.setName("Label_3_3");
        button.setUIID("SmallFontLabel");

        return new SwipeableContainer(null,
                button);
    }

    
    

    public ListEvents(Form previous) {
        setTitle("List Events");
        current = this;

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);

        ArrayList<Evenement> ev = new ArrayList<Evenement>();
        ev = ServiceEvent.getInstance().getAllEvents();

        for (Evenement s : ev) {
            list.add(createRankWidget(s.getTitle(), s.getType(), s,Integer.toString(s.getId())));
        }
        addAll(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            new AddEvent(current).show();
        });

    }

}
