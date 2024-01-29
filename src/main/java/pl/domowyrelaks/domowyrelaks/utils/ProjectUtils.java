package pl.domowyrelaks.domowyrelaks.utils;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import org.jetbrains.annotations.NotNull;
import pl.domowyrelaks.domowyrelaks.model.Visit;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static pl.domowyrelaks.domowyrelaks.utils.CalendarUtils.getEventDateTime;

public class ProjectUtils {


    @NotNull
    public static DateTime mapDateTimeFromString(String string) {
        return new DateTime(string);
    }
    @NotNull
    public static EventDateTime mapEventDateTimeFromString(String string) {
        DateTime dateTime = mapDateTimeFromString(string);
        return getEventDateTime(dateTime);
    }
    public static String getEarlierDateTime(String inputDateTimeString, int minutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDateTimeString, formatter);
        ZonedDateTime earlierZonedDateTime = zonedDateTime.minusMinutes(15);


        return earlierZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }
}
