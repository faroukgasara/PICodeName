/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import PICodeName.gui.SignInForm;
import PICodeName.gui.SignUpForm;
/**
 *
 * @author farou
 */
public class Home extends Form{
    Form current;
     private Form current1;
     private Resources theme;
    public Home(){
     
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose"));
        Button btnAddEvent = new Button("Add Event");
        Button btnListEvents = new Button("List Events");
     
        btnAddEvent.addActionListener(e-> new AddEvent(current).show());
        btnListEvents.addActionListener(e-> new ListEvents(current).show());
      
        addAll(btnAddEvent,btnListEvents);
   
     
  


        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        //houni awl interface yet7al
        new SignUpForm(theme).show(); //n7oto signup bch yjibha awl form
       // new SignInForm(theme).show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
