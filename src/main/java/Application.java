import java.util.ArrayList;
import java.util.UUID;

public class Application {

    public static void main(String[] args) {
        ArrayList<UUID> calendarIds = new ArrayList<>();
        calendarIds.add(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9")); //Joanna Hef
        calendarIds.add(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9")); //Danny Boy
        calendarIds.add(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e9")); //Emma Win

        Integer duration = 60;
        String periodToSearch = "2019-04-23T06:00:00Z/2019-05-11T15:30:00Z";

        ArrayList<Timeslot> availableTimes = findAvailableTime(calendarIds, duration, periodToSearch);
        System.out.println("Available time: " + availableTimes.size() + " slots");
        System.out.println(availableTimes);
    }


    public static ArrayList<Timeslot> findAvailableTime (
            ArrayList<UUID> calendarIds,
            Integer duration,
            String periodToSearch
    ) {
        if(calendarIds.size()==0) {
            throw new RuntimeException("There should be at least one calender id.");
        }

        AppointmentPlanner appointmentPlanner = new AppointmentPlanner(calendarIds, duration, periodToSearch);
        return appointmentPlanner.findAvailableTime();
    }

}
