/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Evenement;
import PICodeName.entities.Reclamation;
import com.codename1.components.MultiButton;
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
import services.ServiceReclamation;

/**
 *
 * @author farou
 */
public class UpdateRec extends Form {

    Form current;
    Form c;

    public UpdateRec(Reclamation e, String id, Form previous) throws Exception {
        
         String[] characters = { "bein late", "problem with the connection", "being rude"};
         
         Button btnValider = new Button("Add Complaint");


        MultiButton b = new MultiButton("Pick A reason...");
        b.addActionListener(ee -> {
            Dialog d = new Dialog();
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(true);
            for(int iter = 0 ; iter < characters.length ; iter++) {
                MultiButton mb = new MultiButton(characters[iter]);

                d.add(mb);
                mb.addActionListener(eee -> {
                    b.setTextLine1(mb.getTextLine1());
                    b.setTextLine2(mb.getTextLine2());
                    b.setIcon(mb.getIcon());
                    d.dispose();
                    b.revalidate();
                });
            }
            d.showPopupDialog(b);
        });
        c = new HomeAdmin();
        current = new LIstReclamations(c,ServiceReclamation.getInstance().affichageReclamationsa());
        System.out.println(e.toString());

        setTitle("Update rec");
        setLayout(BoxLayout.y());
       // TextField tfTitle = new TextField(e.getMotif());
        TextField tfType = new TextField(e.getMessage());
        TextField tfDescription = new TextField(e.getGsm());
        
        
        Button btnValiderr = new Button("Update rec");

        btnValiderr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfDescription.getText().length() == 0 || tfType.getText().length() == 0|| b.getText().length() == 0) {
                    Dialog.show("Alert", "Please Fill All fields", new Command("OK"));
                } else {
                    try {
                        Reclamation e = new Reclamation(b.getText(), tfType.getText(), tfDescription.getText());
                        if (ServiceReclamation.getInstance().updateRec(e, Integer.parseInt(id))) {
                            Dialog.show("Success", "complaint Updated", new Command("OK"));
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
        addAll(b,tfType, tfDescription,  btnValiderr);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ei -> previous.show());

    }

}
