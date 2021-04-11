import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;

import static util.DateUtil.parsePeriod;

public class AppointmentPlanner {

    ArrayList<UUID> calendarIds;
    int duration;
    Instant periodStart;
    Instant periodEnd;


    public AppointmentPlanner(ArrayList<UUID> calendarIds, int duration, String periodToSearch) {
        this.calendarIds = calendarIds;
        this.duration = duration;
        Instant[] period = parsePeriod(periodToSearch);
        this.periodStart = period[0];
        this.periodEnd = period[1];
        System.out.println("period:" + this.periodStart + " - " + this.periodEnd);
    }


    void findAvailableTime() {
        ArrayList<Calender> calenders = createCalenders();
        ArrayList<ArrayList<Timeslot>> availableTimeslotsForCalenders = new ArrayList<>();
        for (Calender calender : calenders) {
            ArrayList<Timeslot> availableTimeslots = calender.getAvailableTimeslots(periodStart, periodEnd);
            availableTimeslotsForCalenders.add(availableTimeslots);
        }
        System.out.println(availableTimeslotsForCalenders);

        ArrayList<Timeslot> availableTimeslotsForFirstCalender = availableTimeslotsForCalenders.get(0);

    }



    public ArrayList<Calender> createCalenders() {
        //TODO: flytte? Feilhåndering hvis id ikke er definert, ikke så viktig
        HashMap<UUID, String> calendarIdToName = new HashMap<>();
        calendarIdToName.put(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9"), "Joanna Hef");
        calendarIdToName.put(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9"), "Danny boy");
        calendarIdToName.put(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e9"), "Emma Win");

        ArrayList<Calender> calenders = new ArrayList<>();
        for (UUID id : this.calendarIds) {
            Calender calender = new Calender(id, calendarIdToName.get(id));
            calenders.add(calender);
            calender.readFromFile();
        }
        return calenders;
    }

}
