/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import PICodeName.entities.Booking;
import PICodeName.entities.Evenement;
import PICodeName.entities.ParticipantE;
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
    public ArrayList<ParticipantE> participant;
    public ArrayList<Booking> booking;
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

    //////////////////////////////////////////////////////////////////////
    public boolean addEvent(Evenement e) {
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

            json.put("dateAt", e.getDateAt());
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

    //////////////////////////////////////////////////////////////////////
    public boolean updateEvent(Evenement e, int id) {
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

            json.put("dateAt", e.getDateAt());
            json.put("title", e.getTitle());
            json.put("type", e.getType());
            json.put("description", e.getDescription());
            json.put("localitation", e.getLocalitation());

            post.setUrl("http://127.0.0.1:8000/webserviceseventupdateevent/" + id);
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
                e.setDescription(obj.get("description").toString());
                e.setLocalitation(obj.get("localitation").toString());
                events.add(e);
            }

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return events;
    }

    //////////////////////////////////////////////////////////////////////
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
        return events;
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<Evenement> parseEventDeails(String jsonText) {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(EventsListJson.toString());
            List<Map<String, String>> list = (List<Map<String, String>>) EventsListJson.get("root");

            System.out.println(EventsListJson.get("id"));

            Evenement e = new Evenement();
            float id = Float.parseFloat(EventsListJson.get("id").toString());
            e.setId((int) id);
            e.setTitle(EventsListJson.get("title").toString());
            // e.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
            e.setType(EventsListJson.get("type").toString());
            e.setDescription(EventsListJson.get("description").toString());
            e.setLocalitation(EventsListJson.get("localitation").toString());
            events.add(e);

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return events;
    }
    //////////////////////////////////////////////////////////////////////

    public ArrayList<Evenement> getEventDetails(int id) {
        String url = "http://127.0.0.1:8000/webserviceseventeventdetails/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEventDeails(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    //////////////////////////////////////////////////////////////////////
    public boolean deleteEvent(int id) {
        String url = "http://127.0.0.1:8000/webserviceseventdeleteevent/" + id;
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

    //////////////////////////////////////////////////////////////////////
    public boolean Viewed(int id) {
        String url = "http://127.0.0.1:8000/webserviceseventviewed/" + id;
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

    //////////////////////////////////////////////////////////////////////
    public ArrayList<ParticipantE> parseParticipant(String jsonText) {
        try {
            participant = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                ParticipantE e = new ParticipantE();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);

                float age = Float.parseFloat(obj.get("age").toString());
                e.setAge((int) age);
                e.setNom(obj.get("nom").toString());
                e.setMail(obj.get("mail").toString());
                e.setSeat(obj.get("seat").toString());
                participant.add(e);
            }
            System.out.println(participant);

        } catch (IOException ex) {
            System.out.println("services.ServiceEvent.parseEvents()");

        }
        return participant;
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<ParticipantE> getAllParticipant(int id) {
        String url = "http://127.0.0.1:8000/getparticipant/" + id;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participant = parseParticipant(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participant;
    }

    //////////////////////////////////////////////////////////////////////
    public boolean deleteParticipant(int id, int idparticipation, String seat) {
        String url = "http://127.0.0.1:8000/webserviceseventdeleteparticipant/" + id + "/" + idparticipation + "/" + seat;
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
    public ArrayList<Booking> parseBooking(String jsonText) {
        try {
            booking = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventsListJson.get("root");

            for (Map<String, Object> obj : list) {
                Booking e = new Booking();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                float A1 = Float.parseFloat(obj.get("A1").toString());
                e.setA1((int) A1);
                float A2 = Float.parseFloat(obj.get("A2").toString());
                e.setA2((int) A2);
                float A3 = Float.parseFloat(obj.get("A3").toString());
                e.setA3((int) A3);
                float A4 = Float.parseFloat(obj.get("A4").toString());
                e.setA4((int) A4);
                float A5 = Float.parseFloat(obj.get("A5").toString());
                e.setA5((int) A5);
                float A6 = Float.parseFloat(obj.get("A6").toString());
                e.setA6((int) A6);
                
                float B1 = Float.parseFloat(obj.get("B1").toString());
                e.setB1((int) B1);
                float B2 = Float.parseFloat(obj.get("B2").toString());
                e.setB2((int) B2);
                float B3 = Float.parseFloat(obj.get("B3").toString());
                e.setB3((int) A3);
                float B4 = Float.parseFloat(obj.get("B4").toString());
                e.setB4((int) B4);
                float B5 = Float.parseFloat(obj.get("B5").toString());
                e.setB5((int) B5);
                float B6 = Float.parseFloat(obj.get("B6").toString());
                e.setB6((int) B6);
                
                float C1 = Float.parseFloat(obj.get("C1").toString());
                e.setC1((int) C1);
                float C2 = Float.parseFloat(obj.get("C2").toString());
                e.setC2((int) C2);
                float C3 = Float.parseFloat(obj.get("C3").toString());
                e.setC3((int) C3);
                float C4 = Float.parseFloat(obj.get("C4").toString());
                e.setC4((int) C4);
                float C5 = Float.parseFloat(obj.get("C5").toString());
                e.setC5((int) C5);
                float C6 = Float.parseFloat(obj.get("C6").toString());
                e.setC6((int) C6);
                
                
                

                booking.add(e);
            }

        } catch (IOException ex) {

        }
        return booking;
    }

    //////////////////////////////////////////////////////////////////////
    public ArrayList<Booking> getAllbooking(int id) {
        String url = "http://127.0.0.1:8000/booking/" + id;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                booking = parseBooking(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return booking;
    }
    
    //////////////////////////////////////////////////////////////////////
    public boolean addParticipant(ParticipantE e,int id) {
        JSONObject json = new JSONObject();
        System.out.println(e.toString());
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

            json.put("mail", e.getMail());
            json.put("age", e.getAge());
            json.put("nom", e.getNom());
            json.put("seat", e.getSeat());

            post.setUrl("http://127.0.0.1:8000/addpar/"+id);
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

}
