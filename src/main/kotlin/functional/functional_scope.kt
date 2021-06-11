fun main() {
    outerCls().apply{
        test()
        test2()
    }
}

class outerCls {
    var name = "base"
    var age  = 100
    fun test(){
        innerCls().apply{
            doFunction{
                // outerCls 자원을 접근해서 사용할 수 있다.
                println("${age}")
                println (name)
            }
        }
    }

    fun test2(){
        innerCls().apply{
            doFunction{
                // outerCls 자원을 접근해서 사용할 수 있다.
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