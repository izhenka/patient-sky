import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.UUID;

public class Application {
    public static void main(String[] args) {

        ArrayList<UUID> calendarIds = new ArrayList<>();
        calendarIds.add(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9")); //Joanna Hef
//        calendarIds.add(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9")); //Danny Boy
//        calendarIds.add(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e9")); //Emma Win

        Integer duration = 20;
        String periodToSearch = "2019-03-01T13:00:00Z/2019-05-11T15:30:00Z";

        findAvailableTime(calendarIds, duration, periodToSearch);

        //1. Jeg antar at det ligger kun appointments og timeslots som hører til én kalender i én fil
    }

    public static ArrayList<Timeslot> findAvailableTime(
            ArrayList<UUID> calendarIds,
            Integer duration,
            String periodToSearch
//                Uuid timeSlotType
    ) {
        AppointmentPlanner appointmentPlanner = new AppointmentPlanner(calendarIds, duration, periodToSearch);
        appointmentPlanner.findAvailableTime();
        return null;
    }


}
