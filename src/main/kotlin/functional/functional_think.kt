package functional

fun main(args: Array<String>) {

    // 심각하게 중요한 내용인데, 심각하게 무시하더라...
    val (knowledge, doing) = listOf("경험에서 나와야 한다", "지금 이 순간에 하고있는 것")
    println("아는 것과 하는 것은 ${ if(knowledge == doing) "같다" else "다르다" }")

    // 경험상 3번에 1번은 안되더라..
    val failTiming = 3
    val (success1, success2, success3 ) = (0..10).map { it }.filter { it % failTiming != 0 }
    println ("경험상 10 번중 $success1 , $success2 , $success3 순서로 ..$failTiming 번 주기로 실패를 경험한다")

    // 아는 것이 없다면..? 할까?
    val OnlyMouth = { knowledge : String ->
        val MyKnowledge ="내가아는것만"
        listOf(knowledge, knowledge==MyKnowledge)
    }
    val (MyKnowledge, CanDoIt) = OnlyMouth("그건 모르는데?")
    println("내가아는것이 [${MyKnowledge}] 이면 할 수있는 지는 [${CanDoIt}] 이다.")
}

