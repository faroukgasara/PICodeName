/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Formation;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import services.ServiceFormation;

/**
 *
 * @author fedi
 */
public class updateFormation extends Form{
    
    
    
    
    Form current;
    Form c;

    public updateFormation (Formation f, String id, Form previous) {
        c = new HomeAdmin();
        current = new afficheformation();
        System.out.println(f.toString());

        setTitle("Update Formation");
        setLayout(BoxLayout.y());
        TextField tfTitle = new TextField(f.getTitle());
        TextField tfDescription = new TextField(f.getDescription());
        TextField tfLocalisation = new TextField(f.getLocalisation());
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Button btnValider = new Button("Update Formation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitle.getText().length() == 0 ) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                       Formation f = new Formation(tfDescription.getText(), date.getDate(), tfTitle.getText(),  tfLocalisation.getText() );
                        if (ServiceFormation.getInstance().updateEvent(f, Integer.parseInt(id))) {
                            Dialog.show("Success", "Formation Updated", new Command("OK"));
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
        addAll(tfTitle, tfDescription, tfLocalisation, date, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ei -> previous.show());

    }


    
}
