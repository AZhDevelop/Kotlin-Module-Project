import java.util.Scanner

class UserInput {

    private val scanner = Scanner(System.`in`)
    private lateinit var userInput: String
    //Флаги для проверки ввода с клавиатуры
    //INT_INPUT = "Ввод пункта меню"
    //STRING_ARCHIVE_NAME = "Ввод названия архива"
    //STRING_NOTE_NAME = "Ввод названия заметки"
    //STRING_NOTE_TEXT = "Ввод текста заметки"

    fun userInput(flag: String, menuLength: Int, showMenu: () -> Unit) : String {
        when(flag) {
            ConstValues.STRING_ARCHIVE_NAME -> {
                checkText("!!! Неправильный ввод - Название архива не может быть пустым !!!" +
                        "\nВведите название архива заново: ")
            }
            ConstValues.STRING_NOTE_NAME -> {
                checkText("!!! Неправильный ввод - Название заметки не может быть пустым !!!" +
                        "\nВведите название заметки заново: ")
            }
            ConstValues.STRING_NOTE_TEXT -> {
                checkText("!!! Неправильный ввод - Текст заметки не может быть пустым !!!" +
                        "\nВведите текст заметки заново: ")
            }
            ConstValues.INT_INPUT -> {
                checkCommand(menuLength, showMenu)
            }
        }
        return userInput
    }

    private fun String.checkInput() : Boolean {
        try {
            this.toInt()
            return true
        } catch (nfe: NumberFormatException) {
            if (this.isEmpty()) {
                println("!!! Неправильный ввод - введена пустая строка !!!")
                return false
            } else {
                println("!!! Неправильный ввод - нужно ввести цифру !!!")
                return false
            }
        }
    }

    private fun checkText(textError : String) : String {
        while(true) {
            userInput = scanner.nextLine()
            if (userInput.isEmpty()) {
                print(textError)
            } else {
                return userInput
            }
        }
    }

    private fun checkCommand(menuLength: Int, showMenu: () -> Unit) : String {
        while(true) {
            while(true) {
                print("\nВведите команду: ")
                userInput = scanner.nextLine()
                if (!userInput.checkInput()) {
                    showMenu()
                    break
                } else if (userInput.toInt() !in 0 until menuLength) {
                    println("!!! Неправильный ввод - такого пункта меню нет !!!")
                    showMenu()
                    break
                }
                return userInput
            }
        }
    }

    fun scannerClose() {
        scanner.close()
    }

}