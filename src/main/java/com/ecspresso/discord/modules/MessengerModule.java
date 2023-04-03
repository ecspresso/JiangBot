package com.ecspresso.discord.modules;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessengerModule extends ListenerAdapter {
    public void sendMessage(JDA jda, long userId, String message) {
        User user = jda.getUserById(userId);
        if (user != null) {
            user.openPrivateChannel().queue(channel -> {
                channel.sendMessage(message).queue();
            });
        }
    }
}
