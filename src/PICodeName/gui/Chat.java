/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import services.ServiceEvent;

/**
 *
 * @author farou
 */
public class Chat extends Form {

    public Chat() {
        TextField chat = new TextField("", "chat");
        Button btnSend = new Button("Send");
        setLayout(BoxLayout.y());
        setTitle("Chat");
        Form f = new Form(BoxLayout.y());
        Form c = new Home();
        Form previous = new ListEventsClient(c, ServiceEvent.getInstance().getAllEvents());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.show());

        Container list = new Container(BoxLayout.y());
        chat.addActionListener(a -> System.out.println("PICodeName.gui.Chat.<init>()"));

        chat.addActionListener(e -> {

            if (chat.getText().length() == 0) {
                list.add("Bot : You Need To Ask");
            } else if (chat.getText().toLowerCase().contains("hi")) {
                list.add("You :" + chat.getText());
                list.add("Bot :Hello There!");
            } else if (chat.getText().toLowerCase().contains("how are you")) {
                list.add("You :" + chat.getText());
                list.add("Bot :I'm doing well,thanks!");
            } else if (chat.getText().toLowerCase().contains("facebook")) {
                list.add("You :" + chat.getText());
                list.add("Bot :https://www.facebook.com/hand.calsp");
            } else if (chat.getText().toLowerCase().contains("instagram")) {
                list.add("You :" + chat.getText());
                list.add("Bot :https://www.instagram.com/hand_clasp/?hl=en");
            } else {
                list.add("You :" + chat.getText());
                list.add("Bot :Try Again");
            }

        });
        f.add(list);
        addAll(chat, btnSend, f);
    }

}
