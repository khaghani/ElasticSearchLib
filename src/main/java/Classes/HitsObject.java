/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.google.gson.Gson;

/**
 *
 * @author amir
 */
public class HitsObject {

    private String index;
    private String type;
    private String id;
    private String score;
    /////////////////////////
    private Object source;

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    /////////////////////////

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    /////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    /////////////////////////

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
    /////////////////////////

    public <T> T getSource(Class<T> classOfT) {
        Gson gson=new Gson();
        return gson.fromJson(source.toString(), classOfT);
//        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
    
    

}
