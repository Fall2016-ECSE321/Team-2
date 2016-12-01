package ca.mcgill.ecse321.android_full_ftms;

/**
 * Created by aliel on 2016-11-27.
 */

public class ScheduleDisplayer {

    private String dayOfWeek;
    private String scheduleString;

    public ScheduleDisplayer(String dayOfWeek, String scheduleString){
        this.dayOfWeek = dayOfWeek;
        this.scheduleString = scheduleString;
    }

    public void setDayOfWeek(String dayOfWeek){ this.dayOfWeek = dayOfWeek;}
    public void setScheduleString(String scheduleString){ this.scheduleString = scheduleString;}
    public String getDayOfWeek(){return this.dayOfWeek;}
    public String getScheduleString(){return this.scheduleString;}
}
