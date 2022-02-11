# 扩展方块形状模组

If you do not understand Chinese, you may refer to the [document in English](README-en.md).

本模组为许多的原版方块添加了其楼梯、台阶、栅栏、栅栏门、按钮和压力板，以及原版不具有的纵台阶、纵楼梯、横条、纵条。具体内容列表请参考[此文件](BlockList.md)。

模组目前支持 Minecraft Java 版 1.18.1、1.17.1 和 1.16.5，请根据你的游戏版本安装对应的模组（相近版本理论上兼容，模组不会阻止启动，但1.16.5的版本**不兼容 1.16-1.16.1**），并需要 Fabric API。

**请注意：自从模组版本 1.4.0 开始，本模组依赖 ARRP（高级运行时资源包，Advance Runtime Resource Pack）模组才能运行，因此您必须同时安装该模组。如果不安装 ARRP 而安装了扩展方块形状模组，游戏将无法正常运行。**

**请注意：如果将模组由 1.2.3 更新到 1.2.4 以上的版本，请提前拆除所有的 木板墙 和 粘土墙 方块。从 1.2.4 开始，木板墙和粘土墙被移除。**

## 特性

### 方块

本模组添加的方块继承了其基础方块的大多数特性。

所有的羊毛制品（即以羊毛为基础的方块，包括各种颜色的羊毛楼梯、羊毛台阶等）均可以燃烧且可以作为燃料（其燃烧时间与原版完整方块相同）。羊毛制品无论是否占了一整格，均具有阻挡潜声传感器（Sculk Sensor）的功能。

所有的下界岩制品（下界岩1.16之前称为地狱岩）均可无限燃烧，基岩制品在末地可无限燃烧。

下界合金制品的掉落形式能够抵抗火焰、熔岩。

所有的楼梯、台阶、栅栏、墙的硬度、挖掘工具和挖掘时间与其基础方块相当（这是参照了原版的特性），而非与其体积成正比。例如，破坏羊毛台阶的时间与破坏整个羊毛的时间相同，而非前者是后者的一半。

在原版中，大多数压力板和按钮的硬度为其基础方块的1/4到1/3。本模组添加的压力板和按钮的硬度均为基础方块的1/4。（基岩制品在生存模式不可破坏，但是基岩按钮、基岩压力板在失去其依靠的方块时仍会正常掉落）。本模组对按钮进行了扩展，质地较软的按钮（如羊毛按钮、雪按钮）的触发时长为60刻（3秒），质地很硬的按钮（如黑曜石按钮、基岩按钮）的触发时长为5刻（1/4秒）。参照原版代码，所有的按钮、压力板均无碰撞箱。

为避免合成表冲突，部分按钮不可合成。例如铁块只能合成铁锭，不能合成铁制按钮，南瓜只能合成南瓜种子，不能合成南瓜按钮。

另外此模组还添加了“双层平滑石台阶”方块和“石化橡木木板”方块，分别可以合成平滑石台阶和石化橡木台阶（原版的平滑石块也可以合成平滑石台阶）。

### 合成与烧炼 Crafting and smelting

所有的方块均可使用其基础方块参照类似原版合成表合成，部分可以通过切石机合成。具体为：楼梯在工作台可3:2合成（原料:产物，下同），在切石机可1:1合成。台阶和纵台阶在工作台、切石机可1:2合成。

台阶、楼梯、横条可以直接在合成表中旋转形成对应的纵向方块，也可以“转回来”。例如 1 个台阶可以合成 1 个对应的纵台阶，1 个纵台阶也可以直接合成 1 个对应的台阶。

各个形状方块在工作台中的合成关系如下：

- 6×基础方块 → 4×楼梯
- 3×基础方块 → 6×台阶
- 3×台阶 → 6×横条 （台阶水平排列）
- 1×台阶 ↔ 1×纵台阶
- 1×楼梯 ↔ 1×纵楼梯
- 1×横条 ↔ 1×纵条
- 3×纵台阶 → 6×纵条 （纵台阶竖直排列）

部分方块的各个形状的方块在切石机中的合成关系如下：

- 1×基础方块 → 1×楼梯 / 1×纵楼梯 / 2×台阶 / 2×纵台阶 / 4×横条 / 4×纵条
- 1×楼梯 → 3×横条
- 1×台阶 → 2×横条
- 1×纵楼梯 → 3×纵条
- 1×纵台阶 → 2×横条 / 2×纵条

注意：本模组中的物品暂不能通过切石机像原版 Minecraft 那样进行“递归切石”。例如，原版游戏中，一个未磨制的安山岩可以直接切石成磨制的安山岩楼梯或者台阶。模组中的方块暂时不能这样切石。

栅栏和栅栏门合成时的原材料（除了基础方块之外），参照既有合成表。石质栅栏和栅栏门的原材料使用燧石，羊毛的为线，砂岩及其变种的为木棍。

台阶和纵台阶作燃料时，其燃烧时间为基础方块的一半。按钮作燃料时，其燃烧时间为基础方块的1/3。横条、纵条作燃料时，其燃烧时间为基础方块的1/4。

### 创造模式物品栏

在创造模式下，我们设置了多个物品组来以不同方式区分物品。

本模组将新增加的方块添加至原版物品组。此外，还设置了几个物品组，以按照不同的基础方块排列各方块（含原版方块），以便玩家快速获取同一方块的多个形状。

如果安装了1.3以上版本的合理排序（Reasonable Sorting）模组，则这些方块会按照基础方块进行排序。请注意合理排序模组依赖 Cloth Config，但是本模组不依赖。

## 关于ARRP

ARRP（全称 Advanced Runtime Resource Pack，高级运行时资源包）是个第三方库模组，该模组可以用来在游戏运行时在内部创建资源和数据文件，而不是将文件写在模组数据中。

通常，一个方块需要有对应的方块状态定义文件、方块模型以使其具有正确的外观，同时需要有战利品表使其被破坏后能正常掉落，合成配方则定义了方块如何合成。此外方块还有对应的物品，因此还需要创建物品模型。上面说到的“方块状态定义”“方块模型”“物品模型”“战利品表”“配方”等都是 json 文件。在模组内，为每个方块都创建这些 json 非常麻烦，而且这些 json 的内容基本上都是重复的，例如方块物品的模型都是直接继承对应的方块模型，但还是需要在每个文件中定义一次。又如，楼梯的方块状态文件非常复杂，然而不同楼梯的方块状态文件的内容基本上都是一样的，只不过是把名字改了下而已，为每个楼梯都定义一次这么复杂的文件，实在是令人费解。

而使用 ARRP 之后，这些文件都可以直接在运行时生成，而不再是存储在模组包中。这样大大减小了模组文件的大小，同时也可以节省输入流和输出流。当然前提是你必须安装 ARRP 模组。

不过，在借助 ARRP 创建资源包时，虽然节省了大量重复的内容，但其代码语法依旧比较繁琐，这与 ARRP 本身的设计有关。此外，尽管 ARRP 节省了输入和输出，但是在游戏内部，还是会将对象转化为 json 并序列化为字节格式存储在内存中，游戏读取时，需要将这些文件反序列化成 json，然后转变成游戏内的对象。其中，ARRP 的对象和 MC 内的原版对象并不直接互通。因此，本模组似乎并为节省将 json 反序列化并转化为游戏内对象的过程，还增加了一次转化并序列化 json 的过程。

然而，ARRP 模组的设计就是如此，归根结底还是 Mojang 的问题。Minecraft 为了实现资源包与数据包，采用了如此离奇且低效的方式读取游戏资源和数据（包括原版 MC 的数据），我们作为模组开发者只好奉陪。

## 本次更新

### 1.4.0

- 使用 ARRP（高级运行时资源包）取代了传统的资源包和数据包文件。模组文件可以大幅度减小，但是自从该版本，模组将依赖 ARRP 才能运行。请确保同时安装了 ARRP 模组。
- 修复西瓜按钮和南瓜按钮合成配方冲突的问题，因此移除了这两个方块的合成表。
- 修复压力板在按压与未按压时使用相同模型的问题。
- 修复含水方块在附近有方块更新时水不流动的问题。

此前的更新请参考[更新日志](UpdateLog.md)。