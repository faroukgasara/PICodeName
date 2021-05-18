/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author Lenovo
 */
public class addoffre extends Form {
        public addoffre(){
        setTitle("Add New User");
        setLayout(BoxLayout.y());
        TextField tfspecialite = new TextField("", "offre specialite");
        TextField tfTypecategorie = new TextField("", "offre Typecategorie");
        TextField tfDescription = new TextField("", "offre Description");
        TextField tfLocalisation = new TextField("", "offre Localisation");
        TextField tfnb_dem = new TextField("", "offre nb_dem");
        Picker date = new Picker();
        Button btnValider = new Button("Add Offre");

       
        
        addAll(tfspecialite, tfTypecategorie,tfDescription,tfLocalisation,tfnb_dem, btnValider);
    }

  

}
    
