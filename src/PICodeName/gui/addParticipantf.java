/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Formation;
import PICodeName.entities.ParticipantF;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
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
public class addParticipantf extends Form {
    
    public addParticipantf(int id){
         setTitle("Add New Formation");
        setLayout(BoxLayout.y());
        TextField tfMail = new TextField("", "Participant mail");
        TextField tfNom = new TextField("", "participant nom");
       
       Button btnValider = new Button("Add Participant");
        
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfMail.getText().length() == 0){
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    
                    try {
                        
                        ParticipantF p = new ParticipantF(tfMail.getText(),tfNom.getText());
                        
                        if (ServiceFormation.getInstance().addParticipantf(p, id)) {
                            Dialog.show("Success", "participation accepted", new Command("OK"));
                             
                        } else {
                            Dialog.show("ERROR", "Server Error", new Command("OK"));
                        }
                    } catch (Exception ex) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
                }
            }

            
        });
       
        
        addAll(tfMail,tfNom, btnValider);
        
        
        
    }
    
}
