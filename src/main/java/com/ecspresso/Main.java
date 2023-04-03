package com.ecspresso;

import com.ecspresso.discord.bot.Bot;
import com.ecspresso.discord.modules.CatFactModule;
import com.ecspresso.discord.modules.MauModule;
import com.ecspresso.mau.kronox.locale.Building;
import com.ecspresso.mau.kronox.scrapper.Scraper;
import com.ecspresso.mau.kronox.Agenda;
import com.ecspresso.mau.kronox.Schedule;
import net.fortuna.ical4j.data.ParserException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try(FileInputStream in = new FileInputStream("config.properties")) {
            properties.load(in);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedReader token = new BufferedReader(new FileReader("bot_token"));
            Bot bot = new Bot(properties.getProperty("discord.token"));
            bot.addModule(new MauModule(Building.getBuildingNames()))
               .addModule(new CatFactModule())
               .start();
        } catch(FileNotFoundException e) {
            System.out.println("kunde inte hitta filen");
            throw new RuntimeException(e);
        }


        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);



        Runnable task = () -> {
            try {
                Schedule schedule = Scraper.getSchedule(LocalDate.now().plusDays(2).toString());
            } catch(IOException | ParserException e) {
                throw new RuntimeException(e);
            }
        };

        Schedule schedule;
        try {
            schedule = Scraper.getSchedule("2023-03-30");
            List<Agenda> agnda = schedule.getAgenda(Building.NIAGARA);
            // for(Agenda a : agnda) {
            //     a.print();
            // }
        } catch(IOException | ParserException e) {
            throw new RuntimeException(e);
        }


    }
}