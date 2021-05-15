/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import PICodeName.entities.Rendezvous;
import PICodeName.entities.Surfer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ComboBox;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;

/**
 *
 * @author wassim
 */
public class Servicerdv {
    public ArrayList<Rendezvous> events;
    public ArrayList<Surfer> events1;
    public static Servicerdv instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Servicerdv() {
        req = new ConnectionRequest();
    }

    public static Servicerdv getInstance() {
        if (instance == null) {
            instance = new Servicerdv();
        }
        return instance;
    }

    public boolean addrdv(Rendezvous e) {
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

            json.put("meet", e.getMeet());
            json.put("date", e.getDate());
            json.put("description", e.getDescription());
            json.put("mail_id", e.getMail_id());
           

            post.setUrl("http://127.0.0.1:8000/webservicesaddrdv");
            post.setPost(true);
            post.setContentType("application/json");
            post.addArgument("body", json.toString());
            String bodyToString = json.toString();
            NetworkManager.getInstance().addToQueueAndWait(post);
            Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(post.getResponseData()), "UTF-8"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());;
        }
        return true;

    }
     public boolean updaterdv(Rendezvous e,int id) {
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

            json.put("meet", e.getMeet());
            json.put("date", e.getDate());
            json.put("description", e.getDescription());
            //json.put("mail_id", e.getMail_id());

            post.setUrl("http://127.0.0.1:8000/webservicesupdaterdv/"+id);
            post.setPost(true);
            post.setContentType("application/json");
            post.addArgument("body", json.toString());
            String bodyToString = json.toString();
            NetworkManager.getInstance().addToQueueAndWait(post);
            Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(post.getResponseData()), "UTF-8"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());;
        }
        return true;

    }

    public ArrayList<Rendezvous> parserdv(String jsonText) throws Exception  {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Rendezvous e = new Rendezvous();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                
                e.setMeet(obj.get("meet").toString());
                String sDate1=obj.get("date").toString();  
                Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00").parse(sDate1);
                e.setDate(date);
                // e.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                e.setDescription(obj.get("description").toString());
                events.add(e);
            }

        } catch (IOException ex) {
            System.out.println("services.Servicerdv.parserdv()");

        }
        return events;
    }
     public ArrayList<Surfer> parsecombo(String jsonText) throws Exception  {
        try {
            events1 = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Surfer e = new Surfer();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setEmailadress(obj.get("emailadress").toString());
                
                events1.add(e);
            }

        } catch (IOException ex) {
            System.out.println("services.Servicerdv.parsecombo()");

        }
        return events1;
    }
     public ArrayList<Surfer> getcombo() {
        String url = "http://127.0.0.1:8000/webservicescombo";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    events1 = parsecombo(new String(req.getResponseData()));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());;
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(events1.toString());
        return events1;
    }

    public ArrayList<Rendezvous> getAllrdvs() {
        String url = "http://127.0.0.1:8000/webservicesrdvs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    events = parserdv(new String(req.getResponseData()));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());;
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(events.toString());
        return events;
    }
    public boolean deleterdv(int id) {
        String url = "http://127.0.0.1:8000/webservicesdeleterdv/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(events.toString());
        return true;
    }
}