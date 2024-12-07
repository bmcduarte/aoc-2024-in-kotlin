
fun main() {

    fun part1(input: List<String>): Int {
        val regex = Regex("mul\\(\\d+,\\d+\\)")
        var multiplicationList = mutableListOf<Int>()
        input.forEach {
            regex.findAll(it).forEach { matchResult ->
                print(matchResult.value)
                val (a, b) = matchResult.value.substring(4, matchResult.value.length - 1).split(",").map { it.toInt() }
                multiplicationList.add(a * b)
                println(" = ${multiplicationList.last()}")
            }
        }

        return multiplicationList.sum()
    }

    fun part2(input: List<String>): Int {
        val cleanedInput = mutableListOf<String>()
        var mulEnabled = true

        input.forEach { line ->
            var currentIndex = 0
            var cleanedLine = ""

            while (currentIndex < line.length) {
                val indexDont = line.indexOf("don't()", currentIndex)
                val indexDo = line.indexOf("do()", currentIndex)

                if (indexDont == -1 && indexDo == -1) {
                    cleanedLine += if (mulEnabled) line.substring(currentIndex) else ""
                    break

                }
                if (indexDont != -1 && (indexDo == -1 || indexDo > indexDont)) {
                    cleanedLine += if (mulEnabled) line.substring(currentIndex, indexDont) else ""
                    currentIndex =  indexDont + 7
                    mulEnabled = false
                } else {
                    cleanedLine += if (mulEnabled) line.substring(currentIndex, indexDo) else ""
                    currentIndex = indexDo + 4
                    mulEnabled = true
                }
            }
            cleanedInput.add(cleanedLine)
        }


        return part1(cleanedInput)
    }



    // Test if implementation meets criteria from the description, like:
    part1(listOf("where(536,162)~'what()what()how(220,399){ mul(5,253);mul(757,101)\$where()@why()who()")).println()
    part2(listOf("where(536,162)~'what()what()how(220,399){ mul(5,253);mul(757,101)\$where()@why()who()&when()from()mul(394,983)why()!&'how()mul(924,201)] mul(44,185)[]what()?\${{}#,mul(116,356):~(from()]<why()mul(792,23)<+)%%'::mul(389,401)where()why()~{^@->when()'mul(557,491)<+select()]'mul(584,228)don't()*?#how()'where()&;@mul(58,115),where()?why()]]+:>mul(263,869)# %")).println()


    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 2)
    part1(testInput).println()
    check(part2(testInput) == 121)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day03")
//    part1(input).println()
    part2(input).println()
}
