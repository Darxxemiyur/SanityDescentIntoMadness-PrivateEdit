package croissantnova.sanitydim.passive;

import croissantnova.sanitydim.capability.ISanity;
import croissantnova.sanitydim.config.ConfigProxy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;

public class Darkness implements IPassiveSanitySource {
    @Override
    public float get(@Nonnull ServerPlayer player, @Nonnull ISanity cap, @Nonnull ResourceLocation dim) {
        if (player.level.getMaxLocalRawBrightness(player.blockPosition()) > ConfigProxy.getDarknessThreshold(dim)) {
            return 0;
        }

        Item offHandItem = player.getOffhandItem().getItem();
        Item mainHandItem = player.getMainHandItem().getItem();
        Block bitem;
        if (null != (bitem = (offHandItem instanceof BlockItem item ? item.getBlock()
                : mainHandItem instanceof BlockItem item ? item.getBlock() : null))) {
            // Check if the block emits light
            if (bitem.defaultBlockState().getLightEmission() > 0) {
                return 0;
            }
        }

        Item[] lightCastItems = { Items.GLOWSTONE_DUST, Items.GLOW_BERRIES, Items.GLOW_INK_SAC,
                Items.LAVA_BUCKET, Items.BROWN_MUSHROOM };
        for (Item item : lightCastItems) {
            if (offHandItem.equals(item) || mainHandItem.equals(item)) {
                return 0;
            }
        }

        return ConfigProxy.getDarkness(dim);
    }
}