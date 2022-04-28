package pers.solid.extshape.block;

import net.devtech.arrp.generator.BRRPWallBlock;
import net.devtech.arrp.json.recipe.JRecipe;
import net.minecraft.block.Block;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.solid.extshape.tag.ExtShapeBlockTags;

public class ExtShapeWallBlock extends BRRPWallBlock implements ExtShapeVariantBlockInterface {
  public ExtShapeWallBlock(@NotNull Block baseBlock, Settings settings) {
    super(baseBlock, settings);
  }

  @Override
  public MutableText getName() {
    return new TranslatableText("block.extshape.?_wall", this.getNamePrefix());
  }

  @Override
  public @Nullable JRecipe getCraftingRecipe() {
    final JRecipe craftingRecipe = super.getCraftingRecipe();
    return craftingRecipe == null ? null : craftingRecipe.group(getRecipeGroup());
  }

  @Override
  public @Nullable JRecipe getStonecuttingRecipe() {
    return simpleStoneCuttingRecipe(1);
  }

  @Override
  public String getRecipeGroup() {
    if (ExtShapeBlockTags.WOOLS.contains(baseBlock)) return "wool_wall";
    if (ExtShapeBlockTags.CONCRETES.contains(baseBlock)) return "concrete_wall";
    if (ExtShapeBlockTags.STAINED_TERRACOTTA.contains(baseBlock)) return "stained_terracotta_wall";
    if (ExtShapeBlockTags.GLAZED_TERRACOTTA.contains(baseBlock)) return "glazed_terracotta_wall";
    if (ExtShapeBlockTags.PLANKS.contains(baseBlock)) return "wooden_wall";
    return "";
  }
}
