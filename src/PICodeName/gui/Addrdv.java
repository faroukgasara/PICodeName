/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Rendezvous;
import PICodeName.entities.Surfer;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.notifications.LocalNotification;
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
import com.codename1.util.Base64;
import java.util.ArrayList;
import java.util.Map;
import javafx.geometry.Pos;
import services.Servicerdv;

/**
 *
 * @author wassim
 */
public class Addrdv extends Form {
Form current;
    public Addrdv(Form previous) {
        //notif();
        setTitle("Add New Meet");
        setLayout(BoxLayout.y());
        TextField tfMeet = new TextField("", "Meet");
        TextField tfDescription = new TextField("", "Description");
        ComboBox cb=new ComboBox();
         ArrayList<Surfer> ev = new ArrayList<Surfer>();
        ev = Servicerdv.getInstance().getcombo();

        for (Surfer s : ev) {
cb.addItem(s.getEmailadress());

        }
        //TextField tfMail = new TextField("", "Mail_id");
      
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Button btnValider = new Button("Add Meet");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (tfMeet.getText().length() == 0 || tfDescription.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        Rendezvous e = new Rendezvous(date.getDate(),tfMeet.getText(), tfDescription.getText(),cb.getSelectedIndex()+1);
                        if (Servicerdv.getInstance().addrdv(e,cb.getSelectedItem().toString())) {
                            Dialog.show("Success", "RDV added", new Command("OK"));
                            
                  new Listrdv(current).show();
                            System.out.println(cb.getSelectedIndex()+1);
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
    public void notif() {
        String accountSID = "AC471e653953bb194788b3daa9265d904a";
String authToken = "ba488390fcdab29363e9e170fcb05212";
String fromPhone = "+18162392929";
Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/AC471e653953bb194788b3daa9265d904a/Messages.json").
        queryParam("To", "+21655581858").
        queryParam("From", fromPhone).
        queryParam("Body", "val").
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
    System.out.println("hani houni");
    }
  
}