package com.ecspresso.mau.kronox.scrapper;

import com.ecspresso.mau.kronox.Schedule;
import com.ecspresso.mau.kronox.locale.Room;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.CalendarDateFormat;
import net.fortuna.ical4j.model.component.CalendarComponent;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Scraper {
    private static final String baseUrlString = "https://schema.mau.se/setup/jsp/SchemaICAL.ics?startDatum=";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

    public static Schedule getSchedule(String date) throws IOException, ParserException {
        Schedule schedule = new Schedule();

        StringBuilder roomString = new StringBuilder("&intervallTyp=d&intervallAntal=1&sokMedAND=false&sprak=SV&resurser=");
        for(Room room : Room.values()) {
            roomString.append("l.").append(room.getRoomName()).append("%2C");
        }

        String url = baseUrlString + date + roomString;
        System.out.println(url);

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = new BasicClassicHttpRequest(Method.GET, url);
            BasicHttpClientResponseHandler httpHandler = new BasicHttpClientResponseHandler();
            String calendarString = httpclient.execute(httpPost, httpHandler);

            StringReader sin = new StringReader(calendarString);
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(sin);

            for(CalendarComponent event : calendar.getComponents()) {
                String[] locations = event.getProperties("location").get(0).getValue().split(" ");
                for(String location : locations) {
                    CalendarDateFormat.UTC_DATE_TIME_FORMAT.parse(event.getProperties("dtstart").get(0).getValue());
                    LocalDateTime start = LocalDateTime.parse(event.getProperties("dtstart").get(0).getValue(), formatter);
                    LocalDateTime end = LocalDateTime.parse(event.getProperties("dtend").get(0).getValue(), formatter);
                    schedule.addEvent(location, start, end);
                }
            }
        }

        return schedule;
    }
}
