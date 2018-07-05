/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElasticSearch.Functions;

import ElasticSearch.Elastic;
import Query.Index;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amir
 */
public final class Indexes {

    List<Index> indexes = new ArrayList<>();
    private ElasticSearch.Elastic elastic;

    ////////////////////////////////////////////////////////////////////////////////
    public Indexes(ElasticSearch.Elastic elastic) {
        setElastic(elastic);
    }
////////////////////////////////////////////////////////////////////////////////

    public Elastic getElastic() {
        return elastic;

    }

    public Indexes setElastic(Elastic elastic) {
        this.elastic = elastic;
        return this;
    }
    ////////////////////////////////////////

    public List<Index> getIndexes() {
        return indexes;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public List<Index> GetAllIndexes() {
        indexes = new ArrayList<>();
        elastic.customQuery("_cat/indices");
        String body = elastic.getBody();
        if (body != null) {
            for (String line : body.split("\n")) {
                indexes.add(new Index(line));
            }
        }
        return indexes;
    }
    ////////////////////////////////////////

}
