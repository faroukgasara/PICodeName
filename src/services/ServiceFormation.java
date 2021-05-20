/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import PICodeName.entities.Formation;
import PICodeName.entities.ParticipantF;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.json.JSONObject;

/**
 *
 * @author fedi
 */
public class ServiceFormation {
    
    public ArrayList<Formation> forms;
    private ConnectionRequest req;
    public static ServiceFormation instance = null;
    public ArrayList<ParticipantF> participants;
    
    
     private ServiceFormation() {
        req = new ConnectionRequest();
    }

    public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }
    
    
    
     public boolean addformation(Formation f) {
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
            json.put("description", f.getDescription());
            json.put("dateAt", f.getDateAt());
            json.put("title", f.getTitle());
            json.put("localisation", f.getLocalisation());
            json.put("id_soc", f.getId_soc());
            
            System.out.println("kkkkk");
            post.setUrl("http://127.0.0.1:8000/webserviceseventaddformation");
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
     
      public ArrayList<Formation> parseformations(String jsonText) {
        try {
            forms = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Formation f = new Formation();
                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setTitle(obj.get("title").toString());
                //f.setDateAt(Date.from(Instant.MIN));
                f.setDescription(obj.get("description").toString());
                f.setLocalisation(obj.get("localisation").toString());
                forms.add(f);
            }

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return forms;
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<Formation> getAllFormations() {
        String url = "http://127.0.0.1:8000/webservicesafficheformation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                forms = parseformations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return forms;
    }
     
     public boolean updateFormation(Formation f, int id) {
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

            json.put("dateAt", f.getDateAt());
            json.put("title", f.getTitle());
            
            json.put("description", f.getDescription());
            json.put("localisation", f.getLocalisation());

            post.setUrl("http://127.0.0.1:8000/webserviceseventupdateformation/" + id);
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
     
      public boolean deleteFormation(int id) {
        String url = "http://127.0.0.1:8000/webserviceseventdeleteformation/" + id;
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

     
//**************************participant***********************
      
      
       public ArrayList<ParticipantF> parseParticipantf(String jsonText) {
        try {
            participants = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
            ParticipantF p = new ParticipantF(0);

                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);

                
                p.setNom(obj.get("nom").toString());
                p.setMail(obj.get("mail").toString());
              
                participants.add(p);
            }
            System.out.println(participants);

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return participants;
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<ParticipantF> getAllParticipantf(int id) {
        String url = "http://127.0.0.1:8000/webservicesafficheparticipantf/ "+ id ;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participants = parseParticipantf(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participants;
    }

    //////////////////////////////////////////////////////////////////////
    public boolean deleteParticipantf(int id, int idparticipation) {
        String url = "http://127.0.0.1:8000/webserviceseventdeleteparticipantf/" + id  ;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }

    //////////////////////////////////////////////////////////////////////
    public boolean addParticipantf(ParticipantF p,int id) throws IOException {
        JSONObject json = new JSONObject();
        System.out.println(p.toString());
         try {
           String att = p.getNom()+ " " + p.getMail();
           String qrCodeData = att;
           
            String filePath = "formation.png";
            String charset = "UTF-8"; 
            Map < com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel > hintMap = new Hashtable<com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel > ();
            hintMap.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);
            com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200 , (Hashtable) hintMap);
            
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
            
           System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
        String to = p.getMail();
        String host = "smtp.gmail.com";
        final String mail = "handclasp1@gmail.com";
        final String password = "handclasp11223344";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(mail);
            m.addRecipients(javax.mail.Message.RecipientType.TO, to);
            m.setSubject("Participation");
            m.setText("Participation Confirmed Mr/Mr:" + p.getNom() );
             m.addRecipients(Message.RecipientType.TO, to);
            
            Multipart emailContent = new MimeMultipart();
            
            MimeBodyPart TextBodyPArt = new MimeBodyPart();
            TextBodyPArt.setText("formation");
            
            MimeBodyPart imageBodyPArt = new MimeBodyPart();
            imageBodyPArt.attachFile("C:\\Users\\fedi\\Documents\\GitHub\\PICodeName\\formation.png");
            
            emailContent.addBodyPart(TextBodyPArt);
            emailContent.addBodyPart(imageBodyPArt);
            m.setContent(emailContent);
            Transport.send(m);

        } catch (MessagingException ex) {
        }
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

            json.put("mail", p.getMail());
            json.put("nom", p.getNom());
           

            post.setUrl("http://127.0.0.1:8000/webserviceseventaddparticipantf/"+id);
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

    }}

      
   
    

