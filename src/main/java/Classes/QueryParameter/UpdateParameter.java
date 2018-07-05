/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.QueryParameter;

import com.google.gson.Gson;

/**
 *
 * @author amir
 */
public final class UpdateParameter extends Parameter {

    private String body = null;
    ///////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public UpdateParameter() {

    }

    public <T> UpdateParameter(T node) {
        setUpdateItem(node);
    }

    /////////////////////////////////////////////////////////////////////////////////
    public <T> void setUpdateItem(T node) {
        Gson gson = new Gson();
        body = gson.toJson(node);
    }

    public String Body() {
        return this.body;
    }
}
