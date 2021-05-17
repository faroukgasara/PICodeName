/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Rendezvous;
import PICodeName.entities.Surfer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
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
import java.util.Date;
import services.Servicerdv;

/**
 *
 * @author wassim
 */
public class Listrdv extends Form {

    private TextField tfPrenom;
    private TextField tfNom;
    private Resources theme;

    private Button clear;
    private Button submit;
    Form current;
     Image icon = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, "Button", 3.0f);

    public SwipeableContainer createRankWidget(Date d,String meet,Rendezvous s, String desc,String id) {
         MultiButton button = new MultiButton(meet);
        button.setIcon(icon);
        current = this;

        //button.setTextLine1(id);
        button.setPressedIcon(icon);
        button.addLongPressListener(e
                -> Servicerdv.getInstance().deleterdv(Integer.parseInt(id))
        );
        
        button.addLongPressListener(e
                -> {Dialog.show("Confirmation","Delete this RDV?","ok","Annuler");});
        
        
        button.addLongPressListener(e
                -> new Listrdv(current).show()
        );
         button.addActionListener(e
                -> new Updaterdv(s,id,current).show()
        );
        button.setName("Label_3_3");
        button.setUIID("SmallFontLabel");
        button.setTextLine2(desc);
        button.setTextLine3(d.toString());
        return new SwipeableContainer(null,
                button);
    }



    public Listrdv(Form previous) {
        setTitle("Meeting List");

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);

        ArrayList<Surfer> ec = new ArrayList<Surfer>();
        ec = Servicerdv.getInstance().getcombo();
        ArrayList<Rendezvous> ev = new ArrayList<Rendezvous>();
        ev = Servicerdv.getInstance().getAllrdvs();

        for (Rendezvous s : ev ) {

            list.add(createRankWidget(s.getDate(),s.getMeet(),s, s.getDescription(),Integer.toString(s.getId())));
        }
        addAll(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}
