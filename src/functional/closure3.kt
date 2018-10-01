package functional


fun WorkingStyle() : (String) -> Unit {
    var career: Int = 0;

    // 견고함
    class Propessionalcoding{
        fun print(s : String?){
            println ("전문가모드:"+ s)
        }
    }
    // 단순하지만 복잡한...
    fun Philosophycoding(s : String?){
        val error     : String = "메모리에러입니다"
        val maxsize   : Int    = 17
        if( s == null ) return
        var f : (String) -> Unit = Exit@{
            param -> if(param.length > maxsize ){
            println (error)
            return@Exit
        }
            println ("철학자모드:$s")
        }

        f(s)
    }
    // closure..
    return fun (s : String) : Unit{

        when(career){
            in ( 0 .. 2 ) -> println (s)                    // 스타일 없음
            in ( 3 .. 7)  -> {Propessionalcoding().print(s)} // 전문가 스타일
            in ( 8 .. 21) -> {Philosophycoding(s)}          // ????
        }

        career++

        return;
    };
}

fun main(args: Array<String>) {
    val MyWorkingHistory = WorkingStyle()
    // 22년이 지나감에 따라 업무스타일이...
    for ( i in ( 0.. 22) )
        MyWorkingHistory("${i}년차: Hello World ")
}

