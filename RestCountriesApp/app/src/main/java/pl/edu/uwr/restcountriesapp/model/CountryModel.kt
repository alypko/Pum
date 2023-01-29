package pl.edu.uwr.restcountriesapp.model

class CountryResponse : ArrayList<CountryModel>()


data class CountryModel(
    val name: Name,
    val capital: List<String> ?= null,
    val flags: Flag,
    )

data class Flag(
    val png: String,
    val svg: String)

data class Name(
    val official: String
    )
