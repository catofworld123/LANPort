package net.fabricmc.example.mixin;

import net.fabricmc.example.GuiChangePort;
import net.fabricmc.example.StringBridge;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(GuiShareToLan.class)
public class OpenToLanGuiMixin extends GuiScreen{



    @Inject(method = "initGui", at = @At(value = "FIELD", target = "Lnet/minecraft/src/GuiShareToLan;buttonList:Ljava/util/List;",ordinal = 4,shift = At.Shift.AFTER))
    public void initGui(CallbackInfo ci) {

        if (!MinecraftServer.getIsServer()) {
            this.buttonList.add(new GuiButton(10, this.width / 2 - 100, this.height / 4 + 120, "Set Port"));
        }
    }
    @Inject(method = "actionPerformed", at = @At(value = "TAIL"))
    private void openGui(GuiButton par1, CallbackInfo ci){
        if (par1.id == 10){

                this.mc.displayGuiScreen(new GuiChangePort(this, StringBridge.s));
            }



        }
    }


