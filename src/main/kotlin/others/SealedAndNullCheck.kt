package others

fun main(args: Array<String>) {

    sealedTest1()
    sealedTest2()

}

private fun sealedTest1() {

    val myDogState : PetState = PetState.eat(10)
    when (myDogState) {
        is PetState.sleep -> {
            println("sleep")
        }
        is PetState.eat -> {
            println("${myDogState.nTime} times eat")
        }
        is PetState.playing -> {
            println("${myDogState.play}  ")
        }
        else -> {}
    }
}

private fun sealedTest2() {
    val lst = listOf<Note>(
        StudyNote("인문학", "배워야함"),
        WorkNote(1, "2019.07.08", "버그분석", "snake"),
        Doodle("그냥낙서임", "마구 그림그리다".toByteArray())
    )

    // sealed class는 통신프로토콜 또는 RecyclerView의 다양한 데이터에 사용됨
    lst.forEachIndexed { index, note ->

        println("[$index]------------------------")
        when (note) {
            is StudyNote -> {
                println("공부노트: ${note.title}\n${note.content}")
            }
            is WorkNote -> {
                println("업무노트: ${note.number}:${note.title}\n${note.author}")
            }
            is Doodle -> {
                println("낙서노트: ${note.memo}\n${note.Drawing.toList()} ")
            }
            else -> {}
        }
        println("---------------------------\n")

    }

    // ?: 연산자는 널채크를 간단하게 처리할 때 사용함
    var nCheck: String? = null
    println(nCheck ?: "null")
    println(nCheck ?: return)

    println("출력안됨")
}

// enum과 비슷하게 사용
sealed class PetState{
    object sleep : PetState()
    data class  eat(val nTime : Int) : PetState()
    data class  playing(val play : String) : PetState()
}

sealed class Note
data class StudyNote(var title : String, var content : String) : Note()
data class WorkNote(val number : Int, val date : String, var title : String, val author :String) : Note()
data class Doodle  (var memo : String, var Drawing : ByteArray) : Note()
