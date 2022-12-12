package pl.edu.uwr.studentcrime

data class CrimeData(
    val crimeTitle: String,
    val crimeDescription: String,
    val studentIndex: Int,
    val isSolved: Boolean
)