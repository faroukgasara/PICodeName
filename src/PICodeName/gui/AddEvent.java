/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class AddEvent extends Form {

    public AddEvent(Form previous) {
        setTitle("Add New Event");
        setLayout(BoxLayout.y());
        TextField tfTitle = new TextField("", "Event Title");
        TextField tfType = new TextField("", "Event Type");
        TextField tfDescription = new TextField("", "Event Description");
        TextField tfLocalisation = new TextField("", "Event Localisation");
        TextField tfIdsoc = new TextField("", "Event ID_SOC");
        Picker date = new Picker();
        Button btnValider = new Button("Add Event");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitle.getText().length() == 0 || tfType.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        Evenement e = new Evenement(tfTitle.getText(), tfType.getText(),tfDescription.getText(), tfLocalisation.getText(), Integer.parseInt(tfIdsoc.getText()),0);
                        if (ServiceEvent.getInstance().addEvent(e)) {
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
        addAll(tfTitle, tfType,tfDescription,tfLocalisation,tfIdsoc,date, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }

}
