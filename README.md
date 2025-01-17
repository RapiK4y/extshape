# 扩展方块形状模组

If you do not understand Chinese, you may refer to the [document in English](README-en.md).

本模组为许多的原版方块添加了其楼梯、台阶、栅栏、栅栏门、按钮和压力板，以及原版不具有的竖直台阶、竖直楼梯、横条、纵条。具体内容列表请参考[此文件](BlockList.md)。

模组目前支持 Minecraft Java 版 1.19.3-1.16.4，请根据你的游戏版本安装对应的模组（1.19.2 的模组兼容 1.19.1 和 1.19，1.18.1 的模组兼容 1.18，1.16.5 的模组兼容 1.16.4），并需要 Fabric API 和 BRRP。此外，通常还需要 Mod Menu（非必须）以打开模组配置界面。

**请注意：本模组依赖 BRRP（更好的运行时资源包，Better Runtime Resource Pack）模组才能运行，因此您必须同时安装该模组。BRRP 包含 ARRP 的所有功能，请不要同时安装 BRRP 和 ARRP，以免出现问题。**

本模组的更新记录[点击此处](UpdateLog.md)。欢迎加入QQ群**587928350**或开黑啦频道邀请码**KlFS0n**体验本模组的最新更新。

## 特性

### 方块

本模组为大量方块提供了各种形状的变种。例如，羊毛就有羊毛楼梯、羊毛台阶、羊毛墙、羊毛压力板等。竖直楼梯、竖直台阶、横条、纵条是本模组增加的，这些方块和楼梯、台阶一样均可含水。

所有的楼梯、台阶、栅栏、墙的硬度、挖掘工具和挖掘时间与其基础方块相当（这是参照了原版的特性），而非与其体积成正比。例如，破坏羊毛台阶的时间与破坏整个羊毛的时间相同，而非前者是后者的一半。

在原版中，大多数压力板和按钮的硬度为其基础方块的 1/4 到 1/3。本模组添加的压力板和按钮的硬度均为基础方块的 1/4。本模组对按钮进行了扩展，质地较软的按钮（如羊毛按钮、雪按钮）的触发时长为 60 刻（3 秒），质地很硬的按钮（如黑曜石按钮、基岩按钮）的触发时长为 5 刻（1/4 秒）。参照原版代码，所有的按钮、压力板均无碰撞箱。

另外此模组还添加了“双层平滑石台阶”方块和“石化橡木木板”方块，分别可以合成平滑石台阶和石化橡木台阶（原版的平滑石块也可以合成平滑石台阶）。

本模组添加的方块继承了其基础方块的大多数特性：

- **羊毛**制品（即以羊毛为基础的方块，包括各种颜色的羊毛楼梯、羊毛台阶等）和**木板**制品均可以燃烧，且燃烧时间和传播速度与原版完整方块的燃烧时间和传播速度相同。
- **羊毛、木板、木头和原木**制品可用作燃料，其中台阶和竖直台阶作燃料时，其燃烧时间为基础方块的一半。按钮作燃料时，其燃烧时间为基础方块的约 1/3。横条、纵条作燃料时，其燃烧时间为基础方块的 1/4。
- **羊毛**制品无论是否占了一整格，均具有阻挡幽匿感测体的功能。
    - 注意，在原版中，羊毛地毯可以避免发出振动，但本身不能阻挡振动，而本模组的所有羊毛制品均有阻挡振动的功能。
- **下界岩**制品均可无限燃烧
- **基岩**制品在末地可无限燃烧，且在生存模式不可破坏。但是，基岩按钮、基岩压力板在失去其依靠的方块时仍会正常掉落。
- **末地石、黑曜石和哭泣的黑曜石**制品均可抵御末影龙。
- **下界合金和远古残骸**制品的掉落形式能够抵抗火焰、熔岩。
- **金块、粗金块**的制品可被猪灵捡起。
- 破坏**金块、粗金块和镶金黑石**制品会触怒猪灵。
- **雪**楼梯和雪台阶放置在草方块上时，如果正好压住整个草方块顶部，会让草方块显示为积雪形式，就像雪块和雪一样。
- 小型垂滴叶和大型垂滴叶可以放置在**苔藓和黏土**制品上（仅限建筑方块）。
- **南瓜、西瓜、苔藓、菌光体、下界疣块和诡异疣块**制品可用于堆肥。
- **浮冰**和**幽匿块**制品只能通过有精准采集（丝绸之触）附魔的物品获取。
- **黏土、雪块、西瓜**等方块制成的方块在破坏时会掉落对应的物品，如黏土球、雪球（仅限用锹采集）和西瓜片。台阶、横条、竖直台阶、纵条掉落的数量还会在基础方块掉落的数量的基础上除以 2 或 4，且双层台阶掉落双倍。部分物品的掉落可能会受到时运魔咒影响，且带有精准采集附魔时仍可掉落方块本身。

### 配方

所有的方块均可使用其基础方块参照类似原版合成表合成，石质和金属方块还可以通过切石机合成。具体为：楼梯在工作台可 3:2 合成（原料:产物，下同），在切石机可 1:1 切石。台阶和竖直台阶在工作台、切石机可 1:2 制作。

台阶、楼梯、横条可以直接在合成表中旋转形成对应的纵向方块，也可以“转回来”。例如 1 个台阶可以合成 1 个对应的竖直台阶，1 个竖直台阶也可以直接合成 1 个对应的台阶。

各个形状方块在工作台中的合成关系如下：

- 6×基础方块 → 4×楼梯
- 3×基础方块 → 6×台阶
- 3×台阶 → 6×横条 （台阶水平排列）
- 1×台阶 ↔ 1×竖直台阶
- 1×楼梯 ↔ 1×竖直楼梯
- 1×横条 ↔ 1×纵条
- 3×竖直台阶 → 6×纵条 （竖直台阶竖直排列）

部分方块的各个形状的方块在切石机中的合成关系如下：

- 1×基础方块 → 1×楼梯 / 1×竖直楼梯 / 2×台阶 / 2×竖直台阶 / 4×横条 / 4×纵条
- 1×楼梯 → 3×横条
- 1×台阶 → 2×横条
- 1×竖直楼梯 → 3×纵条
- 1×竖直台阶 → 2×横条 / 2×纵条

自 1.5.1 开始，本模组中的物品可以通过切石机进行“递归切石”。例如，一个未磨制的安山岩可以直接切石成磨制的安山岩竖直台阶。

栅栏和栅栏门合成时的原材料（除了基础方块之外），参照既有合成表。石质栅栏和栅栏门的原材料使用燧石，羊毛的为线，砂岩及其变种的为木棍。

为避免合成表冲突，部分方块不可合成。例如铁块只能合成铁锭，不能合成铁制按钮，南瓜只能合成南瓜种子，不能合成南瓜按钮。此外，羊毛和苔藓的压力板也不能直接合成，而是直接与其对应的地毯进行 1:1 的转换。雪台阶也不能由雪块合成，而是先用 3 个雪块合成 1 个雪（片），再用雪合成雪台阶。使用 `/extshape:check-conflict` 命令可检测合成表冲突。

所有合成配方在获得了任意基础方块之后即可解锁，就像原版的配方一样。具体来说，就是为每个合成配方加入了对应的进度，在获取基础方块或者解锁配方之后，该进度就会被触发并解锁相应的配方。

您可以在模组配置界面设置不回避冲突的合成表。例如，如果关闭了“避免木墙合成配方”，那么木墙可以像其他的墙方块那样合成，但是这将与木活板门的合成配方冲突，因此建议您在安装了能解决合成表冲突的模组的情况下修改这些设置。可以通过 Mod Menu（对于Fabric）或 Forge 的模组列表进入配置界面，亦可使用命令 `/extshape:config`。

### 创造模式物品栏

默认情况下，本模组添加了4个专用的物品组，以按照其基础方块的顺序存储各个形状的方块，相同基础方块的一系列方块放在一起。这样你可以很方便地查找到同一个基础方块的多个不同形状。

你可以在模组配置界面选择将本模组的物品添加到原版物品组中。在 1.19.3，这些物品会按照其基础方块排序，就像原版的那样。在 1.19.2 以及之前的版本，模组中的方块会追加到已有方块的后面，导致物品栏可能显得比较乱，可以通过安装合理排序（Reasonable Sorting）模组来对这些内容进行排序。

你还可以在模组配置界面设置添加哪些形状的方块。例如，如果将“添加至原版物品组”设为开启，并将“添加至原版物品组的形状”设为 `stairs slab`，那么只有本模组的楼梯、台阶会添加至原版的物品组中（不影响原版已有物品）。对于 1.19.3 而言，这些形状是按顺序添加的（但不能重复），例如如果写 `slab stairs` 就会将楼梯排在台阶后面（但是不影响原版已有的楼梯和台阶）。