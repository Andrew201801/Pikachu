# Pikachu 航空信用积分系统

## 分工

- 区块链&&架构：屈福阳
- 后端：孙瑞，余志杰
- 前端：孙瑞，余志杰
- JavaScript：马润薪，孙翼
- PPT制作：孙翼
- 现场展示：屈福阳，余志杰



## 设计思路

皮卡丘积分信誉系统通过为个人和公司设置账户，对彼此之间和内部的交易进行监控并分析，并在其中添加积分和信用属性，并依据信用分数为个人和公司的信用等级进行评测，限制市场中投机套利行为的出现、维持正常良好交易环境。

- 积分：

△积分是和钱不同的一种交易物，个人可以用积分在一些特定的商店里交换到一些商品；
△商店可以用积分到总联盟进行结算，但这需要一定的手续；
△航空公司等一些特定的公司可以向消费者发放积分来吸引消费，总联盟可以给这类公司发放积分。

- 信用：

△信用指数决定了个人或公司的信用层级，系统会通过对个人或公司的行为分析进行信用指数的增减；

△信用指数可以是0-100中任一整数，最低信任级别、中间信任级别、最高信任级别的划分标准为33、66，最低信任级别的两个个人/公司不能进行交易，一个最低信任层级和一个中间信任层级的个人/公司之间无法进行交易；

△三个不同的信用等级对应三条链，分别对应三个不同的API接口。
<img src="https://github.com/Andrew201801/Pikachu/blob/master/src/main/webapp/statics/image/IMG_8001.JPG">

- 对市场中投机套利行为的认定：

△ 如果市场中个人用100积分可以到指定商店换取100元的商品，但由于积分兑换的流程的复杂，投机者会去寻找愿意用100积分直接换取80元商品的个人，再用获得的100积分去换取90甚至100元的商品，最初的80元和最后的商品之间差价即为投机利润。

△航空公司等公司：除总联盟的补充外，积分一直减少

△一般的公司（商店）：除向总联盟发起结算外，积分一直增加

△黄牛：积分有大量且基本等额的上升下降

<img src="https://github.com/Andrew201801/Pikachu/blob/master/src/main/webapp/statics/image/IMG_7998.JPG">
<img src="https://github.com/Andrew201801/Pikachu/blob/master/src/main/webapp/statics/image/IMG_7999.JPG">

