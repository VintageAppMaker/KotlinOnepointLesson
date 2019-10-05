package others

fun main(args: Array<String>) {

    // 순차적인 값을 기준으로 map 작성
    val player_reward = (1..100).associate{
        it to reward(it)
    }

    (1..50).forEach { println ("${it}등 -> ${player_reward[it]}")  }
}

fun reward(n: Int, baseMoney : Int = 1000): Int =
    // if else의 간소화 목적
    when {
        n == 1     -> baseMoney * 10
        n in 2..3  -> baseMoney * 5
        n in 4..10 -> baseMoney * 2
        else       -> baseMoney
    }