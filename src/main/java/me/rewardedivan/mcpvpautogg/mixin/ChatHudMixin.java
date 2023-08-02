package me.rewardedivan.mcpvpautogg.mixin;

import me.rewardedivan.mcpvpautogg.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.nbt.NbtByte;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("TAIL"))
    private void addMessage(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) {
        if (Config.get(Config.ENABLED) == NbtByte.of(false)) return;

        if (message.getString().startsWith("Winners: NONE! ")) {
            if (Config.get(Config.DRAW_MSG).asString() != "")
                sendPlayerMsg(Config.get(Config.DRAW_MSG).asString());
        } else if (message.getString().startsWith("Winners: ")) {
            if (message.getString().substring(8).contains(" " + client.player.getDisplayName().getString())) { // use the space to prevent "somewhat matching" names, this will work on 2v2s+
                if (Config.get(Config.WIN_MSG).asString() != "")
                    sendPlayerMsg(Config.get(Config.WIN_MSG).asString());
            } else if (Config.get(Config.LOSE_MSG).asString() != "") {
                sendPlayerMsg(Config.get(Config.LOSE_MSG).asString());
            }
        }
    }

    // stolen from meteor client
    public void sendPlayerMsg(String message) {
        if (Config.get(Config.CHAT_HISTORY) == NbtByte.of(true))
            client.inGameHud.getChatHud().addToMessageHistory(message);

        if (message.startsWith("/")) client.player.networkHandler.sendChatCommand(message.substring(1));
        else client.player.networkHandler.sendChatMessage(message);
    }
}
