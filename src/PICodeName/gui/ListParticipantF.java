/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Formation;
import PICodeName.entities.ParticipantF;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import java.util.ArrayList;
import services.ServiceFormation;

/**
 *
 * @author fedi
 */
public class ListParticipantF extends Form {
    
    Form current;
    Form c;
    public SwipeableContainer createRankWidget(String title, String type,ParticipantF p ,int id) {
        MultiButton button = new MultiButton(title);
        //button.setIcon(icon);
        c = new Home();
       

        //button.setTextLine1(id);
        //button.setPressedIcon(icon);
       
          button.addLongPressListener(e
                -> ServiceFormation.getInstance().deleteParticipantf(p.getId(),id)
        );
      
        
        button.addLongPressListener(e
                -> Dialog.show("Success", "Participant Deleted", new Command("OK"))
        );
        
        button.addLongPressListener(e
                -> new ListParticipantF(id).show()
        );
        
        button.setTextLine2(type);
        
        button.setName("Label_3_3");
        button.setUIID("SmallFontLabel");

        return new SwipeableContainer(null,
                button);
    }
   

    public ListParticipantF(int id) {
        Form previous = new afficheformation();
        System.out.println(id);
        ArrayList<ParticipantF> i = new ArrayList<ParticipantF>();
        i = ServiceFormation.getInstance().getAllParticipantf(id);

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);



        for (ParticipantF p : i) {
            list.add(createRankWidget(p.getNom(), p.getMail(), p, id));
        }
        addAll(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }
    
    
}
