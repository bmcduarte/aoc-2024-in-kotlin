import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        var leftList = mutableListOf<Int>()
        var rightList = mutableListOf<Int>()

        input.map {
            val lineArray = it.split("\\s+".toRegex())
            leftList.add(lineArray[0].toInt())
            rightList.add(lineArray[1].toInt())
        }
        leftList = leftList.sorted().toMutableList()
        rightList = rightList.sorted().toMutableList()

        leftList.mapIndexed() { index, value ->
            val delta = abs(value - rightList[index])
            count += delta
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        var leftList = mutableListOf<Int>()
        var rightList = mutableListOf<Int>()

        input.map {
            val lineArray = it.split("\\s+".toRegex())
            leftList.add(lineArray[0].toInt())
            rightList.add(lineArray[1].toInt())
        }

        leftList.map { left ->
            val repetitions = rightList.count { it == left }
            count += repetitions * left
        }

        return count
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("0 1", "1    1", "2 5")) == 4)
    check(part2(listOf("0 1", "1    1", "2 5")) == 2)


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
