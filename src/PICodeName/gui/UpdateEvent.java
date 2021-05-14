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
import com.codename1.ui.Display;
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
public class UpdateEvent extends Form {

    Form current;
    Form c;

    public UpdateEvent(Evenement e, String id, Form previous) {
        c = new HomeAdmin();
        current = new ListEvents(c);
        System.out.println(e.toString());

        setTitle("Update Event");
        setLayout(BoxLayout.y());
        TextField tfTitle = new TextField(e.getTitle());
        TextField tfType = new TextField(e.getType());
        TextField tfDescription = new TextField(e.getDescription());
        TextField tfLocalisation = new TextField(e.getLocalitation());
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Button btnValider = new Button("Update Event");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitle.getText().length() == 0 || tfType.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        Evenement e = new Evenement(date.getDate(), tfTitle.getText(), tfType.getText(), tfDescription.getText(), tfLocalisation.getText(), 0, 0);
                        if (ServiceEvent.getInstance().updateEvent(e, Integer.parseInt(id))) {
                            Dialog.show("Success", "Event Updated", new Command("OK"));
                            new HomeAdmin().show();
                        } else {
                            Dialog.show("ERROR", "Server Error", new Command("OK"));
                        }
                    } catch (Exception ex) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
                }
            }
        });
        addAll(tfTitle, tfType, tfDescription, tfLocalisation, date, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ei -> previous.show());

    }

}
