/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

import com.google.gson.Gson;


/**
 *
 * @author amir
 */
public class Insert extends Query {

    ///////////////////////
    private String id;
    private int version;
    private String result;
    private int seq_no;
    private int primary_term;
    private boolean found;
    private Object source;

    /////////////////////////////////////////////////////////////////////
    public Insert(String index, String type) {
        this(index, type, null);
    }

    public Insert(String index, String type, String id) {
        super(QueryType.Insert);
        super.setIndex(index);
        super.setType(type);
        setId(id);
    }

    /////////////////////////////////////////////////////////////////////
    public String getId() {
        return id;
    }

    public Insert setId(String id) {
        this.id = id;
        return this;
    }

    ///////////////////////
    public int getVersion() {
        return version;
    }

    public Insert setVersion(int version) {
        this.version = version;
        return this;
    }///////////////////////

    public String getResult() {
        return result;
    }

    public Insert setResult(String result) {
        this.result = result;
        return this;
    }///////////////////////

    public int getSeq_no() {
        return seq_no;
    }

    public Insert setSeq_no(int seq_no) {
        this.seq_no = seq_no;
        return this;
    }///////////////////////

    public int getPrimary_term() {
        return primary_term;
    }

    public Insert setPrimary_term(int primary_term) {
        this.primary_term = primary_term;
        return this;
    }///////////////////////

    public boolean isFound() {
        return found;
    }

    public Insert setFound(boolean found) {
        this.found = found;
        return this;
    }
    ///////////////////////

    public <T> T getSource(Class<T> classOfT) {
        Gson gson = new Gson();

        return gson.fromJson(source.toString(), classOfT);
    }

    public <T> Insert setSource(Object source) {
        this.source = source;
        return this;
    }

}
