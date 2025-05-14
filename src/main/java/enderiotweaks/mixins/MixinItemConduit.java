package enderiotweaks.mixins;

import crazypants.enderio.conduits.conduit.item.ItemConduit;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;

@Mixin(value = ItemConduit.class, remap = false)
public abstract class MixinItemConduit {

    /**
     * アップグレードスロットの上限を強制的に64にします。
     * @reason カスタムチューニングのため
     */
    @Overwrite
    public int getUpgradeSlotLimit(@Nonnull ItemStack stack) {
        return 64;
    }
}
