/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Formation;
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
public class addformation extends Form {
      public addformation(){
        setTitle("Add New Formation");
        setLayout(BoxLayout.y());
        TextField tfTitle = new TextField("", "Formation Title");
        TextField tfDescription = new TextField("", "Formation Description");
        TextField tfLocalisation = new TextField("", "Formation Localisation");
        TextField tfIdsoc = new TextField("", "ID_soc");
        Picker date = new Picker();
        Button btnValider = new Button("Add Formation");
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitle.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    
                    try {
                        
                        Formation f = new Formation(tfDescription.getText(), date.getDate(),tfTitle.getText(), tfLocalisation.getText(), Integer.parseInt(tfIdsoc.getText()));
                        
                        if (ServiceFormation.getInstance().addformation(f)) {
                            Dialog.show("Success", "Formation accepted", new Command("OK"));
                             
                        } else {
                            Dialog.show("ERROR", "Server Error", new Command("OK"));
                        }
                    } catch (Exception ex) {
                        Dialog.show("ERROR", "ERROR", new Command("OK"));
                    }
                }
            }

            
        });
       
        
        addAll(tfTitle,tfDescription,tfLocalisation,tfIdsoc,date, btnValider);
    }

  

}
    

