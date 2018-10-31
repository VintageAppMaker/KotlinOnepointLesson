package advance

/**
 * Created by snake on 17. 5. 23.
 */

// 람다를 기반으로 한 코딩 줄이기
// let(), apply(), run()

fun main(args : Array<String> ){

    // let()
    // 결과를 { -> } 안으로 넘긴다. 그 안에서 이미 선언한 변수처럼 사용한다.
    // ->를 삭제하면 기본변수인 it로 인식한다.
    letTest().let { value -> println (value) }
    letTest().let {  println ( it ) }

    // apply()
    // 파스칼의 with문과 유사함. [객체이름.뭐뭐머] 코딩하는 고생이 덜었음.
    var obj = TestClass().apply { sName = "동작구 스눕독"; nAge = 39; }
    obj.aboutMe()

    // run()
    // 함수처럼 결과값을 {}밖으로 리턴한다.
    obj.run { sName = "동작구 에미넴"; aging(); sName}.let { println (it) }
    obj.aboutMe()

}

fun letTest() = 10

class TestClass{
    var sName   : String = ""
    var nAge    : Int    = 49
    fun aboutMe() = println("이름은 \"$sName\"이고 나이는 $nAge 입니다. ")
    fun aging() = nAge++
}