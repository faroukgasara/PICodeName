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
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author farou
 */
public class Bookin extends Form {

    Form current;

    public Bookin(Form previous, int id) {

        setTitle("Participate");
        setLayout(BoxLayout.y());
        current = this;
        ComboBox cb = new ComboBox();

     

        TextField tfMail = new TextField("", "Mail");
        TextField tfAge = new TextField("", "Age");
        TextField tfNom = new TextField("", "tfNom");
        Button btnValider = new Button("Participat");

        ArrayList<Booking> ev = new ArrayList<Booking>();
        ev = ServiceEvent.getInstance().getAllbooking(id);

        for (Booking s : ev) {
            if (s.getA1() == 0) {
                cb.addItem("A1");
            }
            if (s.getA2() == 0) {
                cb.addItem("A2");
            }
            if (s.getA3() == 0) {
                cb.addItem("A3");
            }
            if (s.getA4() == 0) {
                cb.addItem("A4");
            }
            if (s.getA5() == 0) {
                cb.addItem("A5");
            }
            if (s.getA6() == 0) {
                cb.addItem("A6");
            }

            if (s.getB1() == 0) {
                cb.addItem("B1");
            }
            if (s.getB2() == 0) {
                cb.addItem("B2");
            }
            if (s.getB3() == 0) {
                cb.addItem("B3");
            }
            if (s.getB4() == 0) {
                cb.addItem("B4");
            }
            if (s.getB5() == 0) {
                cb.addItem("B5");
            }
            if (s.getB6() == 0) {
                cb.addItem("B6");
            }

            if (s.getC1() == 0) {
                cb.addItem("C1");
            }
            if (s.getC2() == 0) {
                cb.addItem("C2");
            }
            if (s.getC3() == 0) {
                cb.addItem("C3");
            }
            if (s.getC4() == 0) {
                cb.addItem("C4");
            }
            if (s.getC5() == 0) {
                cb.addItem("C5");
            }
            if (s.getC6() == 0) {
                cb.addItem("C6");
            }
        }
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfMail.getText().length() == 0 || tfNom.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        ParticipantE e = new ParticipantE(tfMail.getText(),tfNom.getText(),Integer.parseInt(tfAge.getText()),cb.getSelectedItem().toString());
                        System.out.println(e.toString());
                        if (ServiceEvent.getInstance().addParticipant(e,id)) {
                            Dialog.show("Success", "Participation accepted", new Command("OK"));
                             new ListEventsClient(current).show();
                        } else {
                            Dialog.show("ERROR", "Server Error", new Command("OK"));
                        }
                    } catch (Exception ex) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNom,tfMail,tfAge,cb,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}
