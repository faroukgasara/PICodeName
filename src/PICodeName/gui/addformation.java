/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;






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
       
        
        addAll(tfTitle,tfDescription,tfLocalisation,tfIdsoc,date, btnValider);
    }

  

}
    

