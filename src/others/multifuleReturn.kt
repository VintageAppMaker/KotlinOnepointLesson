package others

// 멀티타입리턴과 같은 효과로 사용할 수 있다.
fun main(args: Array<String>) {
    // 암시적
    val (s, i) = fn()
    println (s)
    println (i)

    // 명시적 - Any로 받아야 함.
    val (v1 : Any, v2 : Any) = fn2()
    println (v1)
    println (v2)

}

fun fn()   = listOf("I kotlin you", 3)
fun fn2()  = listOf("I kotlin you", 4)

