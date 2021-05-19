/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import PICodeName.entities.Reclamation;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ala
 */
public class ServiceReclamation {
    public static ServiceReclamation instance=null;
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
    public void ajouterReclamation (Reclamation rec){
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
                  //  String gsm=obj.get("gsm").toString();
                    re.setId((int)id);
                    re.setMessage(message);
                    re.setMotif(motif);
                  //  re.setGsm(gsm);
                    result.add(re);
                    System.out.println("data="+re);
                    
                }
            }catch(Exception ex){
                ex.printStackTrace();
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    
}
