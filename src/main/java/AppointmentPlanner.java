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
        for (Calender calender : calenders) {

            ArrayList<Appointment> appointments = calender.getAppointments();
            appointments.sort(Comparator.comparing(Appointment::getStart));

            ArrayList<Timeslot> timeslots = calender.getTimeslots();
            timeslots.sort(Comparator.comparing(Timeslot::getStart));

            ArrayList<Timeslot> availableTimeslots = new ArrayList<>();
            for (Timeslot timeslot : timeslots) {
                Instant timeslotStart = timeslot.getStart();
                Instant timeslotEnd = timeslot.getEnd();

                System.out.println("Timeslot:" + timeslotStart + " - " + timeslotEnd);

                boolean isTimeslotBeyondSearchPeriod =
                        timeslotStart.compareTo(this.periodStart) < 0 ||
                                timeslotEnd.compareTo(this.periodEnd) > 0;
                //antar at slots ikke kan deles opp
                if (isTimeslotBeyondSearchPeriod) {
                    System.out.println("Timeslot is Beyond searching period");
                    continue;
                }

                if (isTimeslotFree(timeslotStart, timeslotEnd, appointments)) {
                    availableTimeslots.add(timeslot);
                }
            }
            System.out.println("Available timeslots: \n" + availableTimeslots);

        }


    }

    public boolean isTimeslotFree (Instant timeslotStart, Instant timeslotEnd, ArrayList<Appointment> appointments) {
        //TODO: fjerne appointments som er allerede brukt
        boolean isTimeslotBusy = false;
        for (Appointment appointment : appointments) {
            Instant appointmentStart = appointment.getStart();
            Instant appointmentEnd = appointment.getEnd();

            System.out.println("Appointment:" + appointmentStart + " - " + appointmentEnd);

            if (appointmentStart.compareTo(timeslotEnd) >= 0) {
                System.out.println("Ledig: breaker");
                break;
            }

            boolean isAppointmentIntersectTimeslot =
                    appointmentStart.compareTo(timeslotEnd) < 0 &&
                            appointmentEnd.compareTo(timeslotStart) > 0;

            if (isAppointmentIntersectTimeslot) {
                isTimeslotBusy = true;
                System.out.println("Busy: breaker");
                break;
            }

            System.out.println("Ledig så langt...");

        }
        return !isTimeslotBusy;
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
