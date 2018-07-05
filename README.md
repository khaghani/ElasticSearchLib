# ElasticSearchLib
A library for using Elastic search in java.

-------------------search-------------------

Elastic elastic = new Elastic("lcoalhost", 9200);
Search search = new Search("index", "type");
search.setSearchParameter(new SearchParameter().match_all(true).Size(10).From(20)); /* {"from":20,"size":10,"query":{"match_all":{}}} */
search=elastic.executeSearchQuery(search);
for(int index=0;index<search.hits.hits.size();index++){
    User user=search.hits.hits.get(index).getSource(User.class); /* 'user' is your class. */
}

-------------------insert-------------------

User user = new User(id:100,name:"something",...); /* 'user' is your class. */
Elastic elastic = new Elastic("lcoalhost", 9200);
Insert insert = new Insert("index", "type");
insert.setInsertParameter(new InsertParameter(user)); 
insert = elastic.executeInsertQuery(insert); /* 'insert' updated with new parameters. */

-------------------update-------------------

Elastic elastic = new Elastic("lcoalhost", 9200);
Update update = new Update("index", "type", "id"); /* 'id' is the id in your index. */
update.setUpdateParameter(new UpdateParameter(user)); /* 'user' is your class. */
update = elastic.executeUpdateQuery(update);
        
-------------------delete-------------------

Elastic elastic = new Elastic("lcoalhost", 9200);
Delete delete = new Delete("index", "type", "id");  /* 'id' is the id in your index. */
delete = elastic.executeDeleteQuery(delete);

---------------------------------------------------------
