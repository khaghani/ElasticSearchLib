/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

import Classes.QueryParameter.SearchParameter;

/**
 *
 * @author amir
 */
public class Search extends Query {
    ///////////////////////
    
    private int took;
    private boolean timed_out;

    /////////////////////////////////////////////////////////////////////
    public Search(String index, String type) {
        this(index,type,null);
    }
    public Search(String index, String type,SearchParameter searchParameter) {
        super(QueryType.Search);
        super.setIndex(index);
        super.setType(type);
        super.setSearchParameter(searchParameter);
    }
    /////////////////////////////////////////////////////////////////////
    
    

    public int getTook() {
        
        return took;
    }

    public void setTook(int took) {
        this.took = took;
    }
    ///////////////////////

    public boolean isTimed_out() {
        return timed_out;
    }

    public void setTimed_out(boolean timed_out) {
        this.timed_out = timed_out;
    }
    
}
