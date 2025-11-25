package net.fabricmc.example.mixin;

import net.fabricmc.example.StringBridge;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.HttpUtil;
import net.minecraft.src.IntegratedServerListenThread;
import net.minecraft.src.NetworkListenThread;
import net.minecraft.src.ServerListenThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

@Mixin(IntegratedServerListenThread.class)
public class ServerStartMixin extends NetworkListenThread {



    @Shadow
    private ServerListenThread myServerListenThread;

    public ServerStartMixin(MinecraftServer par1MinecraftServer) throws IOException {
        super(par1MinecraftServer);
    }



    @Inject(method = "func_71755_c", at = @At("HEAD"), cancellable = true)
    private void overrideServerStart(CallbackInfoReturnable<String> cir) throws IOException {
        int port = -1;
        if (!Objects.equals(StringBridge.s, "")) {
             port = Integer.parseInt(StringBridge.s);
        }

        if (this.myServerListenThread == null) {
            int var1 = -1;
            var1 = HttpUtil.func_76181_a();
            if (var1 <= 0) {
                var1 = 25564;
            }
            if (port != -1){
                var1 = port;
            }
            this.myServerListenThread = new ServerListenThread(this, (InetAddress)null, var1);
            this.myServerListenThread.start();
        }

        cir.setReturnValue(String.valueOf(this.myServerListenThread.getMyPort()));
        cir.cancel();
    }
}