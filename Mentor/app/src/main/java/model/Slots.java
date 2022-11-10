package model;

public class Slots {
    long id;
    String name;
    String date;
    String timeStart;
    String timeEnd;
    String mentor;

    public Slots(long id, String name, String date, String timeStart, String timeEnd, String mentor) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.mentor = mentor;
    }

    public Slots(long id, String name, String date, String timeStart, String timeEnd) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Slots(String name, String date, String timeStart, String timeEnd, String mentor) {
        this.name = name;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.mentor = mentor;
    }

    public Slots(String name, String date, String timeStart, String timeEnd) {
        this.name = name;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }
}
