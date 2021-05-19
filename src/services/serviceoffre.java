/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import PICodeName.entities.Evenement;
import PICodeName.entities.Offre;
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
 * @author Lenovo
 */
public class serviceoffre {
   
    public ArrayList<Offre> offres;
    public static serviceoffre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private serviceoffre () {
        req = new ConnectionRequest();
    }

    public static serviceoffre getInstance() {
        if (instance == null) {
            instance = new serviceoffre();
        }
        return instance;
    }

    public boolean addoffre(Offre  e) {
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

            
            json.put("specialite", e.getSpecialite());
            json.put("typecategorie", e.getTypecategorieId());
            json.put("description", e.getDescription());
            json.put("localitation", e.getLocalisation());
            json.put("offre nb_dem", e.getNbDem());
            json.put("Viewed", 0);

            post.setUrl("http://127.0.0.1:8000/webserviceseventaddoffre");
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

    //////////////////////////////////////////////////////////////////////
    public boolean updateoffre (Offre e,int id) {
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

           json.put("specialite", e.getSpecialite());
            json.put("typecategorie", e.getTypecategorieId());
            json.put("description", e.getDescription());
            json.put("localitation", e.getLocalisation());
            json.put("offre nb_dem", e.getNbDem());

            post.setUrl("http://127.0.0.1:8000/webserviceseventupdateoffre/"+id);
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
     public ArrayList<Offre> parseoffre (String jsonText) {
        try {
            offres= new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Offre e = new Offre();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setSpecialite(obj.get("specialite").toString());
                // e.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                
                e.setDescription(obj.get("description").toString());
                e.setLocalisation(obj.get("localitation").toString());
                offres.add(e);
            }

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return offres;
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<Offre> getAlloffre() {
        String url = "http://127.0.0.1:8000/webservicesoffreevents";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseoffre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(offres.toString());
        return offres;
    }

    //////////////////////////////////////////////////////////////////////
    public boolean deleteoffre(int id) {
        String url = "http://127.0.0.1:8000/webserviceseventdeletoffre/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(offres.toString());
        return true;
    }

}


