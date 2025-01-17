package pers.solid.extshape.builder;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import pers.solid.extshape.block.ExtShapeVerticalStairsBlock;
import pers.solid.extshape.block.VerticalStairsBlock;
import pers.solid.extshape.tag.ExtShapeTags;

public class VerticalStairsBuilder extends AbstractBlockBuilder<VerticalStairsBlock> {
  public VerticalStairsBuilder(Block baseBlock) {
    super(baseBlock, builder -> new ExtShapeVerticalStairsBlock(baseBlock, builder.blockSettings));
    this.defaultTagToAdd = ExtShapeTags.VERTICAL_STAIRS;
    this.shape = BlockShape.VERTICAL_STAIRS;
    itemSettings.group(ItemGroup.BUILDING_BLOCKS);
  }

  @Override
  protected String getSuffix() {
    return "_vertical_stairs";
  }
}
