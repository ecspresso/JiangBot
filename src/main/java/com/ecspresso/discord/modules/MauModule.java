package com.ecspresso.discord.modules;

import com.ecspresso.mau.kronox.locale.Room;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MauModule extends ListenerAdapter implements IModule {
    private final List<String> buildingNames;
    private final SlashCommandData slashCommandData;

    public MauModule(List<String> buildingNames) {
        this.buildingNames = buildingNames;
        slashCommandData = Commands.slash("boka", "Boka en tid hos Mau");
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(!event.getName().equals("boka")) return;

        Collection<ItemComponent> buttons = new ArrayList<>();
        for(String name: buildingNames) {
            buttons.add(Button.primary(name, name));
        }

        event.reply("Välj en byggnad.").addActionRow(buttons).queue();
    }

    public SlashCommandData getSlashCommandData() {
        return Commands.slash("boka", "Boka en tid hos Mau");
                // .addOption(OptionType.STRING, "byggnad", "Datum för bokning", true, true);
    }

    // @Override // Används inte?
    // public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {
    //     if (event.getName().equals("boka") && event.getFocusedOption().getName().equals("byggnad")) {
    //         List<Command.Choice> options = Stream.of(buildingNames.toArray(new String[0]))
    //                                              .filter(word -> word.startsWith(event.getFocusedOption().getValue()))
    //                                              .map(word -> new Command.Choice(word, word))
    //                                              .toList();
    //         event.replyChoices(options).queue();
    //     }
    // }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        TextChannel channel = event.getChannel().asTextChannel();
        List<Room> rooms = Room.filter(event.getComponentId());


    }
}
