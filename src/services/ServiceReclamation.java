/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import PICodeName.entities.Evenement;
import PICodeName.entities.Reclamation;

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
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import org.json.JSONObject;

/**
 *
 * @author ala
 */
public class ServiceReclamation {
    public static ServiceReclamation instance=null;
    public ArrayList<Reclamation> rec;
    public ArrayList<Integer> statage;
    // init
    private ConnectionRequest req;
    public static ServiceReclamation getInstance(){
        if(instance==null){
                instance=new ServiceReclamation();
        }
        return instance;
    }
    
    public ServiceReclamation(){
        req=new ConnectionRequest();
    }
    public void ajouterReclamation (Reclamation rec,String dd){
         String to = dd;
        String host = "smtp.gmail.com";
        final String mail = "handclasp1@gmail.com";
        final String password = "handclasp11223344";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(mail);
            m.addRecipients(javax.mail.Message.RecipientType.TO, to);
            m.setSubject("COMPLAINT");
            m.setText("YOUR COMPLAINT HAS BEEN CREATED AT:"+rec.getCreatedAt()+" WE WILL CONTACT YOU SOON "+" ");
            Transport.send(m);

        } catch (MessagingException ex) {
        }
        
        
        
        
        String url="http://127.0.0.1:8000/addReclamation?message="+rec.getMessage()+"&motif="+rec.getMotif()+"&gsm="+rec.getGsm();
        req.setUrl(url);
        req.addResponseListener((e)->{
            String str =new String(req.getResponseData());
            System.out.println("date=="+str);
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Reclamation>affichageReclamationsa(){
        ArrayList<Reclamation> result=new ArrayList<>();
        String url="http://127.0.0.1:8000/displayReclamations";
        req.setUrl(url);
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp;
            jsonp =new JSONParser();
            
            try{
                Map<String,Object> mapReclamations =jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapReclamations.get("root");
                
                for (Map<String,Object>obj :listOfMaps){
                    Reclamation re =new Reclamation();
                    float id =Float.parseFloat(obj.get("id").toString());
                    String message=obj.get("message").toString();
                    String motif=obj.get("motif").toString();
                   // String gsm=obj.get("gsm").toString();
                    re.setId((int)id);
                    re.setMessage(message);
                    re.setMotif(motif);
                  //  re.setGsm(gsm);
                    result.add(re);
                    System.out.println("data="+re);
                    
                }
            }catch(Exception ex){
                System.out.println("services.ServiceReclamation.affichageReclamationsa()");
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
      //////////////////////////////////////////////////////////////////////
    public ArrayList<Reclamation> search(String motif) {
        String url = "http://127.0.0.1:8000/webserviceseventeventssearch/" + motif;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rec = parseRec(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rec;
    }
        //////////////////////////////////////////////////////////////////////
    public ArrayList<Reclamation> parseRec(String jsonText) {
        try {
            rec = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation e = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setMessage(obj.get("message").toString());
                // e.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                e.setMotif(obj.get("motif").toString());
                e.setGsm(obj.get("gsm").toString());
                rec.add(e);
            }

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return rec;
    }
        //////////////////////////////////////////////////////////////////////
    public ArrayList<Integer> parsestatmotif(String jsonText) {
        ArrayList<Integer> e = new ArrayList<Integer>();
        try {
            statage = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");
            
            for (Map<String, Object> obj : list) {

                float age = Float.parseFloat(obj.get("week").toString());
                e.add((int) age);

                float nbage = Float.parseFloat(obj.get("nb").toString());
                e.add((int) nbage);

            }


        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return e;
    }
    
    //***
      public ArrayList<Integer> getstatmotif() {
        String url = "http://127.0.0.1:8000/statmotif";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                statage = parsestatmotif(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return statage;
    }
    //***********
    public boolean updateRec(Reclamation e, int id) {
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
            json.put("motif", e.getMotif());
            json.put("message", e. getMessage());
            
            json.put("gsm", e.getGsm());
           

            post.setUrl("http://127.0.0.1:8000/webserviceseventupdaterec/" + id);
            post.setPost(true);
            post.setContentType("application/json");
            post.addArgument("body", json.toString());
            String bodyToString = json.toString();
            NetworkManager.getInstance().addToQueueAndWait(post);
          //  Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(post.getResponseData()), "UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;

    }
        //***********
    public boolean ValiderRec(Reclamation e, int id) {
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
            
           

            post.setUrl("http://127.0.0.1:8000/ValiderApi/" + id);
            post.setPost(true);
            post.setContentType("application/json");
            post.addArgument("body", json.toString());
            String bodyToString = json.toString();
            NetworkManager.getInstance().addToQueueAndWait(post);
          //  Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(post.getResponseData()), "UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;

    }
    
       public boolean deleteRec(int id) {
        String url = "http://127.0.0.1:8000/webserviceseventdeleterec/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
}
