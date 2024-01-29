package pl.domowyrelaks.domowyrelaks.utils;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static pl.domowyrelaks.domowyrelaks.utils.CalendarUtils.getEventDateTime;

public class ProjectUtils {

    public static String getEarlierDateTime(String inputDateTimeString, int minutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDateTimeString, formatter);
        ZonedDateTime earlierZonedDateTime = zonedDateTime.minusMinutes(minutes);


        return earlierZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }
}
