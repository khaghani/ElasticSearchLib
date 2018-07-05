/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElasticSearch;

import Classes.HitsObject;
import Errors.ElasticError;
import Messages.Result;
import Query.Delete;
import Query.Query;
import Query.Search;
import Query.Update;
import Query.Insert;
import java.awt.PageAttributes;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author amir
 */
public final class Elastic {

    private int port;
    private String ip;
    ///////////////////
    private String host;
    ///////////////////
    private String username;
    private String password;
    ///////////////////
    PageAttributes.MediaType consumes;
    ///////////////////
    private String body;
    ///////////////////
    private String login;
    ///////////////////
    private boolean hasPassword;
    private boolean hasUsername;
    ///////////////////
    private boolean httpSecure;
    private boolean initialized;
    private boolean isAuthorizated;
    ///////////////////
    private Search search;
    private Insert insert;
    private Update update;
    private Delete delete;
    ///////////////////
    private transient String lastMessage;
    ///////////////////
    private ElasticError error;
    private Result result;
    ///////////////////
    private Query.QueryType qtype;
    /////////////////////////////////////////////////////////

    public Elastic() {
        this("localhost", 9200, null, null, false);
    }

    public Elastic(String ip, int port) {
        this(ip, port, null, null, false);
    }

    public Elastic(String username, String password) {
        this("localhost", 9200, username, password, false);
    }

    public Elastic(String username, String password, boolean httpSecure) {
        this("localhost", 9200, username, password, httpSecure);
    }

    public Elastic(String ip, int port, String username, String password) {
        this(ip, port, username, password, false);
    }

    public Elastic(String ip, int port, String username, String password, boolean httpSecure) {
        setIp(ip);
        setPort(port);
        setPassword(password);
        setUsername(username);
        setHttpSecure(httpSecure);
        setInitialized(Initialize());
    }

    /////////////////////////////////////////////////////////
    public int getPort() {
        return port;
    }

    public Elastic setPort(int port) {
        if (port > -1) {
            this.port = port;
        }
        return this;
    }

    public boolean setPort(String port) {
        if (port == null) {
            return false;
        }
        try {
            int temp = Integer.parseInt(port);
            setPort(temp);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    ///////////////////
    public ElasticError getError() {
        return this.error;
    }

    ///////////////////
    public String getIp() {
        return ip;
    }

    public Elastic setIp(String ip) {
        this.ip = ip;
        return this;
    }
    ///////////////////

    private String getHost() {
        return "http" + (this.httpSecure ? "s://" : "://") + this.ip + ":" + this.port;
    }

    public Elastic setHost(String host) {
        this.host = host;
        return this;
    }
    ///////////////////

    public String getUsername() {
        return username;
    }

    public Elastic setUsername(String username) {
        this.setHasUsername(!(username == null || username.length() < 1));
        this.username = username;
        return this;
    }
    ///////////////////

    public String getPassword() {
        return password;
    }

    public Elastic setPassword(String password) {
        setHasPassword(!(password == null || password.length() < 1));
        this.password = password;
        return this;
    }
    ///////////////////

    public PageAttributes.MediaType getConsumes() {
        return consumes;
    }

    public Elastic setConsumes(PageAttributes.MediaType consumes) {
        this.consumes = consumes;
        return this;
    }
    ///////////////////

    public String getBody() {
        return body;
    }

    public Elastic setBody(String body) {
        this.body = body;
        return this;
    }

    public void BodyClear() {
        this.body = null;
    }
    ///////////////////

    public String getLogin() {
        return login;
    }

    public Elastic setLogin(String login) {
        this.login = login;
        return this;
    }
    ///////////////////

    public boolean isHasPassword() {
        return hasPassword;
    }

    public Elastic setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
        return this;
    }
    ///////////////////

    public boolean isHasUsername() {
        return hasUsername;
    }

    public Elastic setHasUsername(boolean hasUsername) {
        this.hasUsername = hasUsername;
        return this;
    }
    ///////////////////

    public boolean isHttpSecure() {
        return httpSecure;
    }

    public Elastic setHttpSecure(boolean httpSecure) {
        this.httpSecure = httpSecure;
        return this;
    }
    ///////////////////

    public boolean isInitialized() {
        return initialized;
    }

    public Elastic setInitialized(boolean initialized) {
        this.initialized = initialized;
        return this;
    }
    ///////////////////

    public boolean isAuthorizated() {
        return isAuthorizated;
    }

    public Elastic setIsAuthorizated(boolean isAuthorizated) {
        this.isAuthorizated = isAuthorizated;
        return this;
    }
    ///////////////////

    public Result getResult() {
        return result;
    }

    ///////////////////
    public String getLastMessage() {
        return lastMessage;
    }

    private void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    /////////////////////////////////////////////////////////
    private boolean Initialize() {

//        this._elasticNodes = new ArrayList<ElasticNode>();
        this.host = this.getHost();
        if (this.isAuthorizated = (this.hasPassword && this.hasUsername)) {
            String tempLogin = getUsername() + ":" + getPassword();
            setLogin(Base64.getEncoder().encodeToString(tempLogin.getBytes()));
        }
        return true;
    }

    /////////////////////////////////////////////////////////
    public boolean executeDeleteQuery(String query) {

        boolean finalRisult = true;
        String queryTemp;
        if (query.charAt(0) != '/') {
            queryTemp = "/" + query;
        } else {
            queryTemp = query;
        }
        try {
            URL url = new URL(this.host + queryTemp);

            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty(
                    "Content-Type", "application/json");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();

            httpCon.getResponseMessage();

        } catch (MalformedURLException ex) {
            finalRisult = false;
            Logger.getLogger(Elastic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            finalRisult = false;
            Logger.getLogger(Elastic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finalRisult;
    }

    /////////////////////////////////////////////////////////
    public String customQuery(String query) {
        return customQuery(query, null, Connection.Method.GET);
    }

//    public String customQuery(Query query) {
//        return customQuery(Query.fromQuery(query), null, Connection.Method.GET);
//    }
    public String customQuery(String query, String body) {
        return customQuery(query, body, Connection.Method.GET);
    }

    public String customQuery(String query, String body, Connection.Method method) {
        error = null;
        result = null;

        BodyClear();
        try {
            String queryTemp;
            if (query.charAt(0) != '/') {
                queryTemp = "/" + query;
            } else {
                queryTemp = query;
            }

            Connection con = Jsoup.connect(this.host + queryTemp).
                    method(method)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true);
            if (body != null && body.length() > 0) {
                con = con.requestBody(body);
            }
            if (isAuthorizated()) {
                con = con.header("Authorization", "Basic " + this.login);
            }
            con.header("Content-Type", "application/json");
            Connection.Response response = con.execute();
            this.body = response.body();

            error = new ElasticError(this.body);
            result = new Result(this.body);

        } catch (IOException ex) {
            System.err.print(System.getProperty("user.dir") + " -> " + this.getClass().getName()
                    + " type Java Class ->  type func : " + ex.getMessage());
        }
        return this.body;
    }

    /////////////////////////////////////////////////////////
    public void SetQuery(Query query) {
        if (query instanceof Search) {
            this.search = (Search) query;
            this.qtype = Query.QueryType.Search;
        }
        if (query instanceof Insert) {
            this.insert = (Insert) query;
            this.qtype = Query.QueryType.Insert;
        }
        if (query instanceof Update) {
            this.update = (Update) query;
            this.qtype = Query.QueryType.Update;
        }
        if (query instanceof Insert) {
            this.insert = (Insert) query;
            this.qtype = Query.QueryType.Insert;
        }
        if (query instanceof Delete) {
            this.delete = (Delete) query;
            this.qtype = Query.QueryType.Delete;
        }
    }

    public Query executeQuery() {
        switch (qtype) {
            case Search:
                return executeSearchQuery(search);
            case Insert:
                return executeInsertQuery(insert);
            case Update:
                return executeUpdateQuery(update);
            case Delete:
                return executeDeleteQuery(delete);
        }
        return null;
    }

    @SuppressWarnings("null")
    public Search executeSearchQuery(Search searchQuery) {

        JSONObject jobj;

        String queryString = "/" + searchQuery.getIndex() + "/_search";
        String bodyString = null;
        if (searchQuery != null
                && searchQuery.getSearchParameter() != null) {
            bodyString = searchQuery.getSearchParameter().Body();
        }

        try {
            jobj = new JSONObject(this.customQuery(queryString, bodyString, Connection.Method.POST));
            if (!jobj.isNull("took")) {
                searchQuery.setTook(jobj.getInt("took"));
            }
            if (!jobj.isNull("timed_out")) {
                searchQuery.setTimed_out(jobj.getBoolean("timed_out"));
            }
            if (!jobj.isNull("_shards")) {
                JSONObject _shards = jobj.getJSONObject("_shards");
                if (!_shards.isNull("total")) {
                    searchQuery.shards.setTotal(_shards.getInt("total"));
                }
                if (!_shards.isNull("successful")) {
                    searchQuery.shards.setSuccessful(_shards.getInt("successful"));
                }
                if (!_shards.isNull("skipped")) {
                    searchQuery.shards.setSkipped(_shards.getInt("skipped"));
                }
                if (!_shards.isNull("failed")) {
                    searchQuery.shards.setFailed(_shards.getInt("failed"));
                }
            }
            if (!jobj.isNull("hits")) {
                JSONObject hits = jobj.getJSONObject("hits");
                if (!hits.isNull("total")) {
                    searchQuery.hits.setTotal(hits.getInt("total"));
                }
                if (!hits.isNull("max_score")) {
                    searchQuery.hits.setMax_score(hits.getDouble("max_score"));
                }
                if (!hits.isNull("hits")) {
                    JSONArray arr = hits.getJSONArray("hits");
                    for (int index = 0; index < arr.length(); index++) {
                        HitsObject hitsObject = new HitsObject();
                        JSONObject hitObj = arr.getJSONObject(index);
                        if (!hitObj.isNull("_index")) {
                            hitsObject.setIndex(hitObj.getString("_index"));
                        }
                        if (!hitObj.isNull("_type")) {
                            hitsObject.setType(hitObj.getString("_type"));
                        }
                        if (!hitObj.isNull("_id")) {
                            hitsObject.setId(hitObj.getString("_id"));
                        }
                        if (!hitObj.isNull("_score")) {
                            hitsObject.setScore(hitObj.get("_score").toString());
                        }
                        if (!hitObj.isNull("_source")) {
                            hitsObject.setSource(hitObj.get("_source"));
                        }

                        searchQuery.hits.hits.add(hitsObject);
                    }
                }
            }

        } catch (JSONException ex) {
            Logger.getLogger(Elastic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return searchQuery;
    }

    @SuppressWarnings("null")
    public Insert executeInsertQuery(Insert insertQuery) {
        JSONObject jobj;

        String queryString = "/" + insertQuery.getIndex() + "/" + insertQuery.getType() + ((insertQuery.getId() == null) ? "" : ("/" + insertQuery.getId()));
        String bodyString = null;
        if (insertQuery != null
                && insertQuery.getInsertParameter() != null) {
            bodyString = insertQuery.getInsertParameter().Body();
        }

        try {
            jobj = new JSONObject(this.customQuery(queryString, bodyString, Connection.Method.POST));
            System.out.println(jobj);
            if (!jobj.isNull("_index")) {
                insertQuery.setIndex(jobj.getString("_index"));
            }
            if (!jobj.isNull("_type")) {
                insertQuery.setType(jobj.getString("_type"));
            }
            if (!jobj.isNull("_id")) {
                insertQuery.setId(jobj.getString("_id"));
            }
            if (!jobj.isNull("_version")) {
                insertQuery.setVersion(jobj.getInt("_version"));
            }
            if (!jobj.isNull("result")) {
                insertQuery.setResult(jobj.getString("result"));
            }
            if (!jobj.isNull("found")) {
                insertQuery.setFound(jobj.getBoolean("found"));
            }
            if (!jobj.isNull("_seq_no")) {
                insertQuery.setSeq_no(jobj.getInt("_seq_no"));
            }
            if (!jobj.isNull("_primary_term")) {
                insertQuery.setPrimary_term(jobj.getInt("_primary_term"));
            }
            if (!jobj.isNull("_source")) {
                insertQuery.setSource(jobj.getJSONObject("_source").toString());
            }

            if (!jobj.isNull("_shards")) {
                JSONObject _shards = jobj.getJSONObject("_shards");
                if (!_shards.isNull("total")) {
                    insertQuery.shards.setTotal(_shards.getInt("total"));
                }
                if (!_shards.isNull("successful")) {
                    insertQuery.shards.setSuccessful(_shards.getInt("successful"));
                }
                if (!_shards.isNull("skipped")) {
                    insertQuery.shards.setSkipped(_shards.getInt("skipped"));
                }
                if (!_shards.isNull("failed")) {
                    insertQuery.shards.setFailed(_shards.getInt("failed"));
                }

            }

        } catch (JSONException ex) {
            Logger.getLogger(Elastic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return insertQuery;
    }

    @SuppressWarnings("null")
    public Update executeUpdateQuery(Update updateQuery) {
        JSONObject jobj;

        String queryString = "/" + updateQuery.getIndex() + "/" + updateQuery.getType() + "/" + updateQuery.getId();
        String bodyString = null;
        if (updateQuery != null
                && updateQuery.getUpdateParameter() != null) {
            bodyString = updateQuery.getUpdateParameter().Body();
        }

        try {
            jobj = new JSONObject(this.customQuery(queryString, bodyString, Connection.Method.POST));
            if (!jobj.isNull("_index")) {
                updateQuery.setIndex(jobj.getString("_index"));
            }
            if (!jobj.isNull("_type")) {
                updateQuery.setType(jobj.getString("_type"));
            }
            if (!jobj.isNull("_id")) {
                updateQuery.setId(jobj.getString("_id"));
            }
            if (!jobj.isNull("_version")) {
                updateQuery.setVersion(jobj.getInt("_version"));
            }
            if (!jobj.isNull("result")) {
                updateQuery.setResult(jobj.getString("result"));
            }
            if (!jobj.isNull("_seq_no")) {
                updateQuery.setSeq_no(jobj.getInt("_seq_no"));
            }
            if (!jobj.isNull("_primary_term")) {
                updateQuery.setPrimary_term(jobj.getInt("_primary_term"));
            }

            if (!jobj.isNull("_shards")) {
                JSONObject _shards = jobj.getJSONObject("_shards");
                if (!_shards.isNull("total")) {
                    updateQuery.shards.setTotal(_shards.getInt("total"));
                }
                if (!_shards.isNull("successful")) {
                    updateQuery.shards.setSuccessful(_shards.getInt("successful"));
                }
                if (!_shards.isNull("skipped")) {
                    updateQuery.shards.setSkipped(_shards.getInt("skipped"));
                }
                if (!_shards.isNull("failed")) {
                    updateQuery.shards.setFailed(_shards.getInt("failed"));
                }

            }

        } catch (JSONException ex) {
            Logger.getLogger(Elastic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return updateQuery;
    }

    public Delete executeDeleteQuery(Delete deleteQuery) {
        return executeDeleteQuery(deleteQuery.getIndex() + "/" + deleteQuery.getType() + "/" + deleteQuery.getId())
                ? deleteQuery
                : null;
    }

    /////////////////////////////////////////////////////////
//    public Index getDetail(String indexName) {
//        Index result = null;
//        String body = customQuery("_cat/indices");
//        
//        return result;
//    }
    /////////////////////////////////////////////////////////
    public boolean isReachable() {

        String customBody = customQuery(" ");
        if (customBody == null) {
            return false;
        }
        boolean finalResult;
        try {
            JSONObject json = new JSONObject(customBody);
            setLastMessage(
                    ""
            );
            if (!json.isNull("version")
                    && !json.getJSONObject("version").isNull("number")) {
                setLastMessage("version number: " + json.getJSONObject("version").getString("number"));

            }

//            setLastMessage();
            finalResult = true;
        } catch (JSONException ex) {
            finalResult = false;
            Logger.getLogger(Elastic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finalResult;
    }
}
