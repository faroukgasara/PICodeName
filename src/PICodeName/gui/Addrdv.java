/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Rendezvous;
import PICodeName.entities.Surfer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import java.util.ArrayList;
import services.Servicerdv;

/**
 *
 * @author wassim
 */
public class Addrdv extends Form {

    public Addrdv(Form previous) {
        
        setTitle("Add New Rendez_vous");
        setLayout(BoxLayout.y());
        TextField tfMeet = new TextField("", "Meet");
        TextField tfDescription = new TextField("", "Description");
        ComboBox cb=new ComboBox();
         ArrayList<Surfer> ev = new ArrayList<Surfer>();
        ev = Servicerdv.getInstance().getcombo();

        for (Surfer s : ev) {
cb.addItem(s.getId());

        }
        //TextField tfMail = new TextField("", "Mail_id");
      
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Button btnValider = new Button("Add Rendez_vous");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfMeet.getText().length() == 0 || tfDescription.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        Rendezvous e = new Rendezvous(date.getDate(),tfMeet.getText(), tfDescription.getText(),Integer.parseInt(cb.getSelectedItem().toString()));
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
        addAll(tfMeet,tfDescription,cb,date, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

    }
  
}