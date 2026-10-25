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
class Menu(val catalog: Array<Book>) {
    fun startMenu() {
        var isRunning = true
        while (isRunning) {
            when (readlnOrNull()) {
                "1" -> Catalog()
                "2" -> Statistics()
                "3" -> Filters()
                "4" -> Actions()
                "0" -> { println("Bye.")
                isRunning = false }
                else -> println("Unknown command.")
            }
        }
    }
    private fun Catalog() {
        val format = "%-4s %-28s %-16s %-6s %-14s %-12s"
        println(String.format(format, "№", "Название", "Автор", "Год", "Жанр", "Статус"))
        for (i in 0 until catalog.size) {
            val book = catalog[i]
            println(
                String.format(
                    format,
                    (i + 1).toString(),
                    book.title,
                    book.author,
                    book.year.toString(),
                    book.genre.name,
                    book.status.name
                )
            )
        }
    }
    private fun Statistics() {
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
    private fun Filters() {
            println("\n--- Выберите жанр из списка ---")
        val genres = Genre.values()
        for (i in genres.indices) {
            println("${i + 1}. ${genres[i].displayName} (${genres[i].name})")
        }
        print("Введите номер жанра: ")
        val input = readlnOrNull()?.toIntOrNull()
        if (input == null || input !in 1..genres.size) {
            println("Такой команды нет.")
            return
        }
        val selectedGenre = genres[input - 1]
        println("\nКниги в этом жанре:")
        var found = false
        for (book in catalog) {
            if (book.genre == selectedGenre) {
                println(" - ${book.title} (Автор: ${book.author}, ${book.year})")
                found = true
            }
        }
        if (!found) {
            println("В каталоге нет книг этого жанра.")
        }
    }
    private fun Actions() {
        println("\n====== Что вы хотите сделать? ======")
        println("1. Показать весь каталог книг")
        println("2. Выбрать книгу")
        println("3. Вернуть книгу")
        println("4. Сообщить о потере книги")
        println("5. Зарезервировать книгу")
        println("==========================")
        print("Выберите пункт: ")
        when (readlnOrNull()) {
            "1" -> Catalog()
            "2" -> handleAction(newStatus = BookStatus.BORROWED)
            "3" -> handleAction(newStatus = BookStatus.AVAILABLE)
            "4" -> handleAction(newStatus = BookStatus.LOST)
            "5" -> handleAction(newStatus = BookStatus.RESERVED)
        }

    }
    private fun handleAction(newStatus: BookStatus) {
        print("Введите номер книги:")
        val input = readlnOrNull()
        val bookNumber = input?.toIntOrNull()
        if (bookNumber == null || bookNumber !in 1..catalog.size) {
            println("Неверный номер книги.")
            return
        }
        val index = bookNumber - 1
        val currentBook = catalog[index]
        if (newStatus == BookStatus.BORROWED && currentBook.status != BookStatus.AVAILABLE) {
            println("Книга уже кем-то взята.")
            return
        }
        catalog[index] = currentBook.copy(status = newStatus)
        when (newStatus) {
            BookStatus.BORROWED -> println("Вы взяли книгу '${currentBook.title}'.")
            BookStatus.AVAILABLE -> println("Книга '${currentBook.title}' возвращена в библиотеку.")
            BookStatus.LOST -> println("Зарегистрирована потеря книги '${currentBook.title}'.")
            BookStatus.RESERVED -> println("Книга '${currentBook.title}' зарезервирована.")
            BookStatus.ARCHIVED -> println("Книга '${currentBook.title}' отправлена в архив.")
        }
    }
    private fun printReport() {
        val bookCount = catalog.size
        val statusCount = catalog.groupBy { it.status }.mapValues { it.value.size }
        val genreCount = catalog.groupBy { it.genre }.mapValues { it.value.size }
        println("\n=== ОТЧЁТ ПО КАТАЛОГУ ===")
        println("Всего книг: $bookCount")
        println("\nПо статусам:")
        for(status in BookStatus.values()) {
            val count = statusCount[status] ?: 0
            val interest = (count.toDouble() / bookCount * 100).toInt()
            println("$status:  $count ($interest%)")
        }
    }
}

fun main() {
    val catalog = arrayOf(
        Book("Преступление и наказание", "Достоевский", 1866, Genre.FICTION, BookStatus.BORROWED),
        Book("Краткая история времени", "Хокинг", 1988, Genre.SCIENTIFIC, BookStatus.AVAILABLE),
        Book("Хоббит", "Толкин", 1937, Genre.FANTASY, BookStatus.RESERVED),
        Book("Больше не человек", "Осаму Дадзай", 1948, Genre.BIOGRAPHY, BookStatus.RESERVED),
        Book("Луна над горой", "Ацуси Накадзима", 1942, Genre.FICTION, BookStatus.ARCHIVED),
        Book("Безупречный", "Сакуноскэ Ода", 1941, Genre.FICTION, BookStatus.AVAILABLE),
        Book("Исповедь „неполноценного“ человека", "Осаму Дадзай", 1948, Genre.SCIENTIFIC, BookStatus.RESERVED),
        Book("Ад одиночества", "Ранпо Эдогава", 1956, Genre.DETECTIVE, BookStatus.BORROWED),
        Book("Порочный круг", "Анго Сакагути", 1946, Genre.CHILDREN, BookStatus.LOST),
        Book("Поэма о человеке", "Тюя Накахара", 1947, Genre.FICTION, BookStatus.AVAILABLE),
    )
    println("\n====== ГЛАВНОЕ МЕНЮ ======")
    println("1. Показать весь каталог книг")
    println("2. Показать статистику библиотеки")
    println("3. Фильтрация книг")
    println("4. Действия с книгами")
    println("0. Выход")
    println("==========================")
    println("Введите нужный вам пункт: ")
    val menu = Menu(catalog)
    menu.startMenu()
}