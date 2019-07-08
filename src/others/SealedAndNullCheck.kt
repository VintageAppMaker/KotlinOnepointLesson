package others

fun main() {

    val lst = listOf<Note>(
            StudyNote("인문학", "배워야함"),
            WorkNote(1, "2019.07.08", "버그분석", "snake"),
            Doodle("그냥낙서임", "마구 그림그리다".toByteArray() )
    )

    // sealed class는 통신프로토콜 또는 RecyclerView의 다양한 데이터에 사용됨
    lst.forEach {

        when(it){
            is StudyNote -> {println ("공부노트: ${it.title}\n${it.content}")}
            is WorkNote  -> {println ("업무노트: ${it.number}:${it.title}\n${it.author}")}
            is Doodle    -> {println ("업무노트: ${it.memo}\n${it.Drawing.toList()} ")}
            else         -> {}
        }

    }

    // ?: 연산자는 널채크를 간단하게 처리할 때 사용함
    var nCheck : String? = null
    println(nCheck ?: "null")
    println(nCheck ?: return)

    println("출력안됨")

}

sealed class Note
data class StudyNote(var title : String, var content : String) : Note()
data class WorkNote(val number : Int, val date : String, var title : String, val author :String) : Note()
data class Doodle  (var memo : String, var Drawing : ByteArray) : Note()
