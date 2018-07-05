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
public class SearchParameter extends Parameter {

    private Integer from = 0;
    private Integer size = null;
    /////////////////////

    private boolean match_all = false;
    /////////////////////
    private String sortQuery;
    /////////////////////
    private CoupleNode match;
    /////////////////////
    private CoupleNode term;
    /////////////////////
    private CoupleNode match_phrase;
    /////////////////////
    private CoupleNode match_phrase_prefix;

    //////////////////////////////////////////
    public enum OrderBy {
        asc("asc"),
        desc("desc");
        private String order;

        OrderBy(String name) {
            this.order = name;
        }

        public String getName() {
            return order;
        }
    }

    ///////////////////////////////////////////////////////////////
    public SearchParameter() {
    }

    ///////////////////////////////////////////////////////////////
    public Integer getFrom() {
        return from;
    }

    public SearchParameter From(Integer from) {
        if (from > -1) {
            this.from = from;
        }
        return this;
    }
    /////////////////////

    public Integer getSize() {
        return this.size;
    }

    public SearchParameter Size(Integer size) {
        if (size > 0) {
            this.size = size;
        }
        return this;

    }
    /////////////////////

    public <T> T match(CoupleNode.Field field) {
        if (field == CoupleNode.Field.Parameter) {
            return (T) match.Parameter;
        }
        return (T) match.Value;
    }

    public <T> SearchParameter match(String parameter, T value) {
        if (match == null) {
            match = new CoupleNode();
        }
        this.match.Parameter = parameter;
        this.match.Value = value;
        return this;

    }

    public void match_Clear() {
        this.match = null;
    }
    /////////////////////

    public boolean match_all() {
        return match_all;
    }

    public SearchParameter match_all(boolean match_all) {
        this.match_all = match_all;
        return this;
    }
    /////////////////////

    public <T> T term(CoupleNode.Field field) {
        if (field == CoupleNode.Field.Parameter) {
            return (T) term.Parameter;
        }
        return (T) term.Value;
    }

    public <T> SearchParameter term(String parameter, T value) {
        if (term == null) {
            term = new CoupleNode();
        }
        this.term.Parameter = parameter;
        this.term.Value = value;
        return this;
    }

    public void term_Clear() {
        this.term = null;
    }

    /////////////////////
    public SearchParameter sort(String field, OrderBy order) {
        if (field != null
                && !field.trim().equals("")) {
            sortQuery = "\"sort\" : [{ \""
                    + field + "\" : {\"order\" : \""
                    + order.getName() + "\"}}]";
        } else {
            sortQuery = null;
        }
        return this;
    }

    /////////////////////
    public <T> T match_phrase(CoupleNode.Field field) {
        if (field == CoupleNode.Field.Parameter) {
            return (T) match_phrase.Parameter;
        }
        return (T) match_phrase.Value;
    }

    public <T> void match_phrase(String parameter, T value) {
        if (match_phrase == null) {
            match_phrase = new CoupleNode();
        }
        this.match_phrase.Parameter = parameter;
        this.match_phrase.Value = value;
    }

    public void match_phrase_Clear() {
        this.match_phrase = null;
    }

    /////////////////////
    public <T> T match_phrase_prefix(CoupleNode.Field field) {
        if (field == CoupleNode.Field.Parameter) {
            return (T) match_phrase_prefix.Parameter;
        }
        return (T) match_phrase_prefix.Value;
    }

    public <T> void match_phrase_prefix(String parameter, T value) {
        if (match_phrase_prefix == null) {
            match_phrase_prefix = new CoupleNode();
        }
        this.match_phrase_prefix.Parameter = parameter;
        this.match_phrase_prefix.Value = value;
    }

    public void match_phrase_prefix_Clear() {
        this.match_phrase_prefix = null;
    }

    ///////////////////////////////////////////////////////////////
    public String Body() {
        StringBuilder body = new StringBuilder("{");
        if (from != null) {
            body.append("\"from\":").append(from).append(",");
        }
        if (size != null) {
            body.append("\"size\":").append(size).append(",");
        }
        body.append(query());

        body.append("}");
        return body.toString();
    }

    private String query() {
        StringBuilder result = new StringBuilder().append("\"query\":{");
        if (match_all()) {
            result.append("\"match_all\":{}");
        }
        if (match != null) {
            result.append("\"match\":{\"").append(match.Parameter).append("\":\"").append(match.Value).append("\"}");
        }
        if (match_phrase != null) {
            result.append("\"match_phrase\":{\"").append(match_phrase.Parameter).append("\":\"").append(match_phrase.Value).append("\"}");
        }
        if (match_phrase_prefix != null) {
            result.append("\"match_phrase_prefix\":{\"").append(match_phrase_prefix.Parameter).append("\":\"").append(match_phrase_prefix.Value).append("\"}");
        }
        if (term != null) {
            result.append("\"term\":{\"").append(term.Parameter).append("\":\"").append(term.Value).append("\"}");
        }
        result.append("}");
        if (sortQuery != null
                && !sortQuery.trim().equals("")) {
            result.append(",").append(sortQuery);
        }
        return result.toString();
    }

}
