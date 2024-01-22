package croissantnova.sanitydim.item;

import croissantnova.sanitydim.effect.EffectsRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties OPIUM = (new FoodProperties.Builder())
            .nutrition(0)
            .saturationMod(0.0F)
            .effect(() -> new MobEffectInstance(EffectsRegistry.SEDATED.get(), 6000, 0), 1.0F)
            .fast()
            .alwaysEat()
            .build();
}
