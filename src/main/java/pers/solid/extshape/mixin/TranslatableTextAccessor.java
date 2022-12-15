package pers.solid.extshape.mixin;

import net.minecraft.text.StringVisitable;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Consumer;

@Mixin(TranslatableText.class)
public interface TranslatableTextAccessor {
  @Invoker
  void invokeForEachPart(String translation, Consumer<StringVisitable> partsConsumer);
}
