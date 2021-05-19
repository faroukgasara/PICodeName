/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import PICodeName.entities.NotifEvent;
import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class EventNotif extends Form {
    
    public SwipeableContainer createRankWidget(String title) {
        MultiButton button = new MultiButton(title);
        return new SwipeableContainer(null,
                button);
    }

    public EventNotif(Form previous) {
        setTitle("Event Deatils");

        setLayout(BoxLayout.y());
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);
        Form f = new Form(BoxLayout.y());



        ArrayList<NotifEvent> ev = new ArrayList<NotifEvent>();
        ev = ServiceEvent.getInstance().geteventnotif();
        for (NotifEvent s : ev) {
            list.add(createRankWidget(s.getNotif()));

        }
        f.add(list);

        addAll( f);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }
}
