package com.relax.birdie.relax;

public class Events {

    public static class Event{
    private String eventName;
    private int pictureId;
    private String eventType;
    private String eventDate;
    private String eventTime;

    public Event(String eventName, int pictureId, String eventType, String eventDate, String eventTime) {
        this.eventName = eventName;
        this.pictureId = pictureId;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public int getPicture() {
        return pictureId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

}

public static Events.Event[] events= {
        new Event("Meeting with Boss", R.drawable.time, "Meeting", "02/05/2019" , ""),
        new Event("Going to gym", R.drawable.time, "Sports", "01/05/2019" , "" ),
        new Event("Dinner with parents", R.drawable.time, "Parents", "29/04/2019" ,""),
        new Event("Study for final ",R.drawable.time, "Study", "low", ""),
        new Event("Meet with Merve Isler", R.drawable.time, "Friends", "low" , ""),
        new Event("Mayfest", R.drawable.time, "Event", "low", ""),
        new Event("OS Final",R.drawable.time,"Study " , "normal" , ""),
        new Event("Meet with Betim ",R.drawable.time,"Friends" , "normal" , ""),
        new Event("Go to Corvus", R.drawable.time,"Friends" , "normal",""),
        new Event("Study for exam" ,R.drawable.time, "Study", "high",""),
        new Event("Interview ", R.drawable.time , "Meeting", "high",""),
        new Event("Going to gym", R.drawable.time, "", "high","")};


        }
