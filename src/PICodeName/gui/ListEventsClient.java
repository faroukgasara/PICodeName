/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import com.codename1.components.MultiButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.AutoCompleteTextField;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class ListEventsClient extends Form {

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
        button.addActionListener(e
                -> new EventDetails(current, id).show()
        );

        button.addActionListener(e
                -> ServiceEvent.getInstance().Viewed(Integer.parseInt(id))
        );

        button.addLongPressListener(e
                -> new Bookin(current, Integer.parseInt(id)).show()
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

    Button btnrecherche = new Button("Search");

    
    final DefaultListModel<String> options = new DefaultListModel<>();
    AutoCompleteTextField ac = new AutoCompleteTextField(options);
    Form c;

    public ListEventsClient(Form previous, ArrayList<Evenement> ev) {
        c = new Home();
        setTitle("List Events Client");
        current = new Home();
        setLayout(BoxLayout.y());

        Form f = new Form(BoxLayout.y());

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        list.setDropTarget(true);
        for (Evenement s : ev) {
            list.add(createRankWidget(s.getTitle(), s.getType(), s, Integer.toString(s.getId())));
            options.addItem(s.getTitle());
        }

        f.add(list);

        btnrecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListEventsClien();
            }
        });


        ArrayList<Evenement> a = new ArrayList<Evenement>();
        a = ServiceEvent.getInstance().getAllEvents();
        for (Evenement s : a) {

            options.addItem(s.getTitle());
        }

        addAll(ac, btnrecherche, f);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }



    public ArrayList<Evenement> ListEventsClien() {
        ArrayList<Evenement> ev = new ArrayList<Evenement>();

        if (ac.getText().length() == 0) {
            ev = ServiceEvent.getInstance().getAllEvents();
            new ListEventsClient(c, ev).show();

            return ev;

        } else {
            try {
                ev = ServiceEvent.getInstance().search(ac.getText());
                new ListEventsClient(c, ev).show();
                return ev;

            } catch (Exception ex) {
                Dialog.show("ERROR", "ERROR", new Command("OK"));
            }
        }
        return ev;

    }

}
