package util;

import java.time.Instant;

public class DateUtil {

    public static Instant[] parsePeriod (String periodToSearch) {
        String[] periodSplit = periodToSearch.split("/");
        if (periodSplit.length != 2) {
            throw new RuntimeException("Wrong format of periodToSearch.");
        }
        Instant periodStart = Instant.parse(periodSplit[0]);
        Instant periodEnd = Instant.parse(periodSplit[1]);
        return new Instant[]{periodStart, periodEnd};
    }
}
