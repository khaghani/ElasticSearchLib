/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author amir
 */
public class Result {

    private String index;
    private boolean acknowledged;
    private boolean shards_acknowledged;
    private transient boolean valid;
    private transient JSONObject jsonObj;

    /////////////////////////////////////////////////////////////////////
    public Result(String obj) {
        if (setJsonObj(obj)) {
            valid = fill();
        }
    }
    /////////////////////////////////////////////////////////////////////

    public JSONObject getJsonObj() {
        return jsonObj;
    }

    public void setJsonObj(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }

    public boolean setJsonObj(String jsonObj) {
        if (jsonObj == null) {
            return false;
        }
        try {
            this.jsonObj = new JSONObject(jsonObj);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    ///////////////////////
    public boolean isValid() {
        return valid;
    }

    ///////////////////////
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    ///////////////////////

    public boolean acknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }
    ///////////////////////

    public boolean shards_acknowledged() {
        return shards_acknowledged;
    }

    public void setShards_acknowledged(boolean shards_acknowledged) {
        this.shards_acknowledged = shards_acknowledged;
    }

    /////////////////////////////////////////////////////////////////////
    public boolean fill() {
        boolean result = true;
        try {
            if (!jsonObj.isNull("acknowledged")) {
                acknowledged = jsonObj.getBoolean("acknowledged");
            }
            if (!jsonObj.isNull("shards_acknowledged")) {
                shards_acknowledged = jsonObj.getBoolean("shards_acknowledged");
            }
            if (!jsonObj.isNull("index")) {
                index = jsonObj.getString("index");
            }
        } catch (JSONException ex) {
            result = false;
            Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
