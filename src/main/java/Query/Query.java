/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

import Classes.Hits;
import Classes.QueryParameter.InsertParameter;
import Classes.QueryParameter.SearchParameter;
import Classes.QueryParameter.UpdateParameter;
import Classes.Shards;

/**
 *
 * @author amir
 */
public class Query {

    private QueryType queryType = QueryType.Search;
    ///////////////////////
    private transient String index;
    private transient String type;

    ///////////////////////
    public Shards shards = new Shards();
    ///////////////////////
    public Hits hits = new Hits();

    ///////////////////////
    private SearchParameter searchParameter;
    private UpdateParameter updateParameter;
    private InsertParameter insertParameter;

    ///////////////////////
    public enum QueryType {
        Search,
        Insert,
        Update,
        Delete
    }

    /////////////////////////////////////////////////////////////////////
    public Query() {
        this(null);
    }

    public Query(QueryType qType) {
        setQueryType(qType);
    }
    /////////////////////////////////////////////////////////////////////

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }
    ///////////////////////

    public InsertParameter getInsertParameter() {
        return insertParameter;
    }

    public Query setInsertParameter(InsertParameter insertParameter) {
        this.insertParameter = insertParameter;
        return this;
    }

    ///////////////////////
    public UpdateParameter getUpdateParameter() {
        return updateParameter;
    }

    public void setUpdateParameter(UpdateParameter updateParameter) {
        this.updateParameter = updateParameter;
    }

    ///////////////////////
    public SearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(SearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    ///////////////////////
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    ///////////////////////

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
