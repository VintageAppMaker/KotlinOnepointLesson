package basic

/**
 * Created by snake on 17. 5. 22.
 */

fun main(args : Array<String>){
    // kotlin에서 함수는 pascal 문법과 유사하다(그렇다지만 프로시져와 함수의 구분은 없다.).
    // fun 함수명(변수명 : 데이터크기, ...) : 리턴값 { return; }
    // 의 형태로 되어있다.
    funByNoParam()
    funByParameter(3, " 숫자입니다")
    println (basic.funByReturn("3을 넘기니"))
    println(basic.funByInline(3, 10))
}

fun funByReturn(s: String): Any? {
    return s + "-를 입력받았습니다."
}

fun funByParameter(i: Int, s: String) {
    println (i.toString() + s)
}

fun funByInline(i: Int, i1: Int) = i * i1

fun funByNoParam() {
    println ("매개변수 없어요")
}
