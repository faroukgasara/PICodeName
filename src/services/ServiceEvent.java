/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import PICodeName.entities.Evenement;
import PICodeName.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author farou
 */
public class ServiceEvent {

    public ArrayList<Evenement> events;
    public static ServiceEvent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public boolean addEvent(Evenement e) {
        /*json.put("title", "Test");
                    json.put("type", "Test");
                    json.put("description", "Test");
                    json.put("localitation", "Testpw");
                    json.put("id_societe", 1);
                  req.setUrl("http://127.0.0.1:8000/webserviceseventaddevent");*/
        JSONObject json = new JSONObject();
        try {
            ConnectionRequest post = new ConnectionRequest() {
                @Override
                protected void buildRequestBody(OutputStream os) throws IOException {
                    os.write(json.toString().getBytes("UTF-8"));
                }

                @Override
                protected void readResponse(InputStream input) throws IOException {
                }

                @Override
                protected void postResponse() {
                }
            };
            /*json.put("title", e.getTitle());
            json.put("type", e.getType());
            json.put("description", e.getDescription());
            json.put("localitation", e.getLocalitation());
            json.put("id_societe", 1);*/

            json.put("title", e.getTitle());
            json.put("type", e.getType());
            json.put("description", e.getDescription());
            json.put("localitation", e.getLocalitation());
            json.put("id_societe", e.getIdSociete());
            json.put("Viewed", 0);

            post.setUrl("http://127.0.0.1:8000/webserviceseventaddevent");
            post.setPost(true);
            post.setContentType("application/json");
            post.addArgument("body", json.toString());
            String bodyToString = json.toString();
            NetworkManager.getInstance().addToQueueAndWait(post);
            Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(post.getResponseData()), "UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;

    }

    public ArrayList<Evenement> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setTitle(obj.get("title").toString());
                // e.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                e.setType(obj.get("type").toString());
                events.add(e);
            }

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return events;
    }

    public ArrayList<Evenement> getAllEvents() {
        String url = "http://127.0.0.1:8000/webserviceseventevents";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(events.toString());
        return events;
    }

}
