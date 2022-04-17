package others

class InnerCls(s : String){
    var initString = s
    fun display() = "${this.javaClass} : ${initString}"
}

// DI(Dependency Injection)는 클래스 내에 사용하는 내부 클래스를 외부에서 생성하고 주입할 경우 사용하는 패턴이다.
// DI는 내부 클래스의 코드수정으로 인해 외부클래스의 코드수정을 최소화 되도록 하는 것이 목적이다. (약한 결합력이 목표)
// DI를 사용하지 않았을 경우, 내부 클래스 변경 시, 이를 사용한 클래스 모두를 수정해야 하는 [번거로움]이 있다.
// DI를 언급할 때는 주로 프레임웍(Koin, dagger, hilt..)을 사용할 경우이다.
// 개발자들이 알아서 DI 패턴과 비슷한 코딩스타일로 주입하는 경우도 많다.

// 1.일반적으로 가장많이 사용하는 방법(DI 사용하지 않는 방법)
// class내에서 객체를 생성하고 관리
// 대부분 편리하지만, 내부객체의 구조(InnerCls의 생성자)가 바뀐다면
// 사용한만큼 수정을 해야한다.
class OuterCls1{
    var inner : InnerCls
    init {
        inner = InnerCls("1.내부에서 생성")
    }
    fun printMsg(){
        println ( inner.display())
    }
}

// 2.생성자를 통한 외부주입
// 외부주입을 할 경우, 많이 사용된다.
class OuterCls2(inn : InnerCls){

    var inner : InnerCls = inn
    fun printMsg(){
        println ( inner.display())
    }
}

// 3.setter를 통한 외부주입
class OuterCls3{

    lateinit var inner : InnerCls

    fun printMsg(){
        println ( inner.display())
    }
}

// 3.setter를 통한 외부주입
class OuterCls4{

    lateinit var inner : InnerCls

    fun printMsg(){
        println ( inner.display())
    }
}

// 4.생성관리 모듈을 사용
// DI 관련 프레임웍(koin, Hilt, Dagger, kodein...)을 사용하지 않는다면
// 가장 많이 사용하는 코딩스타일
object InnerService {
    var instance : InnerCls?= null
    fun getInstance(s : String) : InnerCls{
        return InnerCls(s)
    }

    fun getSingleton(s : String) : InnerCls{
        return if(instance == null ){
            instance = InnerCls(s)
            instance!!
        } else{
            instance!!
        }
    }
}


fun main() {
    OuterCls1().printMsg()
    OuterCls2(InnerCls("2.생성자로 넘김")).printMsg()

    OuterCls3().apply {
        inner = InnerCls("3.setter로 넘김")
        printMsg()
    }

    // 외부클래스를 관리하여 inject하는 초간단 코드
    val outList = listOf(OuterCls3(), OuterCls4(), OuterCls4(), OuterCls3())

    instanceInject(outList)
    singletonInject(outList)
}

private fun singletonInject(outList : List<Any>) {
    outList.forEach {
        obj ->

        val inn = InnerService.getSingleton("")
        when(obj){
            is OuterCls3 ->{
                obj.apply {
                    inner = inn
                    inner.initString = "inner singlton => out(${obj}) inner(${inn})"
                    printMsg()
                }
            }
            is OuterCls4 ->{
                obj.apply {
                    inner = inn
                    inner.initString = "inner singlton => out(${obj}) inner(${inn})"
                    printMsg()
                }
            }
            else -> {}
        }

    }
}

private fun instanceInject(outList : List<Any>) {
    val outList = listOf(OuterCls3(), OuterCls4(), OuterCls4(), OuterCls3())
    outList.forEach {
            obj ->

        val inn = InnerService.getInstance("")
        when(obj){
            is OuterCls3 ->{
                obj.apply {
                    inner = inn
                    inner.initString = "inner instance => out(${obj}) inner(${inn})"
                    printMsg()
                }
            }
            is OuterCls4 ->{
                obj.apply {
                    inner = inn
                    inner.initString = "inner instance => out(${obj}) inner(${inn})"
                    printMsg()
                }
            }
            else -> {}
        }

    }
}