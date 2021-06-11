fun main() {
    outerCls().apply{
        test()
        test2()
    }
}

class outerCls {
    var name = "base"
    fun test(){
        innerCls().apply{
            doFunction{
                // innerCls의 name을 사용한다.
                println (name)
            }
        }
    }

    fun test2(){
        innerCls().apply{
            doFunction{
                // outerCls의 name을 사용한다.
                println (this@outerCls.name)
            }
        }
    }
}

class innerCls{
    var name : String = "inner"
    fun doFunction (fn: () -> Unit){
        fn()
    }
}