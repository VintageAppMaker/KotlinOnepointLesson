package functional

/**
 * Created by home on 2017-06-01.
 */
fun main(args : Array<String>){
    var obj = ani{
        crying()
        eat()
        nCount++
    }

    println("증가된 숫자는 ${obj.nCount} 입니다.");

    obj.setOnEvent(3, "울어", {
        // 확장함수를 호출한 파라메터
        nCount ->
        println ("${nCount}번 울겠습니다.")
        (1..nCount).map{crying()}
    })

    obj.setOnEvent(2, "먹어", {
        // 확장함수를 호출한 파라메터
        nCount ->
        println ("${nCount}번 먹겠습니다.")
        (1..nCount).map{eat()}
    })

}

// closure 활용
// 넘겨질 객체 Animal.() 와 같이
// 확장함수를 파라메터로 넘긴다
fun ani(extFunc: Animal.() -> Unit) : Animal{
    var ani = Animal()

    // 확장함수가 실행되어야 한다.
    ani.extFunc()
    return ani
}

class Animal{
    open var nCount = 0
    fun crying() = println("$this>> 아흥")
    fun eat()    = println("$this>> 우걱우걱")

    // Java의 불편한 Interfafce보다 편리하게
    // 이벤트 핸들러를 등록할 수 있다.
    open fun setOnEvent(nCount : Int, message : String, extFunc: Animal.(Int) -> Unit) : Animal{
        // 확장함수가 실행되어야 한다. 숫자를 넘겼다.
        when (message){
            "울어" -> {extFunc(nCount)}
            "먹어" -> {var n = if (nCount < 3) 3 else nCount;  extFunc(n)}
            else -> {println("알수없는 메시지: ${ message } 입니다.")}
        }

        return this
    }
}