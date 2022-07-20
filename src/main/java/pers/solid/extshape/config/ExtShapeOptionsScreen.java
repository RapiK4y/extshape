package pers.solid.extshape.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import pers.solid.extshape.ExtShape;
import pers.solid.extshape.ExtShapeItemGroup;
import pers.solid.extshape.ExtShapeRRP;
import pers.solid.mod.fabric.ConfigScreenFabric;

@Environment(EnvType.CLIENT)
public class ExtShapeOptionsScreen extends Screen {

  private final Screen parent;
  private final GameOptions gameOptions;
  public final ExtShapeConfig oldConfig = ExtShapeConfig.CURRENT_CONFIG;
  public final ExtShapeConfig newConfig = ExtShapeConfig.CURRENT_CONFIG.clone();

  public ExtShapeOptionsScreen(Screen parent) {
    super(new TranslatableText("options.extshape.title"));
    this.parent = parent;
    this.gameOptions = MinecraftClient.getInstance().options;
  }

  @Override
  protected void init() {
    // 里面的内容不需要被选中，所以只是drawable。
    addDrawable(new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25));
    ExtShapeConfig config = newConfig;
    this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, button -> onClose()));

    // addToVanillaGroups
    addDrawableChild(CyclingOption.create(
        "options.extshape.addToVanillaGroups",
        new TranslatableText("options.extshape.addToVanillaGroups.tooltip", ItemGroup.BUILDING_BLOCKS.getDisplayName(), ItemGroup.DECORATIONS.getDisplayName(), ItemGroup.REDSTONE.getDisplayName())
            .append("\n\n")
            .append(new TranslatableText("options.extshape.default", ScreenTexts.onOrOff(ExtShapeConfig.DEFAULT_CONFIG.addToVanillaGroups)).formatted(Formatting.GRAY)),
        g -> config.addToVanillaGroups,
        (g, o, value) -> config.addToVanillaGroups = value
    ).createButton(gameOptions, width / 2 - 205, 36, 200));
    // showSpecificGroups
    addDrawableChild(CyclingOption.create(
        "options.extshape.showSpecificGroups",
        new TranslatableText("options.extshape.showSpecificGroups.tooltip")
            .append("\n\n")
            .append(new TranslatableText("options.extshape.default", ScreenTexts.onOrOff(ExtShapeConfig.DEFAULT_CONFIG.showSpecificGroups)).formatted(Formatting.GRAY)),
        g -> config.showSpecificGroups,
        (g, o, value) -> config.showSpecificGroups = value
    ).createButton(gameOptions, width / 2 + 5, 36, 200));
    // registerBlockFamilies
    addDrawableChild(CyclingOption.create(
        "options.extshape.registerBlockFamilies",
        new TranslatableText("options.extshape.registerBlockFamilies.description")
            .append("\n\n")
            .append(new TranslatableText("options.extshape.default", ScreenTexts.onOrOff(ExtShapeConfig.DEFAULT_CONFIG.registerBlockFamilies)).formatted(Formatting.GRAY)),
        g -> config.registerBlockFamilies,
        (g, o, value) -> config.registerBlockFamilies = value
    ).createButton(gameOptions, width / 2 - 205, 61, 200));

    // preventWoodenWallRecipes
    addDrawableChild(CyclingOption.create(
        "options.extshape.preventWoodenWallRecipes",
        new TranslatableText("options.extshape.preventWoodenWallRecipes.tooltip")
            .append("\n\n")
            .append(new TranslatableText("options.extshape.default", ScreenTexts.onOrOff(ExtShapeConfig.DEFAULT_CONFIG.preventWoodenWallRecipes)).formatted(Formatting.GRAY)),
        g -> config.preventWoodenWallRecipes,
        (g, o, value) -> config.preventWoodenWallRecipes = value
    ).createButton(gameOptions, width / 2 + 5, 61, 200));
    // avoidSomeButtonRecipes
    addDrawableChild(CyclingOption.create(
        "options.extshape.avoidSomeButtonRecipes",
        new TranslatableText("options.extshape.avoidSomeButtonRecipes.tooltip")
            .append("\n\n")
            .append(new TranslatableText("options.extshape.default", ScreenTexts.onOrOff(ExtShapeConfig.DEFAULT_CONFIG.avoidSomeButtonRecipes)).formatted(Formatting.GRAY)),
        g -> config.avoidSomeButtonRecipes,
        (g, o, value) -> config.avoidSomeButtonRecipes = value
    ).createButton(gameOptions, width / 2 - 205, 86, 200));
    // specialPressurePlateRecipes
    addDrawableChild(CyclingOption.create(
        "options.extshape.specialSnowSlabCrafting",
        new TranslatableText("options.extshape.specialSnowSlabCrafting.tooltip")
            .append("\n\n")
            .append(new TranslatableText("options.extshape.default", ScreenTexts.onOrOff(ExtShapeConfig.DEFAULT_CONFIG.specialSnowSlabCrafting)).formatted(Formatting.GRAY)),
        g -> config.specialSnowSlabCrafting,
        (g, o, value) -> config.specialSnowSlabCrafting = value
    ).createButton(gameOptions, width / 2 + 5, 86, 200));

    // 运行时资源包设置。
    addDrawableChild(new ButtonWidget(width / 2 - 150, 111, 300, 20, new TranslatableText("options.extshape.rrp.title"), button -> {
      if (client != null) client.setScreen(new ExtShapeRRPScreen(this));
    }, (button, matrices, mouseX, mouseY) -> renderOrderedTooltip(matrices, textRenderer.wrapLines(new TranslatableText("options.extshape.rrp.description"), 200), mouseX, mouseY)));

    {
      final ButtonWidget reasonableSortingButton = new ButtonWidget(width / 2 - 150, 135, 300, 20, new TranslatableText("options.extshape.reasonable-sorting"), button -> {
        if (client != null) {
          try {
            client.setScreen(ConfigScreenFabric.INSTANCE.createScreen(this));
          } catch (LinkageError e) {
            ExtShape.LOGGER.error("Failed to open Reasonable Sorting config screen:", e);
          }
        }
      }, (button, matrices, mouseX, mouseY) -> renderOrderedTooltip(matrices, textRenderer.wrapLines(new TranslatableText("options.extshape.reasonable-sorting.description"), 200), mouseX, mouseY));
      reasonableSortingButton.active = client != null && FabricLoader.getInstance().isModLoaded("reasonable-sorting");
      addDrawableChild(reasonableSortingButton);
    }
  }

  @Override
  public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
    super.render(matrices, mouseX, mouseY, delta);
    drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 16, 0xffffff);
    for (Element child : children()) {
      if (child instanceof CyclingButtonWidget<?> widget && widget.isHovered()) {
        renderOrderedTooltip(matrices, widget.getOrderedTooltip(), mouseX, mouseY);
      }
    }
  }

  public void save() {
    final ExtShapeConfig oldConfig = ExtShapeConfig.CURRENT_CONFIG;
    ExtShapeConfig.CURRENT_CONFIG = newConfig;
    ExtShapeConfig.CURRENT_CONFIG.writeFile(ExtShapeConfig.CONFIG_FILE);

    // 应用物品组。
    if (oldConfig.showSpecificGroups != newConfig.showSpecificGroups) {
      if (newConfig.showSpecificGroups) {
        ExtShape.LOGGER.info("Adding item groups at runtime. This may cause some instability.");
        ExtShapeItemGroup.implementGroups();
      } else {
        ExtShape.LOGGER.info("Removing item groups at runtime. This may cause some instability.");
        ExtShapeItemGroup.removeGroups();
      }
    }
  }

  private boolean suppressedGroupsWarning = false;
  private boolean suppressedDataWarning = false;

  @Override
  public void onClose() {
    assert client != null;
    if (!suppressedGroupsWarning && !newConfig.addToVanillaGroups && !newConfig.showSpecificGroups
        && !(!oldConfig.addToVanillaGroups && !oldConfig.showSpecificGroups)) {
      // 由于两个设置都被关闭，因此需要确认是否不添加到任何物品栏。
      client.setScreen(new ConfirmScreen(
          t -> {
            if (t) {
              // 确定要继续
              suppressedGroupsWarning = true;
              onClose();
            } else {
              // 返回重新修改
              client.setScreen(this);
            }
          },
          new TranslatableText("options.extshape.confirm"),
          new TranslatableText("options.extshape.confirm.noGroups", new TranslatableText("options.extshape.addToVanillaGroups").formatted(Formatting.GRAY), new TranslatableText("options.extshape.showSpecificGroups").formatted(Formatting.GRAY), ScreenTexts.OFF),
          ScreenTexts.YES,
          new TranslatableText("options.extshape.confirm.redo")
      ));
      return;
    }
    if (!suppressedDataWarning && (newConfig.preventWoodenWallRecipes != oldConfig.preventWoodenWallRecipes
        || newConfig.avoidSomeButtonRecipes != oldConfig.avoidSomeButtonRecipes || newConfig.specialSnowSlabCrafting != oldConfig.specialSnowSlabCrafting)) {
      client.setScreen(new ConfirmScreen(
          t -> {
            suppressedDataWarning = true;
            onClose();
            if (t) {
              ExtShapeRRP.STANDARD_PACK.clearResources();
              ExtShapeRRP.generateServerData(ExtShapeRRP.STANDARD_PACK);
              client.inGameHud.getChatHud().addMessage(
                  new TranslatableText("options.dataChanged.finish",
                      new LiteralText("/reload").styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reload"))).formatted(Formatting.GRAY)));
            }
          },
          new TranslatableText("options.extshape.dataChanged"),
          new TranslatableText("options.extshape.dataChanged.details")
      ));
      return;
    }
    save();
    client.setScreen(parent);
  }
}
