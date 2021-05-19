/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Offre;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import services.serviceoffre;

/**
 *
 * @author Lenovo
 */
public class updateoffre extends Form{
    public updateoffre(Offre f,int id){
        

        setTitle("Update Offre");
        setLayout(BoxLayout.y());
        TextField specialite = new TextField("", "specialite ");
        TextField localisation = new TextField("", " localisation");
        TextField nb_dem = new TextField("", "nb ");
        TextField description = new TextField("", " description");
        
        Button btnValider = new Button("Add Offre");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (description.getText().length() == 0 || localisation.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        //System.out.println(".actionPerformed()");
                        Offre e = new Offre(specialite.getText(), localisation.getText(), Integer.parseInt(nb_dem.getText()), description.getText());
                        System.out.println(".actionPerformed()");
                        if (serviceoffre.getInstance().updateoffree(e,id)) {
                            Dialog.show("Success", "Offre accepted", new Command("OK"));
                            new HomeAdmin().show();
                            
                        } else {
                            Dialog.show("ERROR", "Server Error", new Command("OK"));
                        }
                    } catch (Exception ex) {
                        Dialog.show("ERROR", ex.toString(), new Command("OK"));
                    }
                }
            }
        });
        addAll(specialite,localisation,nb_dem,description, btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());
        
    }
    
}
