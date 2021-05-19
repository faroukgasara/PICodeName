/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import PICodeName.entities.Reclamation;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.HashMap;
import java.util.Map;
import services.ServiceEvent;

import services.ServiceReclamation;

/**
 *
 * @author ala
 */
public class AddReclamation extends Form{
    public AddReclamation(Form previous) {
           
        Form hi = new Form("Button", BoxLayout.y());

        String[] characters = { "bein late", "problem with the connection", "being rude"};

        int size = Display.getInstance().convertToPixels(7);
         setTitle("Add New Complaint");
        setLayout(BoxLayout.y());
        TextField gsm = new TextField("", "gsm");
        TextField message = new TextField("", "message");
        
        
        
        Button btnValider = new Button("Add Complaint");


        MultiButton b = new MultiButton("Pick A reason...");
        b.addActionListener(e -> {
            Dialog d = new Dialog();
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(true);
            for(int iter = 0 ; iter < characters.length ; iter++) {
                MultiButton mb = new MultiButton(characters[iter]);

                d.add(mb);
                mb.addActionListener(ee -> {
                    b.setTextLine1(mb.getTextLine1());
                    b.setTextLine2(mb.getTextLine2());
                    b.setIcon(mb.getIcon());
                    d.dispose();
                    b.revalidate();
                });
            }
            d.showPopupDialog(b);
        });
        
        hi.add(b);
        addAll(hi,gsm,message,btnValider);
        btnValider.addActionListener((e)->{
            try{
               if (b.getText()==""|| message.getText()==""||gsm.getText()==""){
                   Dialog.show("please fill all data","", "discard", "OK");
               } else{
                   InfiniteProgress ip =new InfiniteProgress();
                   final Dialog iDialog=ip.showInfiniteBlocking();
                   Reclamation r=new  Reclamation(String.valueOf(message.getText()).toString(),String.valueOf(b.getText()).toString(),String.valueOf(gsm.getText()).toString());
                   System.out.println("data reclamation"+r);
                   ServiceReclamation.getInstance().ajouterReclamation(r);
                   iDialog.dispose();
                   refreshTheme();
                  new  LIstReclamations(previous).show();
                   
               }
            }catch(Exception ex){
                ex.printStackTrace();
                
            }
        }
        );

    }

               
   }
