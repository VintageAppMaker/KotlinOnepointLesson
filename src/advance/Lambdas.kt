package advance

/**
 * Created by snake on 17. 5. 23.
 */

// 아직 람다식에 익숙하지 않다.
// 코딩을 짧게 해주는 인라인함수 정도로만 이해하고 사용한다.
fun main(args : Array<String> ){
    // 가장 심플한 람다식 표현
    var pfunc = {a: Int, b: Int -> a + b}

    // 그냥함수변수는 TestFunc : (Int, Int) -> Int 형으로 표현가능하다.
    // C/C++ 에서 함수형변수 정의하는 것과 유사하다.
    // 함수처럼 사용함
    println( pfunc(10, 30 ) )

    // 변수로 넘김
    funTest1( pfunc )
    funTest2{ n, n2 -> n + n2}
    funtTest3(
            { n, n2 -> n +n2 },
            100, 10
    )

    // 함수원형의 포인터를 대입시키는 편법
    var funParameter = {a : Int -> AddFunc(a)}
    funtTest4( funParameter )

    // 위의 코딩을 점점 더 단순화
    funtTest4( { a : Int -> AddFunc(a)} )
    funtTest4{ a : Int -> AddFunc(a)}
    funtTest4{ a  -> AddFunc(a) }
}

fun funTest1(func : (Int, Int) -> Int  ){
    func(10, 10).let { 결과값 ->  println("결과값은 $결과값 입니다") }
}

fun funTest2(func : (Int, Int) -> Int  ){
    println ( func(10, 10) )
}

fun funtTest3(func : (Int, Int) -> Int, a : Int, b : Int  ){
    println ( func(a, b) )
}

fun funtTest4(func : (Int) -> Int ){
    println ( func(1) )
}

fun AddFunc(i : Int) : Int {return i+100}