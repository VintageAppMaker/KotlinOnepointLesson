import java.time.LocalDate

// 0. 사용법
// inline fun<reified T> 함수명: T

// 1.
// 대입받는 변수의 데이터 형에 따라
// 결과값을 선택가능하다.
inline fun<reified T> getToDay(): T? {
    val nowDay: LocalDate = LocalDate.now()
    return when (T::class) {
        String::class -> "now is : $nowDay" as T
        Int::class -> nowDay.dayOfMonth as T
        else -> null as T
    }
}

// 2. 제너릭을 사용한 변수를 넘길 경우,
// 변수형::class와 같이 비교할 수 없다.
// 이 때 reified 사용함
inline fun <reified T> doAction(value : T, calc : (T) -> T) : T?{
    //when (T::class) { <== 컴파일시 에러임.
    var ptype = T::class
    when (ptype){
       Int::class -> {
           return calc(value)
       }
    }

    return null
}

fun main() {
    var rst  : Int?    = getToDay()
    var rst2 : String? = getToDay()
    var rst3 : Long?   = getToDay()

    println("rst = ${rst}, rst2 = ${rst2}, rst3= ${rst3} ")

    val rst4 = doAction(11,  { n ->
        n * 3
    })

    val rst5 = doAction(11.0,  { n ->
        n * 3
    })

    println("rst4 = ${rst4}, rst5 = ${rst5}")
}