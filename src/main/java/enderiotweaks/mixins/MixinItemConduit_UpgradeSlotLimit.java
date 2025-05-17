package enderiotweaks.mixins;

import crazypants.enderio.conduits.conduit.item.ItemConduit;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;

@Mixin(value = ItemConduit.class, remap = false)
public abstract class MixinItemConduit_UpgradeSlotLimit {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getUpgradeSlotLimit(@Nonnull ItemStack stack) {
        return 64;
    }
}
