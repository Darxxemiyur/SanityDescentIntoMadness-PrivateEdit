package croissantnova.sanitydim.effect;

import croissantnova.sanitydim.SanityMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SanityMod.MODID);

    public static final RegistryObject<MobEffect> SEDATED = MOB_EFFECTS.register("sedated",
            () -> new SedatedEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
