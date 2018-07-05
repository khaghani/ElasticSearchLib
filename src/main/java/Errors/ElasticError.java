/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author amir
 */
public final class ElasticError {

    private int status;
    public Error error = new Error();
    private boolean valid;

    private transient JSONObject jsonObj;
    private transient boolean convertingAsWell;

    /////////////////////////////////////////////////////////////////////////////////////////////
    public ElasticError(String obj) {
        if (setJsonObj(obj)) {
            setValid(run());
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    public JSONObject getJsonObj() {
        return jsonObj;
    }

    public ElasticError setJsonObj(JSONObject obj) {
        this.jsonObj = obj;
        return this;
    }

    public boolean setJsonObj(String obj) {
        if (obj == null) {
            return false;
        }
        try {
            setJsonObj(new JSONObject(obj));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    ///////////////////////////////
    public int getStatus() {
        return status;
    }

    public ElasticError setStatus(int status) {
        this.status = status;
        return this;
    }

    ///////////////////////////////
    public Error getError() {
        return error;
    }

    public ElasticError setError(Error error) {
        this.error = error;
        return this;
    }
    ///////////////////////////////

    public boolean isValid() {
        return valid;
    }

    private void setValid(boolean valid) {
        this.valid = valid;
    }
    ///////////////////////////////

    public boolean isConvertingAsWell() {
        return convertingAsWell;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public boolean run() {
        boolean result = false;
        if (jsonObj == null) {
            return result;
        }
        try {
            if (result = !jsonObj.isNull("error")) {
                convertingAsWell = true;

                if (!jsonObj.isNull("root_cause")) {
                    JSONArray json_root_cause = jsonObj.getJSONArray("root_cause");
                    if (json_root_cause.length() > 0) {
                        error.root_cause = new Error[json_root_cause.length()];
                        for (int index = 0; index < error.root_cause.length; index++) {
                            error.root_cause[index] = getRoot_Cause(json_root_cause.getJSONObject(index));
                        }
                    }
                }
                if (!jsonObj.isNull("type")) {
                    error.setType(jsonObj.getString("type"));

                }
                if (!jsonObj.isNull("reason")) {
                    error.setReason(jsonObj.getString("reason"));

                }
                if (!jsonObj.isNull("resource.type")) {
                    error.setResourceType(jsonObj.getString("resource.type"));
                }
                if (!jsonObj.isNull("resource.id")) {
                    error.setResourceId(jsonObj.getString("resource.id"));
                }
                if (!jsonObj.isNull("index_uuid")) {
                    error.setIndex_uuid(jsonObj.getString("index_uuid"));
                }
                if (!jsonObj.isNull("index")) {
                    error.setIndex(jsonObj.getString("index"));
                }
            }
            if (!jsonObj.isNull("status")) {
                setStatus(jsonObj.getInt("status"));
            }

        } catch (JSONException ex) {
            convertingAsWell = false;
            Logger.getLogger(ElasticError.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    private Error getRoot_Cause(JSONObject json) throws JSONException {
        Error result = null;
        if (json != null) {
            if (!json.isNull("type")) {
                result.setType(json.getString("type"));
            }
            if (!json.isNull("reason")) {
                result.setReason(json.getString("reason"));
            }
            if (!json.isNull("resource.type")) {
                result.setResourceType(json.getString("resource.type"));
            }
            if (!json.isNull("resource.id")) {
                result.setResourceId(json.getString("resource.id"));
            }
            if (!json.isNull("index_uuid")) {
                result.setIndex_uuid(json.getString("index_uuid"));
            }
            if (!json.isNull("index")) {
                result.setIndex(json.getString("index"));
            }
        }
        return result;
    }

}
