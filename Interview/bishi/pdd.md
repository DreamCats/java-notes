## PDD笔试题

### 1、[回合制游戏](https://www.nowcoder.com/practice/17a083854661490e85e5bb6c4b26e546?tpId=158&&tqId=34025&rp=1&ru=/ta/exam-pdd&qru=/ta/exam-pdd/question-ranking)

你在玩一个回合制角色扮演的游戏。现在你在准备一个策略，以便在最短的回合内击败敌方角色。在战斗开始时，敌人拥有HP格血量。当血量小于等于0时，敌人死去。一个缺乏经验的玩家可能简单地尝试每个回合都攻击。但是你知道辅助技能的重要性。
在你的每个回合开始时你可以选择以下两个动作之一：聚力或者攻击。
  聚力会提高你下个回合攻击的伤害。
  攻击会对敌人造成一定量的伤害。如果你上个回合使用了聚力，那这次攻击会对敌人造成buffedAttack点伤害。否则，会造成normalAttack点伤害。
给出血量HP和不同攻击的伤害，buffedAttack和normalAttack，返回你能杀死敌人的最小回合数。

**输入描述**：

```html
第一行是一个数字HP
第二行是一个数字normalAttack
第三行是一个数字buffedAttack
1 <= HP,buffedAttack,normalAttack <= 10^9
```

输出描述：

```html
输出一个数字表示最小回合数
```

示例1:

输入

```html
13
3
5
```

输出

```html
5
```

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hp = sc.nextInt();
        int normal = sc.nextInt();
        int buffed = sc.nextInt();
        int res = 0;
        if (buffed <= 2 * normal) {
            // 说明normal攻击，划算
            System.out.println(hp % normal == 0 ? hp / normal : hp / normal + 1);
        } else {
            int mod = hp % buffed;
            if (mod == 0) {
                // 说明为0，直接除就ok
                System.out.println(hp / buffed * 2);
            } else if (mod <= normal) {
                // 说明余量小于normal，最后一次攻击normal，不需要蓄力
                System.out.println(hp / buffed * 2 + 1);
            } else {
                // 还得蓄力才行
                System.out.println(hp / buffed * 2 + 2);
            }
        }
    }
}
```

