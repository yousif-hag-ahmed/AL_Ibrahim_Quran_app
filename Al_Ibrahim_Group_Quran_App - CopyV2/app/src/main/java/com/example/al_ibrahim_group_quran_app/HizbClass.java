package com.example.al_ibrahim_group_quran_app;

public class HizbClass {

    private String hizmName;
    private int id;
    private Boolean state ;
    private Boolean pendingState;

    public HizbClass(String hizmName, int id, Boolean state, Boolean pendingState, String personAssigned) {
        this.hizmName = hizmName;
        this.id = id;
        this.state = state;
        this.pendingState = pendingState;
        PersonAssigned = personAssigned;
    }

    private String PersonAssigned ;

    public HizbClass(){}


    public Boolean getPendingState() {
        return pendingState;
    }

    public void setPendingState(Boolean pendingState) {
        this.pendingState = pendingState;
    }

    public String getPersonAssigned() {
        return PersonAssigned;
    }

    public void setPersonAssigned(String personAssigned) {
        PersonAssigned = personAssigned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHizmName() {
        return hizmName;
    }

    public void setHizmName(String hizmName) {
        this.hizmName = hizmName;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
