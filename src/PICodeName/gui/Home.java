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

import PICodeName.entities.Evenement;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import PICodeName.gui.SignInForm;
import PICodeName.gui.SignUpForm;
import com.codename1.components.ImageViewer;

import java.util.ArrayList;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class Home extends Form {

    Form current;
    private Form current1;
    private Resources theme;

    public Home() {

        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        Toolbar tb = getToolbar();
        tb.addMaterialCommandToSideMenu("Les Evenement", FontImage.MATERIAL_UPDATE, e -> new ListEventsClient(this, ServiceEvent.getInstance().getAllEvents()).show());

        
        
      

        
        
        //addAll(btnValider);

    }

}
