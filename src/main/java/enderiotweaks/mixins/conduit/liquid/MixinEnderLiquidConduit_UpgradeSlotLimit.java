package enderiotweaks.mixins.conduit.liquid;

import crazypants.enderio.base.conduit.item.ItemFunctionUpgrade;
import crazypants.enderio.conduits.conduit.liquid.EnderLiquidConduit;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;

@Mixin(value = EnderLiquidConduit.class, remap = false)
public class MixinEnderLiquidConduit_UpgradeSlotLimit {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getUpgradeSlotLimit(@Nonnull ItemStack stack) {
        return stack.getItem() instanceof ItemFunctionUpgrade ? 64 : 15;
    }
}
