/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import PICodeName.entities.ParticipantE;
import com.codename1.components.MultiButton;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class ListParticipantE extends Form {

    Form current;
    Form c;
    public SwipeableContainer createRankWidget(String title, String type,ParticipantE s ,int id) {
        MultiButton button = new MultiButton(title);
        //button.setIcon(icon);
        c = new Home();
        current =new  ListEvents(c,ServiceEvent.getInstance().getAllEvents());

        //button.setTextLine1(id);
        //button.setPressedIcon(icon);
        button.addLongPressListener(e
                -> ServiceEvent.getInstance().deleteParticipant(s.getId(),id,s.getSeat())
        );
        
        button.addLongPressListener(e
                -> Dialog.show("Success", "Participant Deleted", new Command("OK"))
        );
        
        button.addLongPressListener(e
                -> new ListParticipantE(id,current).show()
        );

        button.setTextLine2(type);
        button.setName("Label_3_3");
        button.setUIID("SmallFontLabel");

        return new SwipeableContainer(null,
                button);
    }

    public ListParticipantE(int id, Form previous) {
        System.out.println(id);
        ArrayList<ParticipantE> i = new ArrayList<ParticipantE>();
        i = ServiceEvent.getInstance().getAllParticipant(id);

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);
        final DefaultListModel<String> options = new DefaultListModel<>();



        for (ParticipantE s : i) {
            list.add(createRankWidget(s.getNom(), s.getMail(), s, id));
            options.addItem(s.getNom());
        }
        AutoCompleteTextField ac = new AutoCompleteTextField(options);
        addAll(ac,list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}
