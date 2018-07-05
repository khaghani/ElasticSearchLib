/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;


/**
 *
 * @author amir
 */
public class Error {

    public enum Type {
        none,
        index_not_found_exception;

        public static Type fromString(String value) {
            switch (value.toLowerCase()) {
                case "index_not_found_exception":
                    return index_not_found_exception;
            }
            return none;
        }
    }

    public enum ResourceType {
        none,
        index_or_alias;

        public static ResourceType fromString(String value) {
            switch (value.toLowerCase()) {
                case "index_or_alias":
                    return index_or_alias;
            }
            return none;
        }
    }
    private Type type = Type.none;

    private String reason;
    private ResourceType resourceType = ResourceType.none;
    private String resourceId;
    private String index_uuid;
    private String index;
    public Error[] root_cause;
    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public Type getType() {
        return type;
    }

    public Error setType(String type) {
        this.type = Type.fromString(type);
        return this;
    }

    public Error setType(Type type) {
        this.type = type;
        return this;
    }

    ///////////////////////////////
    public String getReason() {
        return reason;
    }

    public Error setReason(String reason) {
        this.reason = reason;
        return this;
    }

    ///////////////////////////////
    public ResourceType getResourceType() {
        return resourceType;
    }

    public Error setResourceType(String resourceType) {
        this.resourceType = ResourceType.fromString(resourceType);
        return this;
    }

    public Error setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    ///////////////////////////////
    public String getResourceId() {
        return resourceId;
    }

    public Error setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    ///////////////////////////////
    public String getIndex_uuid() {
        return index_uuid;
    }

    public Error setIndex_uuid(String index_uuid) {
        this.index_uuid = index_uuid;
        return this;
    }

    ///////////////////////////////
    public String getIndex() {
        return index;
    }

    public Error setIndex(String index) {
        this.index = index;
        return this;
    }

}
