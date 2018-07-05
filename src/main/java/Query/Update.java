/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

/**
 *
 * @author amir
 */
public final class Update extends Query {

    private String id;
    private int version;
    private String result;
    private int seq_no;
    private int primary_term;
    /////////////////////////////////////////////////////////////////////

    public Update(String index, String type,String id) {
        super(QueryType.Update);
        super.setIndex(index);
        super.setType(type);
        setId(id);
    }

    /////////////////////////////////////////////////////////////////////
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }///////////////////////

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }///////////////////////

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }///////////////////////

    public int getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(int seq_no) {
        this.seq_no = seq_no;
    }///////////////////////

    public int getPrimary_term() {
        return primary_term;
    }

    public void setPrimary_term(int primary_term) {
        this.primary_term = primary_term;
    }

}
