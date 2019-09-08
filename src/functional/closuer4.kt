package functional


// event 핸들러 구현시,
// 핸들러를 구현하는 곳과 실행하는 곳에서
// 코드를 공유해야할 때
fun main(args: Array<String>) {
    UsingCallback().apply {
        fireEvent()
    }
}

class UsingCallback{
    var count = 0
    fun fireEvent(){
        RunningCallback().apply {
            eventHandling(count) {
                callback ->
                println("eventHandling - start")
                (0..100).forEach{callback(this@UsingCallback)}
                println("eventHandling - end")
            }
        }
    }
}

class RunningCallback{
    fun eventHandling( num : Int,  callback : ( ( UsingCallback )-> Unit ) -> Unit){
        callback{
            obj ->
            if (obj.count > 9 ) return@callback
            println("callback - start")
            obj.count++
            println(obj.count)
            println("callback - end")
        }
    }

}