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
public class Updaterdv extends Form{
Form current;
    public Updaterdv(Rendezvous e ,String id,Form previous) {
        current = new Home();
        
        System.out.println(e.toString());
        
        setTitle("Update Rendez_vous");
        setLayout(BoxLayout.y());
        TextField tfMeet = new TextField(e.getMeet());
        TextField tfDescription = new TextField(e.getDescription());
        ComboBox cb=new ComboBox();
         ArrayList<Surfer> ev = new ArrayList<Surfer>();
        ev = Servicerdv.getInstance().getcombo();

        for (Surfer s : ev) {
cb.addItem(s.getEmailadress());

        }
        //TextField tfMail = new TextField(e.getMail_id());
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Button btnValider = new Button("Update Rendez_vous");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfMeet.getText().length() == 0 || tfDescription.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    
                         try {
                       Rendezvous e = new Rendezvous(date.getDate(),tfMeet.getText(), tfDescription.getText(),cb.getSelectedIndex()+1);
                        if (Servicerdv.getInstance().updaterdv(e,Integer.parseInt(id))) {
                            Dialog.show("Success", "RDV Updated", new Command("OK"));
                             new Listrdv(current).show();
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
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e1 -> previous.show());
    }

    
}
