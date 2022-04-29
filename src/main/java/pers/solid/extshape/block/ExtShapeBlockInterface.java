package pers.solid.extshape.block;

import com.google.common.collect.BiMap;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.generator.BlockResourceGenerator;
import net.devtech.arrp.generator.ResourceGeneratorHelper;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JStonecuttingRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.Advancement;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.data.client.model.TextureKey;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import pers.solid.extshape.mixin.AbstractBlockAccessor;

/**
 * 该模组中的绝大多数方块共用的接口。
 */
public interface ExtShapeBlockInterface extends BlockResourceGenerator {
  /**
   * 方块所在的合成配方的组。
   *
   * @return 方块合成配方中的 {@code group} 字段。
   * @see JRecipe#group(String)
   */
  default String getRecipeGroup() {
    return "";
  }

  /**
   * 方块的切石配方。该方法在生成数据时，只有在确认了 {@link #isStoneCut(Block)} 的情况下才会被使用，因此该方法内部无需判断方块是否可以切石。
   *
   * @return 方块的切石配方。用于切石机。
   */
  default @Nullable JRecipe getStonecuttingRecipe() {
    return null;
  }

  /**
   * 通过判断方块的材料是否为石头，来确定方块是否可以被切石。用于生成运行时数据。
   *
   * @param baseBlock 基础方块。
   * @return 方块能否被切石机切石。
   */
  static boolean isStoneCut(Block baseBlock) {
    return baseBlock != null && ((AbstractBlockAccessor) baseBlock).getMaterial() == Material.STONE;
  }

  @Override
  default void writeRecipes(RuntimeResourcePack pack) {
    BlockResourceGenerator.super.writeRecipes(pack);
    final JRecipe stonecuttingRecipe = getStonecuttingRecipe();
    if (stonecuttingRecipe != null && isStoneCut(getBaseBlock())) {
      final Identifier recipeId = getRecipeId().brrp_append("_from_stonecutting");
      pack.addRecipe(recipeId, stonecuttingRecipe);
      // the advancement that corresponds to the advancement
      final Advancement.Task advancement = stonecuttingRecipe.asAdvancement();
      if (advancement != null && !advancement.getCriteria().isEmpty()) {
        stonecuttingRecipe.prepareAdvancement(recipeId);
        pack.addAdvancement(recipeId.brrp_prepend("recipes/"), advancement);
      }
    }
  }

  @Environment(EnvType.CLIENT)
  default JModel simpleModel(String parent) {
    return new JModel(parent).textures(JTextures.ofSides(getTextureId(TextureKey.TOP), getTextureId(TextureKey.SIDE), getTextureId(TextureKey.BOTTOM)));
  }

  /**
   * 考虑到上蜡的方块使用的模型均为未上蜡的方块的模型，故在此做出调整。
   */
  @Environment(EnvType.CLIENT)
  @Override
  default Identifier getBlockModelId() {
    final BiMap<Block, Block> map = HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get();
    if (this instanceof Block && map.containsKey(this)) {
      return ResourceGeneratorHelper.getBlockModelId(map.get(this));
    } else {
      return BlockResourceGenerator.super.getBlockModelId();
    }
  }

  default JStonecuttingRecipe simpleStoneCuttingRecipe(int resultCount) {
    final Block baseBlock = getBaseBlock();
    if (baseBlock == null) {
      return null;
    } else {
      final JStonecuttingRecipe stonecuttingRecipe = new JStonecuttingRecipe(baseBlock, (ItemConvertible) this, resultCount);
      stonecuttingRecipe.addInventoryChangedCriterion("has_base_block", baseBlock);
      return stonecuttingRecipe;
    }
  }
}
