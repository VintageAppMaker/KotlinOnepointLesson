package usingexample

// Kotlin에서 DSL을 이용하기 위한
fun main(args: Array<String>) {

    // 복잡한 구조로
    // Data class에 여러개의 값을 대입할 경우.
    // DSL이 편리할 수도 있다.
    val p = DSLMaker {
        name = "Player 1"
        exp {
            job = "Paladin"
            level = 21
        }
    }

    println (p)
}

// DSL을 구현하기 위한 확장함수들
// {}안으로 들어갈때마다
// 확장함수를 모두 만들어주어야 한다.
fun DSLMaker(block: Player.() -> Unit): Player = Player().apply(block)
fun Player.exp(block : Status.()-> Unit){
    status = Status().apply(block)
}

// data class
data class Player(var name:   String? = null,
                  var status: Status?    = null)
data class Status(var job: String? = null, var level: Int? = null)