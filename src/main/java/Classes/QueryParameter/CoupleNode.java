/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.QueryParameter;

/**
 *
 * @author amir
 */
public class CoupleNode {

    public String Parameter;
    public Object Value;

    //////////////////////
    public enum Field {
        Parameter,
        Value;
    }

    //////////////////////////////////////////////////////////////////
    public <T extends Object> void setValue(T value) {
        Value = value;
    }

}
