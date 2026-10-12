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
    FICTION("Доступна"),
    SCIENTIFIC("Выдана"),
    FANTASY("Потеряна"),
    DETECTIVE("Зарезервирована"),
    BIOGRAPHY("В архиве"),
    CHILDREN("В архиве"),
}
fun main() {

}