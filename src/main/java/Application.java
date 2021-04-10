import java.util.ArrayList;
import java.util.UUID;

public class Application {
    public static void main(String[] args) {

        ArrayList<UUID> calendarIds = new ArrayList<UUID>();
        calendarIds.add(UUID.fromString("48644c7a-975e-11e5-a090-c8e0eb18c1e9")); //Joanna Hef
        calendarIds.add(UUID.fromString("48cadf26-975e-11e5-b9c2-c8e0eb18c1e9")); //Danny Boy
        calendarIds.add(UUID.fromString("452dccfc-975e-11e5-bfa5-c8e0eb18c1e9")); //Emma Win

        //1. Jeg antar at det ligger kun appointments og timeslots som hører til én kalender i én fil
        //2. Laster inn alle kalendere med enn gang uansett hva som ble bedt om i calendarIds


        Calender calender1 = new Calender("Joanna Hef", "48644c7a-975e-11e5-a090-c8e0eb18c1e9");
        calender1.readFromFile();
        Calender calender2 = new Calender("Danny boy", "48cadf26-975e-11e5-b9c2-c8e0eb18c1e9");
        calender2.readFromFile();
        Calender calender3 = new Calender("Emma Win", "452dccfc-975e-11e5-bfa5-c8e0eb18c1e9");
        calender3.readFromFile();


//        findAvailableTime(
//                array <Uuid> calendarIds,
//                Integer duration,
//                Iso8601TimeInteval periodToSearch,
//                Uuid timeSlotType
//        );

    }


}
