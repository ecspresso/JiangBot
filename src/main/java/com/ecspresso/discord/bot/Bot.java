package com.ecspresso.discord.bot;

import com.ecspresso.discord.modules.IModule;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;

public class Bot {
    private final JDABuilder builder;
    private final List<SlashCommandData> slashCommands = new ArrayList<>();

    public Bot(String token) {
        builder = JDABuilder.createDefault(token);
    }

    public void start() {
        JDA jda = builder.build();
        jda.updateCommands().addCommands(slashCommands).queue();
    }

    public <T extends ListenerAdapter & IModule> Bot addModule(T module) {
        builder.addEventListeners(module);
        slashCommands.add(module.getSlashCommandData());
        return this;
    }
}