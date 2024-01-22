package croissantnova.sanitydim.passive;

import croissantnova.sanitydim.capability.ISanity;
import croissantnova.sanitydim.config.ConfigProxy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;

public class Darkness implements IPassiveSanitySource
{
    @Override
    public float get(@Nonnull ServerPlayer player, @Nonnull ISanity cap, @Nonnull ResourceLocation dim)
    {
        if (player.level.getMaxLocalRawBrightness(player.blockPosition()) <= ConfigProxy.getDarknessThreshold(dim)) {
            Item offHandItem = player.getOffhandItem().getItem();
            Item mainHandItem = player.getMainHandItem().getItem();

            Block block = Blocks.DIRT;
            block = offHandItem instanceof BlockItem ? ((BlockItem) offHandItem).getBlock() : block;
            block = mainHandItem instanceof BlockItem ? ((BlockItem) mainHandItem).getBlock() : block;

            /*
            Item[] lightCastItems = {   Items.TORCH, Items.SOUL_TORCH, Items.REDSTONE_TORCH,
                                        Items.GLOWSTONE, Items.GLOWSTONE_DUST, Items.GLOW_BERRIES,
                                        Items.GLOW_INK_SAC, Items.JACK_O_LANTERN, Items.SEA_LANTERN,
                                        Items.LANTERN, Items.SOUL_LANTERN, Items.SOUL_CAMPFIRE,
                                        Items.CAMPFIRE, Items.LAVA_BUCKET, Items.SHROOMLIGHT,
                                        Items.END_ROD, Items.BEACON, Items.CANDLE, Items.CYAN_CANDLE,
                                        Items.BLACK_CANDLE, Items.BLUE_CANDLE, Items.BROWN_CANDLE,
                                        Items.GRAY_CANDLE, Items.GREEN_CANDLE, Items.LIGHT_BLUE_CANDLE,
                                        Items.LIGHT_GRAY_CANDLE, Items.LIME_CANDLE, Items.MAGENTA_CANDLE,
                                        Items.ORANGE_CANDLE, Items.PINK_CANDLE, Items.RED_CANDLE,
                                        Items.PURPLE_CANDLE, Items.WHITE_CANDLE, Items.YELLOW_CANDLE,
                                        Items.CRYING_OBSIDIAN, Items.ENCHANTING_TABLE, Items.ENDER_CHEST,
                                        Items.MAGMA_BLOCK, Items.BROWN_MUSHROOM, Items.BREWING_STAND,
                                        Items.DRAGON_EGG};
            */

            if (!block.equals(Blocks.DIRT)) {
                // Check if the block emits light
                if (block.defaultBlockState().getLightEmission() > 0) {
                    return 0;
                }
            }

            Item[] lightCastItems = {   Items.GLOWSTONE_DUST, Items.GLOW_BERRIES, Items.GLOW_INK_SAC,
                    Items.LAVA_BUCKET, Items.BROWN_MUSHROOM};
            for (Item item : lightCastItems) {
                if (offHandItem.equals(item) || mainHandItem.equals(item)) {
                    return 0;
                }
            }

            return ConfigProxy.getDarkness(dim);
        }

        return 0;
    }
}