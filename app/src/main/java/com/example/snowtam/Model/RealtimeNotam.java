package com.example.snowtam.Model;

public class RealtimeNotam {
    private String Condition;
    private String all;
    private String StateName;
    private String StateCode;
    private String criticality;
    private String message;
    private String startdate;
    private String type;
    private String Subject;
    private String Created;
    private String isICAO;
    private String Area;
    private String enddate;
    private String Qcode;
    private String location;
    private String id;
    private String SubArea;
    private String Modifier;
    private String entity;
    private String key;
    private String status;

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String StateName) {
        this.StateName = StateName;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String Created) {
        this.Created = Created;
    }

    public String getIsICAO() {
        return isICAO;
    }

    public void setIsICAO(String isICAO) {
        this.isICAO = isICAO;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getQcode() {
        return Qcode;
    }

    public void setQcode(String Qcode) {
        this.Qcode = Qcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubArea() {
        return SubArea;
    }

    public void setSubArea(String SubArea) {
        this.SubArea = SubArea;
    }

    public String getModifier() {
        return Modifier;
    }

    public void setModifier(String Modifier) {
        this.Modifier = Modifier;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RealtimeNotam [Condition = " + Condition + ", all = " + all + ", StateName = " + StateName + ", StateCode = " + StateCode + ", criticality = " + criticality + ", message = " + message + ", startdate = " + startdate + ", type = " + type + ", Subject = " + Subject + ", Created = " + Created + ", isICAO = " + isICAO + ", Area = " + Area + ", enddate = " + enddate + ", Qcode = " + Qcode + ", location = " + location + ", id = " + id + ", SubArea = " + SubArea + ", Modifier = " + Modifier + ", entity = " + entity + ", key = " + key + ", status = " + status + "]";
    }
}
