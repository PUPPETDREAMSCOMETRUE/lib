//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
enum class BookStatus(val displayName: String){
    AVAILABLE("Доступна"),
    BORROWED("Выдана"),
    LOST("Потеряна"),
    RESERVED("Зарезервирована"),
    ARCHIVED("В архиве"),
}
enum class Genre(val displayName: String){
    FICTION("Художественная лит-ра"),
    SCIENTIFIC("научная лит-ра"),
    FANTASY("Фантастика"),
    DETECTIVE("детектив"),
    BIOGRAPHY("биография"),
    CHILDREN("детская лит-ра"),
}
data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val genre: Genre,
    val status: BookStatus
)
var availableCount = 0
var borrowedCount = 0
var lostCount = 0
var reservedCount = 0
var archivedCount = 0
var fictionCount = 0
var scientificCount = 0
var fantasyCount = 0
var detectiveCount = 0
var biographyCount = 0
var childrenCount = 0
fun main() {
    val catalog = arrayOf(
        Book("Преступление и наказание", "Достоевский", 1866, Genre.FICTION, BookStatus.BORROWED),
        Book("Краткая история времени", "Хокинг", 1988, Genre.SCIENTIFIC, BookStatus.AVAILABLE),
        Book("Хоббит", "Толкин", 1937, Genre.FANTASY, BookStatus.RESERVED),
    )
    val format = "%-4s %-28s %-16s %-6s %-14s %-12s"
    println(String.format(format, "№", "Название", "Автор", "Год", "Жанр", "Статус"))
    for (i in 0 until catalog.size) {
        val book = catalog[i]
        println(
            String.format(
                format,
                (i + 1).toString(), // Порядковый номер (начиная с 1)
                book.title,
                book.author,
                book.year.toString(),
                book.genre.name,    // Выводит имя константы (например, FICTION)
                book.status.name    // Выводит имя константы (например, AVAILABLE)
            )
        )
    }
    for (book in catalog) {
        when (book.status) {
            BookStatus.AVAILABLE -> availableCount++
            BookStatus.BORROWED -> borrowedCount++
            BookStatus.LOST -> lostCount++
            BookStatus.RESERVED -> reservedCount++
            BookStatus.ARCHIVED -> archivedCount++
        }
        when (book.genre) {
            Genre.FICTION -> fictionCount++
            Genre.SCIENTIFIC -> scientificCount++
            Genre.FANTASY -> fantasyCount++
            Genre.DETECTIVE -> detectiveCount++
            Genre.BIOGRAPHY -> biographyCount++
            Genre.CHILDREN -> childrenCount++
        }
    }
    println("   Статистика по статусам  ")
    println("Доступно: $availableCount")
    println("Выдано: $borrowedCount")
    println("Потеряно: $lostCount")
    println("Зарезервировано: $reservedCount")
    println("В архиве: $archivedCount")
    println("   Статистика по жанрам  ")
    println("Художественная: $fictionCount")
    println("Научная: $scientificCount")
    println("Фантастика: $fantasyCount")
    println("Детектив: $detectiveCount")
    println("Биография: $biographyCount")
    println("Детская: $childrenCount")
    var oldestBook = catalog[0]
    var newestBook = catalog[0]
    var totalSum = 0
    for (book in catalog) {
        totalSum += book.year
        if (book.year < oldestBook.year) {
            oldestBook = book
        }
        if (book.year > newestBook.year) {
            newestBook = book
        }
    }
    val avgYear = totalSum.toDouble() / catalog.size
    println("   Временные показатели   ")
    println("Самая старая книга: ${oldestBook.title} (${oldestBook.year})")
    println("Самая новая книга: ${newestBook.title} (${newestBook.year})")
    println(String.format("Средний год издания по каталогу: %.1f", avgYear))
}