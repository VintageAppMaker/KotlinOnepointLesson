fun main() {
    outerCls().apply{
        doTest()
    }
}

class outerCls {
    var name = "base"
    var age  = 100 / 2 + 2
    fun outerFunc() = println ("outer Func")
    fun doTest(){
        innerCls().apply{
            // 1. 넘겨지는 람다(doFunction)의 스코프는 outerCls
            doFunction{
                // innerCls의 name을 사용한다.
                println (age)
                outerFunc()
            }

            // 2. 넘겨지는 람다(doFunction)의 스코프는 outerCls 이지만
            // doFunction을 호출하는 영역은 innerCls이다.
            // 그래서 변수명이 innerCls에서 같은 것이 존재한다면
            // innerCls의 변수를 기본적으로 사용하게 된다.
            doFunction{
                // innerCls name을 사용한다.
                println (name)
                // outerCls의 name을 사용한다.
                println (this@outerCls.name)

                // innerCls의 innerFunc()
                innerFunc()
            }

            // 3. 람다(doFunction)에서 innerCls의 자원을 사용하고자 한다면
            // innerCls에서 파라메터로 자신을 넘겨주면 된다.
            doFunction2{
                inner ->
                inner.innerFunc()
            }

        }
    }

}

class innerCls{
    var name : String = "inner"
    fun innerFunc() = println("innerCls Func")
    fun doFunction (fn: () -> Unit){
        fn()
    }

    fun doFunction2 (fn: ( c: innerCls ) -> Unit){
        fn(this)
    }
}