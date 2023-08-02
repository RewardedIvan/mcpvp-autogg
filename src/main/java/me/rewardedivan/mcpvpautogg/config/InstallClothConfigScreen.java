// stolen from fabrishot (only because it's using cloth config)
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

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class InstallClothConfigScreen extends Screen {
    private static final Text INSTALL_CLOTH_CONFIG = Text.of("You must install Cloth Config");
    private final Screen parent;

    public InstallClothConfigScreen(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }

    @Override
    protected void init() {
        addDrawableChild(ButtonWidget.builder(ScreenTexts.OK, buttonWidget -> client.setScreen(parent))
                .position(width / 2 - 100, height - 52)
                .size(200, 20)
                .build());
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        renderBackground(drawContext);

        int textWidth = client.textRenderer.getWidth(INSTALL_CLOTH_CONFIG);
        drawContext.drawTextWithShadow(client.textRenderer, INSTALL_CLOTH_CONFIG, (width - textWidth) / 2, height / 3, 0xFF0000);

        super.render(drawContext, mouseX, mouseY, delta);
    }
}
