/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.QueryParameter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author amir
 */
public final class InsertParameter extends Parameter {

    private String body = null;

    private boolean includeNull = false;
    ///////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public InsertParameter() {

    }

    public <T> InsertParameter(T node) {
        this(node,false);
    }
    public <T> InsertParameter(T node,boolean includeNullParameter){
        setIncludeNull(includeNullParameter);
        setUpdateItem(node);
    }
    ///////////////////////////

    public boolean isIncludeNull() {
        return includeNull;
    }

    public InsertParameter setIncludeNull(boolean includeNull) {
        this.includeNull = includeNull;
        return this;
    }

    ///////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    public <T> void setUpdateItem(T node) {
        Gson gson;
        if (isIncludeNull()) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            gson = gsonBuilder.create();
        } else {
            gson = new Gson();
        }
        body = gson.toJson(node);
    }

    public String Body() {
        return this.body;
    }
}
