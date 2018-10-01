package basic

/**
 * Created by park on 2017-05-21.
 */

fun main(args : Array<String>){

    // 숫자형 DataType 크기 순으로 선언
    // 정확한 크기를 알 필요는 거의 없음.
    // 대부분 왠만한 수는 Int로 해결되니까..
    var doubleValue : Double = 10.1111
    var floatValue  : Float  = 10.1f
    var longValue   : Long   = 10
    var intValue    : Int    = 10
    var shortValue  : Short  = 10
    var byteValue   : Byte   = 10

    // 출력해보기
    println (doubleValue)
    println (floatValue)
    println (intValue)

    // 크기변환 후, 대입 : 캐스팅
    // to대입할크기() 메소드를 사용한다.
    // ** as로 형변환은 기본형에서는 안된다. **
    doubleValue   = intValue.toDouble()
    intValue = doubleValue.toInt()

}