package com.ecspresso.discord.modules;

import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface IModule {
    SlashCommandData getSlashCommandData();
}
