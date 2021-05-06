/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import PICodeName.entities.Rendezvous;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import services.ServiceEvent;
import services.Servicerdv;

/**
 *
 * @author wassim
 */
public class Addrdv extends Form {

    public Addrdv(Form previous) {
        setTitle("Add New Rendez_vous");
        setLayout(BoxLayout.y());
        TextField tfTitle = new TextField("", "Meet");
        //TextField tfType = new TextField("", "Date");
        TextField tfDescription = new TextField("", "Description");
        TextField tfLocalisation = new TextField("", "Mail_id");
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Button btnValider = new Button("Add Rendez_vous");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitle.getText().length() == 0 || tfDescription.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        Rendezvous e = new Rendezvous(date.getDate(),tfTitle.getText(), tfDescription.getText(),Integer.parseInt(tfLocalisation.getText()));
                        if (Servicerdv.getInstance().addrdv(e)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));

                        } else {
                            Dialog.show("ERROR", "Server Error", new Command("OK"));
                        }
                    } catch (Exception ex) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
                }

            }
        });
        addAll(tfTitle,tfDescription,tfLocalisation,date, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}