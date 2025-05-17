package enderiotweaks.mixins.conduit.item;

import crazypants.enderio.base.conduit.item.FunctionUpgrade;
import crazypants.enderio.base.conduit.item.ItemFunctionUpgrade;
import crazypants.enderio.conduits.conduit.item.ItemConduit;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import java.util.EnumMap;


@Mixin(value = ItemConduit.class, remap = false)
public abstract class MixinItemConduit_MaximumExtracted {

    @Shadow
    @Final
    @Nonnull
    protected EnumMap<EnumFacing, ItemStack> functionUpgrades;

    @Shadow
    @Nonnull
    public abstract ItemStack getFunctionUpgrade(@NotNull EnumFacing dir);

    /**
     * スピードアップグレードの効果を64個まで拡張
     *
     * @author
     */
    @Overwrite
    public int getMaximumExtracted(@Nonnull EnumFacing dir) {

//        特定方向の導管のアップグレードスロットのItemStackを取得
        ItemStack stack = this.getFunctionUpgrade(dir);

//        空の場合 デフォルトの4を返す
        if (stack.isEmpty()) {
            return FunctionUpgrade.BASE_MAX_EXTRACTED; // 4
        }

//        取得したItemStackに対応するFunctionUpgradeを取得
        FunctionUpgrade functionUpgrade = ItemFunctionUpgrade.getFunctionUpgrade(stack);

//        EXTRACT_SPEED_UPGRADEの場合
//        アップグレードの数（スタック数）に応じて 抽出量を増やす (最大 64)
//        元の戻り値では FunctionUpgradeクラスからアップグレードの最大値(15)を取得していたが
//        参照・経由しないことで 上限の突破(15 → 64)が可能に
//        return functionUpgrade.getMaximumExtracted(stack.getCount());
//            ↓
//        public int getMaximumExtracted(int stackSize) {
//                                                    ↓ 制限(15)されていた
//            return 4 + Math.min(stackSize, this.getMaxStackSize()) * 4;
//        }

        if (functionUpgrade == FunctionUpgrade.EXTRACT_SPEED_UPGRADE) {
            return FunctionUpgrade.BASE_MAX_EXTRACTED + Math.min(stack.getCount(), 64) * 4;
        }

        //その他の処理 (WIP)
        return functionUpgrade.getMaximumExtracted(stack.getCount());
    }

}
