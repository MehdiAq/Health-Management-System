package MFD.HealthManagementSystem.model;

import java.sql.Time;
import java.time.LocalTime;

public enum TimeSlots {

    A("8:00-9:00"), B("9:00-10:00"), C("10:00-11:00"), D("11:00-12h"), E("13:00-14h00"), F("14:00-15:00"), G("15:00-16:00"), H("16:00-17:00");

    private final String startTime;

    TimeSlots(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return startTime;
    }

}
