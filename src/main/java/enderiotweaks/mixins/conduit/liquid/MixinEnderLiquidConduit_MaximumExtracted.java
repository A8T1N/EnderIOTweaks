package enderiotweaks.mixins.conduit.liquid;

import crazypants.enderio.base.conduit.item.FunctionUpgrade;
import crazypants.enderio.base.conduit.item.ItemFunctionUpgrade;
import crazypants.enderio.conduits.conduit.liquid.EnderLiquidConduit;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;

@Mixin(value = EnderLiquidConduit.class,remap = false)
public abstract class MixinEnderLiquidConduit_MaximumExtracted {

    @Shadow
    @Nonnull
    public abstract ItemStack getFunctionUpgrade(@NotNull EnumFacing dir);

    /**
     * @author
     * @reason
     */
    @Overwrite
    public float getExtractSpeedMultiplier(@Nonnull EnumFacing dir) {
        ItemStack upgradeStack = getFunctionUpgrade(dir);
        if (!upgradeStack.isEmpty()) {
            FunctionUpgrade upgrade = ItemFunctionUpgrade.getFunctionUpgrade(upgradeStack);
            if (upgrade == FunctionUpgrade.EXTRACT_SPEED_UPGRADE){
                return 1 + Math.min(upgradeStack.getCount(),64);
            }
            if (upgrade != null) {
                return upgrade.getFluidSpeedMultiplier(upgradeStack.getCount());
            }
        }

        return 1;

    }
}
