/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import services.ServiceEvent;
import PICodeName.entities.Booking;
import PICodeName.entities.ParticipantE;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author farou
 */
public class Bookin extends Form {

    Form current;
    private Resources theme;

    public Bookin(Form previous, int id) {

        setTitle("Participate");
        setLayout(BoxLayout.y());
        current = this;
        ComboBox cb = new ComboBox();

        TextField tfMail = new TextField("", "Mail");
        TextField tfAge = new TextField("", "Age");
        TextField tfNom = new TextField("", "tfNom");
        Button btnValider = new Button("Participat");

        Form A = new Form("Seats", new BoxLayout(BoxLayout.X_AXIS));
        A.setScrollableX(true);
        Label A1 = new Label("A1   ");
        A1.getAllStyles().setFgColor(0xff000);
        Label A2 = new Label("A2   ");
        A2.getAllStyles().setFgColor(0xff000);
        Label A3 = new Label("A3   ");
        A3.getAllStyles().setFgColor(0xff000);
        Label A4 = new Label("A4   ");
        A4.getAllStyles().setFgColor(0xff000);
        Label A5 = new Label("A5   ");
        A5.getAllStyles().setFgColor(0xff000);
        Label A6 = new Label("A6   ");
        A6.getAllStyles().setFgColor(0xff000);

        A.add(new Label("A          ")).
                add(A1).
                add(A2).
                add(A3).
                add(A4).
                add(A5).
                add(A6);

        Container B = new Container(new BoxLayout(BoxLayout.X_AXIS));
        B.setScrollableX(true);
        Label B1 = new Label("B1   ");
        B1.getAllStyles().setFgColor(0xff000);
        Label B2 = new Label("B2   ");
        B2.getAllStyles().setFgColor(0xff000);
        Label B3 = new Label("B3   ");
        B3.getAllStyles().setFgColor(0xff000);
        Label B4 = new Label("B4   ");
        B4.getAllStyles().setFgColor(0xff000);
        Label B5 = new Label("B5   ");
        B5.getAllStyles().setFgColor(0xff000);
        Label B6 = new Label("B6   ");
        B6.getAllStyles().setFgColor(0xff000);

        B.add(new Label("B          ")).
                add(B1).
                add(B2).
                add(B3).
                add(B4).
                add(B5).
                add(B6);

        Container C = new Container(new BoxLayout(BoxLayout.X_AXIS));
        C.setScrollableX(true);
        Label C1 = new Label("C1   ");
        C1.getAllStyles().setFgColor(0xff000);
        Label C2 = new Label("C2   ");
        C2.getAllStyles().setFgColor(0xff000);
        Label C3 = new Label("C3   ");
        C3.getAllStyles().setFgColor(0xff000);
        Label C4 = new Label("C4   ");
        C4.getAllStyles().setFgColor(0xff000);
        Label C5 = new Label("C5   ");
        C5.getAllStyles().setFgColor(0xff000);
        Label C6 = new Label("C6   ");
        C6.getAllStyles().setFgColor(0xff000);

        C.add(new Label("C          ")).
                add(C1).
                add(C2).
                add(C3).
                add(C4).
                add(C5).
                add(C6);

        ArrayList<Booking> ev = new ArrayList<Booking>();
        ev = ServiceEvent.getInstance().getAllbooking(id);

        for (Booking s : ev) {
            if (s.getA1() == 0) {
                cb.addItem("A1");
            } else {
                A1.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getA2() == 0) {
                cb.addItem("A2");
            } else {
                A2.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getA3() == 0) {
                cb.addItem("A3");
            } else {
                A3.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getA4() == 0) {
                cb.addItem("A4");
            } else {
                A4.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getA5() == 0) {
                cb.addItem("A5");
            } else {
                A5.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getA6() == 0) {
                cb.addItem("A6");
            } else {
                A6.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }

            if (s.getB1() == 0) {
                cb.addItem("B1");
            } else {
                B1.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getB2() == 0) {
                cb.addItem("B2");
            } else {
                B2.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getB3() == 0) {
                cb.addItem("B3");
            } else {
                B3.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getB4() == 0) {
                cb.addItem("B4");
            } else {
                B4.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getB5() == 0) {
                cb.addItem("B5");
            } else {
                B5.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getB6() == 0) {
                cb.addItem("B6");
            } else {
                B6.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }

            if (s.getC1() == 0) {
                cb.addItem("C1");
            } else {
                C1.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getC2() == 0) {
                cb.addItem("C2");
            } else {
                C2.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getC3() == 0) {
                cb.addItem("C3");
            } else {
                C3.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getC4() == 0) {
                cb.addItem("C4");
            } else {
                C4.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getC5() == 0) {
                cb.addItem("C5");
            } else {
                C5.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
            if (s.getC6() == 0) {
                cb.addItem("C6");
            } else {
                C6.getAllStyles().setFgColor(ColorUtil.rgb(255, 0, 0));
            }
        }

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfMail.getText().length() == 0 || tfNom.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        ParticipantE e = new ParticipantE(tfMail.getText(), tfNom.getText(), Integer.parseInt(tfAge.getText()), cb.getSelectedItem().toString());
                        System.out.println(e.toString());
                        if (ServiceEvent.getInstance().addParticipant(e, id)) {
                            Dialog.show("Success", "Participation accepted", new Command("OK"));
                            new ListEventsClient(current, ServiceEvent.getInstance().getAllEvents()).show();
                        } else {
                            Dialog.show("ERROR", "Server Error", new Command("OK"));
                        }
                    } catch (Exception ex) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
                }
            }
        });
        theme = UIManager.initFirstTheme("/theme");

        ImageViewer im = new ImageViewer(theme.getImage("123.jpg"));

        addAll(tfNom, tfMail, tfAge, cb, im, A, B, C, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}
