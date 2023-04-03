package com.ecspresso.discord.modules;

import com.ecspresso.catfact.CatFact;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

public class CatFactModule extends ListenerAdapter implements IModule {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(!event.getName().equals("catfact")) return;

        event.deferReply().queue();
        event.getHook().sendMessage(CatFact.getFact().fact()).queue();
    }

    @Override
    public SlashCommandData getSlashCommandData() {
        return Commands.slash("catfact", "Facts about cats.");
    }
}
