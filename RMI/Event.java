package RMI;

import java.util.*;
import java.io.*;

public class Event implements Serializable
{
    private String id;
    private String eventName;
    private String eventDate;
    private String eventCode;
    private int expectedAttendance;
    private static final long SerialVersionUID = 20241131L;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEventCode() {
        return eventCode;
    }
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public int getExpectedAttendance() {
        return expectedAttendance;
    }
    public void setExpectedAttendance(int expectedAttendance) {
        this.expectedAttendance = expectedAttendance;
    }
    public static long getSerialversionuid() {
        return SerialVersionUID;
    }
    @Override
    public String toString() {
        return "Event [id=" + id + ", eventName=" + eventName + ", eventDate=" + eventDate + ", expectedAttendance="
                + expectedAttendance + "]";
    }
    public Event(String id, String eventName, String eventDate, int expectedAttendance) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.expectedAttendance = expectedAttendance;
    }
    public Event() {
    }
}