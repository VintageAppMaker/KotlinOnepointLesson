package others

fun main(args: Array<String>) {

    // 1.
    // 변수(함수를 포함한 어떤 형이던) 대입을 할 경우,
    // 제어문을 사용할 수 있다.
    var s1 = 3
    var a  = when(s1){
        3    -> { s1 * 3}
        else -> { s1    }
    }
    println (a)

    // 2.
    // 엘비스오퍼레이터 ?:
    // 저 문자가 우로 90도 회전시
    // 엘비스프레슬리 모습이라고 한다.
    var s2 : String? = "AAAA"
    println( s2?.length ?: -1)

    // 3.
    // null safe를 위해 사용하는 let
    var s3 : Int? = null
    s3?.let{ println (it * 2) }

    // 4.
    // 명령형(제어문) 처리가 아닌 함수형 처리
    // if 문을 줄이겠다는 것은 마음에 들지만...
    // 적응하기 힘듬.
    var multifier    : (Int) -> Int      = { println("multifier    $it"); it * it}
    var even_checker : (Int) -> Boolean  = { println("even_checker $it"); it % 2 == 0  }
    println ( (0..10).map(multifier).filter{ even_checker(it) } )

}