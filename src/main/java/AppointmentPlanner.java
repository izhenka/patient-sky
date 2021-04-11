import java.time.Instant;
import java.util.ArrayList;
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
        //TODO: sjekk på at det er minst 1 calender
        ArrayList<Timeslot> commonAvailableTimeslots = calenders.get(0).getAvailableTimeslots(periodStart, periodEnd);
        for (int i = 1; i < calenders.size(); i++) {
            Calender calender = calenders.get(i);
            ArrayList<Timeslot> availableTimeslots = calender.getAvailableTimeslots(periodStart, periodEnd);
            commonAvailableTimeslots = getCommonAvailableTimeslots(commonAvailableTimeslots, availableTimeslots);
        }

        System.out.println("commonAvailableTimeslots: \n" + commonAvailableTimeslots.size());
        System.out.println(commonAvailableTimeslots);
    }

    public ArrayList<Timeslot> getCommonAvailableTimeslots(ArrayList<Timeslot> availableTimeslots1,
                                                           ArrayList<Timeslot> availableTimeslots2) {

        ArrayList<Timeslot> commonAvailableTimeslots = new ArrayList<>();
        for (Timeslot timeslot1 : availableTimeslots1) {
            for (Timeslot timeslot2 : availableTimeslots2) {
                if (timeslot2.isBeforeTimeslot(timeslot1)) {
                    continue;
                }
                if (timeslot2.isAfterTimeslot(timeslot1)) {
                    break;
                }
                commonAvailableTimeslots.add(timeslot1.getIntersection(timeslot2));
            }
        }
        return commonAvailableTimeslots;
    }


    public ArrayList<Calender> createCalenders() {
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
