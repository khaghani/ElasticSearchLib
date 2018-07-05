/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Query;

import Errors.ElasticError;

/**
 *
 * @author amir
 */
public class Index {

    private String index;
    private Health health = Health.none;
    private Status status = Status.none;
    private String uuid;
    private int pri;
    private int rep;
    private long docsCount;
    private long docsDeleted;
    private long storeSize;
    private long priStoreSize;

    public enum Health {
        none,
        yellow,
        red,
        green;

        public static Health fromString(String health) {
            switch (health.toLowerCase()) {
                case "yellow":
                    return yellow;
                case "red":
                    return red;
                case "green":
                    return green;
            }

            return none;
        }
    }

    public enum Status {
        none,
        open;

        public static Status fromString(String status) {
            switch (status.toLowerCase()) {
                case "open":
                    return open;
            }

            return none;
        }
    }
    /////////////////////////////////////
    private ElasticError error;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Index() {
    }

    public Index(String line) {
        Fill(line);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getIndex() {
        return index;
    }

    public Index setIndex(String index) {
        this.index = index;
        return this;
    }
    /////////////////////////////////////

    public Health getHealth() {
        return health;
    }

    public Index setHealth(Health health) {
        this.health = health;
        return this;
    }

    public Index setHealth(String health) {
        this.health = Health.fromString(health);
        return this;
    }

    /////////////////////////////////////
    public Status getStatus() {
        return status;
    }

    public Index setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Index setStatus(String status) {
        this.status = Status.fromString(status);
        return this;
    }
    /////////////////////////////////////

    public String getUuid() {
        return uuid;
    }

    public Index setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
    /////////////////////////////////////

    public int getPri() {
        return pri;
    }

    public Index setPri(int pri) {
        this.pri = pri;
        return this;
    }

    public boolean setPri(String pri) {

        try {
            this.pri = Integer.parseInt(pri);
        } catch (Exception ex) {
            this.pri = -1;
            return false;
        }
        return true;

    }

    /////////////////////////////////////
    public int getRep() {
        return rep;
    }

    public Index setRep(int rep) {
        this.rep = rep;
        return this;
    }

    public boolean setRep(String rep) {

        try {
            this.rep = Integer.parseInt(rep);
        } catch (Exception ex) {
            this.rep = -1;
            return false;
        }
        return true;

    }
    /////////////////////////////////////

    public long getDocsCount() {
        return docsCount;
    }

    public Index setDocsCount(long docsCount) {
        this.docsCount = docsCount;
        return this;
    }

    public boolean setDocsCount(String docsCount) {

        try {
            this.docsCount = Long.parseLong(docsCount);
        } catch (Exception ex) {
            this.docsCount = -1;
            return false;
        }
        return true;

    }
    /////////////////////////////////////

    public long getDocsDeleted() {
        return docsDeleted;
    }

    public Index setDocsDeleted(long docsDeleted) {
        this.docsDeleted = docsDeleted;
        return this;
    }

    public boolean setDocsDeleted(String docsDeleted) {

        try {
            this.docsDeleted = Long.parseLong(docsDeleted);
        } catch (Exception ex) {
            this.docsDeleted = -1;
            return false;
        }
        return true;

    }
    /////////////////////////////////////

    public long getStoreSize() {
        return storeSize;
    }

    public Index setStoreSize(long storeSize) {
        this.storeSize = storeSize;
        return this;
    }

    public boolean setStoreSize(String storeSize) {

        try {
            this.storeSize = Long.parseLong(storeSize);
        } catch (Exception ex) {
            this.storeSize = -1;
            return false;
        }
        return true;

    }

    /////////////////////////////////////
    public long getPriStoreSize() {
        return priStoreSize;
    }

    public Index setPriStoreSize(long priStoreSize) {
        this.priStoreSize = priStoreSize;
        return this;
    }

    public boolean setPriStoreSize(String priStoreSize) {

        try {
            this.priStoreSize = Long.parseLong(priStoreSize);
        } catch (Exception ex) {
            this.priStoreSize = -1;
            return false;
        }
        return true;

    }
    /////////////////////////////////////

    public ElasticError getError() {
        return error;
    }

    public Index setError(ElasticError error) {
        this.error = error;
        return this;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void Fill(String line) {
        String[] itemObjs = line.split(" ");
        if (itemObjs.length > 0) {
            setHealth(itemObjs[0]);
        }
        if (itemObjs.length > 1) {
            setStatus(itemObjs[1]);
        }
        if (itemObjs.length > 2) {
            setIndex(itemObjs[2]);
        }
        if (itemObjs.length > 3) {
            setUuid(itemObjs[3]);
        }
        if (itemObjs.length > 4) {
            setPri(itemObjs[4]);
        }
        if (itemObjs.length > 5) {
            setRep(itemObjs[5]);
        }
        if (itemObjs.length > 6) {
            setDocsCount(itemObjs[6]);
        }
        if (itemObjs.length > 7) {
            setDocsDeleted(itemObjs[7]);
        }
    }

}
