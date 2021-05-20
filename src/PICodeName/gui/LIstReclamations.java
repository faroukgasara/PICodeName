/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import PICodeName.entities.Reclamation;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.AutoCompleteTextField;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

import services.ServiceEvent;
import services.ServiceReclamation;

/**
 *
 * @author wae
 */
public class LIstReclamations extends Form {

    private TextField tfPrenom;
    private TextField tfNom;
    private Resources theme;
    Form current;
    private Button clear;
    private Button submit;
    //Image icon = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, "Button", 3.0f);

    public SwipeableContainer createRankWidget(String title, String type, Reclamation s, String id) {
        MultiButton button = new MultiButton(title);
        //button.setIcon(icon);
        current = this;

        //button.setTextLine1(id);
        //button.setPressedIcon(icon);
        button.addLongPressListener(e
                -> ServiceReclamation.getInstance().deleteRec(Integer.parseInt(id))
        );

        button.addLongPressListener(e
                -> Dialog.show("Success", "rec Deleted", new Command("OK"))
        );

        button.addLongPressListener(e
                -> {
            try {
                new LIstReclamations(current, ServiceReclamation.getInstance().affichageReclamationsa()).show();
            } catch (Exception ex) {
                System.out.println("PICodeName.gui.LIstReclamations.createRankWidget()");
            }
        }
        );

        button.addActionListener(e //-> new ListParticipantE(Integer.parseInt(id),current).show()
                -> {
            try {
                new UpdateRec(s, id, current).show();
            } catch (Exception ex) {
               System.out.println("PICodeName.gui.LIstReclamations.createRankWidget()");
            }
        }
        );
        button.setTextLine2(type);
        button.setName("Label_3_3");
        button.setUIID("SmallFontLabel");

        return new SwipeableContainer(createStarRankSlider(id),
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
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_WALLET_MEMBERSHIP, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 1, fullStar.getHeight()));

        starRank.addActionListener(e -> new ListParticipantE(Integer.parseInt(id), current).show()
        );
        return starRank;
    }

    Button btnrecherche = new Button("Search");
    final DefaultListModel<String> options = new DefaultListModel<>();
    AutoCompleteTextField ac = new AutoCompleteTextField(options);
    Form c;

    public LIstReclamations(Form previous, ArrayList<Reclamation> ev)  {

        Toolbar tb = getToolbar();

        tb.addMaterialCommandToOverflowMenu("Stat Age", FontImage.MATERIAL_STACKED_LINE_CHART, e -> new Stat(current).show());
        tb.addMaterialCommandToOverflowMenu("Stat", FontImage.MATERIAL_STACKED_LINE_CHART, e -> new StatEvent(current).show());
        c = new HomeAdmin();
        Form p = this;
        setTitle("List reclamation ");
        current = new Home();
        setLayout(BoxLayout.y());

        Form f = new Form(BoxLayout.y());

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);
        for (Reclamation s : ev) {
           list.add(createRankWidget(s.getMessage(), s.getMotif(), s, Integer.toString(s.getId())));
            options.addItem(s.getMotif());
        }

        f.add(list);

        btnrecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

               
                    ListEvent();
               

            }

        });

        ArrayList<Reclamation> a = new ArrayList<Reclamation>();
        a = ServiceReclamation.getInstance().affichageReclamationsa();
        for (Reclamation s : a) {

            options.addItem(s.getMotif());
        }

        addAll(ac, btnrecherche, f);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> c.show());

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder) fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            new AddReclamation(current).show();
        });

    }

    public ArrayList<Reclamation> ListEvent()  {
        ArrayList<Reclamation> ev = new ArrayList<Reclamation>();

        if (ac.getText().length() == 0) {
            ev = ServiceReclamation.getInstance().affichageReclamationsa();
            new LIstReclamations(c, ev).show();

            return ev;

        } 
        return ev;

    }

}
