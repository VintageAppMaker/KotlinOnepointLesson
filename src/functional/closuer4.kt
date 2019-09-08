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
            eventHandling(2) {
                callback ->
                println("[2].using eventHandling - start")
                (0..100).forEach{callback(this@UsingCallback)}
                println("[3].using eventHandling - end")
            }
        }
    }
}

class RunningCallback{
    fun eventHandling( num : Int,  callback : ( ( UsingCallback )-> Unit ) -> Unit){
        println("[1].Running eventHandling - start")
        callback{
            obj ->
            if (obj.count > 9 ) return@callback
            obj.count = obj.count + num
            println(">>> calling callback - ${obj.count}")
        }
        println("[4].Running eventHandling - end")
    }

}