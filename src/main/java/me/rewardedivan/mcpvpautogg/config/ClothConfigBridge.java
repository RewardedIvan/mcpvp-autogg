// stolen from fabrishot (kinda) (only because it's using cloth config)
/*
 * MIT License
 *
 * Copyright (c) 2021 Ramid Khan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package me.rewardedivan.mcpvpautogg.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.nbt.NbtByte;
import net.minecraft.text.Text;

public class ClothConfigBridge implements ConfigScreenFactory<Screen> {
    @Override
    public Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Text.translatable("mcpvp-autogg.config.title"))
                .setSavingRunnable(Config::save)
                .setParentScreen(parent);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory category = builder.getOrCreateCategory(Text.translatable("mcpvp-autogg.config.category"));

        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("mcpvp-autogg.config.enabled"), Config.get(Config.ENABLED) == NbtByte.of(true))
                .setDefaultValue(Config.getDefault(Config.ENABLED) == NbtByte.of(true))
                .setSaveConsumer(v -> Config.write(Config.ENABLED, v))
                .build());

        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("mcpvp-autogg.config.chathistory"), Config.get(Config.CHAT_HISTORY) == NbtByte.of(true))
                .setDefaultValue(Config.getDefault(Config.CHAT_HISTORY) == NbtByte.of(true))
                .setSaveConsumer(v -> Config.write(Config.CHAT_HISTORY, v))
                .build());

        category.addEntry(entryBuilder.startStrField(Text.translatable("mcpvp-autogg.config.win"), Config.get(Config.WIN_MSG).asString())
                .setDefaultValue(Config.getDefault(Config.WIN_MSG).asString())
                .setSaveConsumer(v -> Config.write(Config.WIN_MSG, v))
                .build());

        category.addEntry(entryBuilder.startStrField(Text.translatable("mcpvp-autogg.config.lose"), Config.get(Config.LOSE_MSG).asString())
                .setDefaultValue(Config.getDefault(Config.LOSE_MSG).asString())
                .setSaveConsumer(v -> Config.write(Config.LOSE_MSG, v))
                .build());

        category.addEntry(entryBuilder.startStrField(Text.translatable("mcpvp-autogg.config.draw"), Config.get(Config.DRAW_MSG).asString())
                .setDefaultValue(Config.getDefault(Config.DRAW_MSG).asString())
                .setSaveConsumer(v -> Config.write(Config.DRAW_MSG, v))
                .build());

        return builder.build();
    }
}