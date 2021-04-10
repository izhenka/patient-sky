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

        ArrayList<Calender> calenders = createCalenders(calendarIds);
        String[] periodSplit = periodToSearch.split("/");
        if (periodSplit.length != 2) {
            throw new RuntimeException("Wrong format of periodToSearch.");
        }
        Instant periodStart = Instant.parse(periodSplit[0]);
        Instant periodEnd = Instant.parse(periodSplit[1]);


        for (Calender calender : calenders) {

            ArrayList<Appointment> appointments = calender.getAppointments();
            appointments.sort(Comparator.comparing(Appointment::getStart));
            for (Appointment appointment : appointments) {
                Instant start = appointment.getStart();
                Instant end = appointment.getEnd();
//                System.out.println("" + start + " - " + end);
//                if (start.compareTo(periodStart)>0){
//
//                }
            }
        }


        return null;
    }


    public static ArrayList<Calender> createCalenders(ArrayList<UUID> calendarIds) {

        //TODO: flytte? Feilhåndering hvis id ikke er definert, ikke så viktig
        HashMap<UUID, String> calendarIdToName = new HashMap<>();
        calendarIdToName.put(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9"), "Joanna Hef");
        calendarIdToName.put(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9"), "Danny boy");
        calendarIdToName.put(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e9"), "Emma Win");

        ArrayList<Calender> calenders = new ArrayList<>();
        for (UUID id : calendarIds) {
            Calender calender = new Calender(id, calendarIdToName.get(id));
            calenders.add(calender);
            calender.readFromFile();
        }
        return calenders;
    }

}
