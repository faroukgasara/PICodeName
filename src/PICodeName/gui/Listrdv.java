/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Rendezvous;
import PICodeName.entities.Surfer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
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
import com.codename1.ui.Toolbar;
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
//         Toolbar tb = getToolbar();
//
//        tb.addMaterialCommandToOverflowMenu("Stat", FontImage.MATERIAL_STACKED_LINE_CHART, e -> new Statrdv(current).show());
//        
////          search tbadel 3onwen tool bar
////prepare field
//            TextField searchField;
//            searchField = new TextField("", "Articles' List");
//            searchField.getHintLabel().setUIID("Title");
//            searchField.setUIID("Title");
//            getToolbar().setTitleComponent(searchField);
////if field content changed
//            searchField.addDataChangeListener((i1, i2) -> {
//            String t = searchField.getText();
//            if(t.length() < 1) {
//            for(Component cmp : getContentPane()) {
//            cmp.setHidden(false);
//            cmp.setVisible(true);
//                                }
//                        } else {
//                    t = t.toLowerCase();
//                   for(Component cmp: getContentPane()) {
////tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
//                String val = ((SpanLabel) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(0)).getText();
//                    System.out.println( val );
//                boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
//                cmp.setHidden(!show);
//                cmp.setVisible(show);
//                }
//                }
//                getContentPane().animateLayout(250);
//                });

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
