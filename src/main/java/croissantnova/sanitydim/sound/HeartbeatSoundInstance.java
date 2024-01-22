package croissantnova.sanitydim.sound;

import croissantnova.sanitydim.config.ConfigProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;

public class HeartbeatSoundInstance extends AbstractTickableSoundInstance
{
    public float factor = 0f;

    public HeartbeatSoundInstance()
    {
        super(SoundRegistry.HEARTBEAT.get(), SoundSource.AMBIENT);
        volume = 0;
        delay = 0;
        looping = true;

    }

    @Override
    public void tick()
    {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null)
            volume = factor * ConfigProxy.getInsanityVolume(mc.player.level.dimension().location());
        else
            volume = 0;
    }

    public void setPos(Vec3 pos)
    {
        x = pos.x;
        y = pos.y;
        z = pos.z;
    }

    public boolean canStartSilent()
    {
        return true;
    }

    public void doStop()
    {
        stop();
    }
}