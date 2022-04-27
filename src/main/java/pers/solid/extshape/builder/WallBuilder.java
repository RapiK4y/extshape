package pers.solid.extshape.builder;

import net.minecraft.block.Block;
import pers.solid.extshape.block.ExtShapeWallBlock;
import pers.solid.extshape.mappings.BlockMappings;
import pers.solid.extshape.tag.ExtShapeBlockTag;

public class WallBuilder extends AbstractBlockBuilder<ExtShapeWallBlock> {
  protected WallBuilder(Block baseBlock) {
    super(baseBlock, builder -> new ExtShapeWallBlock(baseBlock, builder.blockSettings));
    this.defaultTag = ExtShapeBlockTag.WALLS;
    this.mapping = BlockMappings.SHAPE_TO_MAPPING.get(Shape.WALL);
  }

  @Override
  protected String getSuffix() {
    return "_wall";
  }
}
