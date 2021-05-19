/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Formation;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
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
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;
import static java.util.concurrent.ThreadLocalRandom.current;
import services.ServiceFormation;

/**
 *
 * @author fedi
 */
public class ListformationC extends Form {
    
        Form current = this;
      public SwipeableContainer createRankWidget(String title, String type,Formation f ,String id) {
        MultiButton button = new MultiButton(title);
        //button.setIcon(icon);
        

        //button.setTextLine1(id);
        //button.setPressedIcon(icon);
      button.addLongPressListener(e
                -> ServiceFormation.getInstance().deleteFormation(Integer.parseInt(id))
        );
        
       
        
        Form current =this;
        button.addActionListener(e 
                -> new addParticipantf(Integer.parseInt(id) ).show()
        );
        button.setTextLine2(type);
        button.setName("Label_3_3");
        button.setUIID("SmallFontLabel");

        return new SwipeableContainer(null,
                button);
    }
      private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider(String id) {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(1);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(12, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_LIST, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 1, fullStar.getHeight()));

        starRank.addActionListener(e -> new ListParticipantF(Integer.parseInt(id)).show()
        );
        return starRank;
    }
    
    
    
    public ListformationC() {
        
        Form previous = new Home();
         setTitle("List Formation");
        

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);

        ArrayList<Formation> ev = new ArrayList<Formation>();
        ev = ServiceFormation.getInstance().getAllFormations();
            
        for (Formation f : ev) {
            list.add(createRankWidget(f.getTitle(), f.getDescription(), f,Integer.toString(f.getId())));
        }
        
        addAll(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            
        });

    }

    
}
   

