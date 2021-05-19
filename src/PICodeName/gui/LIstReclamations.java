/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import PICodeName.entities.Reclamation;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import services.ServiceReclamation;

/**
 *
 * @author ala
 */
public class LIstReclamations extends Form{
     private TextField tfPrenom;
    private TextField tfNom;
    private Resources theme;

    private Button clear;
    private Button submit;

    public SwipeableContainer createRankWidget(String title, String year) {
        MultiButton button = new MultiButton(title);
        button.setTextLine2(year);
        Label lsupprimer= new Label(" ");
        lsupprimer.setUIID("NEWS");
        Style supprimerStyle =new Style(lsupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lsupprimer.setIcon(supprimerImage);
        lsupprimer.setTextPosition(RIGHT);
        
        return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()),
                button);
    }
    
    
        private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
        
        private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
        public LIstReclamations(Form previous) {
        setTitle("List reclamations");
        Label lsupprimer= new Label(" ");

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
       // list.setDropTarget(true);


        ArrayList<Reclamation> ev = new ArrayList<Reclamation>();
        ev = ServiceReclamation.getInstance().affichageReclamationsa();

        for (Reclamation s : ev) {

            list.add(createRankWidget(s.getMotif(), s.getMessage()));
        lsupprimer.setUIID("NEWS");
        Style supprimerStyle =new Style(lsupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lsupprimer.setIcon(supprimerImage);
        lsupprimer.setTextPosition(RIGHT);
        }
        addAll(list,lsupprimer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }
    
}
