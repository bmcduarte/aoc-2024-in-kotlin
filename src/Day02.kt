
fun main() {
    // uses recursion to validate the report is ascending or descending
    fun validateReport(report: List<String>, order: String, tolerance: Int, previousLevels: List<String>): Boolean {
        val mutablePreviousLevels = previousLevels.toMutableList()
        if (report.size < 2) {
            return true
        }

        return if (order == "ascending") {
            if (report[1].toInt() - report[0].toInt() in 1..3) {
                mutablePreviousLevels.add(report[0])
                validateReport(report.subList(1, report.size), order, tolerance, mutablePreviousLevels)
            } else {
                if (tolerance > 0) {
                    val reportWitFirstLevel = listOf(previousLevels, listOf(report[0]), report.subList(2, report.size)).flatten()
                    val reportWithSecondLevel = listOf(previousLevels, listOf(report[1]), report.subList(2, report.size)).flatten()

                    validateReport(reportWitFirstLevel, order, tolerance - 1, listOf()) ||
                    validateReport(reportWithSecondLevel, order, tolerance - 1, listOf())
                } else {
                    false
                }
            }
        } else if (order == "descending") {
            if (report[0].toInt() - report[1].toInt() in 1..3 ) {
                mutablePreviousLevels.add(report[0])
                validateReport(report.subList(1, report.size), order, tolerance, mutablePreviousLevels)
            } else {
                if (tolerance > 0) {
                    val reportWitFirstLevel = listOf(previousLevels, listOf(report[0]), report.subList(2, report.size)).flatten()
                    val reportWithSecondLevel = listOf(previousLevels, listOf(report[1]), report.subList(2, report.size)).flatten()

                    validateReport(reportWitFirstLevel, order, tolerance - 1, listOf()) ||
                    validateReport(reportWithSecondLevel, order, tolerance - 1, listOf())
                } else {
                    false
                }
            }
        } else {
            false
        }
    }

    fun part1(input: List<String>): Int {
        var countSafeReports = 0

        input.map {
            val report = it.split(" ")

            val isSafe = if (report[0].toInt() < report[1].toInt()) {
                // ascending
                validateReport(report, "ascending", 0, listOf())
            } else if (report[0].toInt() > report[1].toInt()) {
                // descending
                validateReport(report, "descending", 0, listOf())
            } else {
                false
            }

            if (isSafe) {
                countSafeReports++
            }
        }

        return countSafeReports
    }

    fun part2(input: List<String>): Int {
        var countSafeReports = 0

        input.map { report ->
            val report = report.split(" ")
            val averageReportValue = report.map { it.toInt() }.average().toInt()

            val isSafe = if (report[0].toInt() < averageReportValue) {
                // ascending
                validateReport(report, "ascending", 1, listOf())
            } else if (report[0].toInt() > averageReportValue) {
                // descending
                validateReport(report, "descending", 1, listOf())
            } else {
                // default, could be either one
                validateReport(report, "descending", 1, listOf())
            }

            if (isSafe) {
                countSafeReports++
            }
        }

        return countSafeReports
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("0 1 3 6 9 10", "1 4 5 5", "15 13 10 7 5 4 2")) == 2)
    //check(part2(listOf("0 1", "1    1", "2 5")) == 2)


    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    //check(part2(testInput) == 31)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
