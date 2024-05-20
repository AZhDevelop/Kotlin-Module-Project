class Activity : ConstValues {

    private val userInput = UserInput()
    private val archiveList = ArchiveList()

    fun start() {
        println(ConstValues.START)
        archiveList.createArchiveList(archiveList.archiveList)
        archiveList.showMenu(archiveList.archiveList)
        while(true) {
            val archiveMenuLength = archiveList.archiveList.size
            val command = userInput.userInput(ConstValues.INT_INPUT, archiveMenuLength) {
                archiveList.showMenu(archiveList.archiveList)
            }
            when(command.toInt()) {
                0 -> {
                    print("Введите название архива: ")
                    val archiveHeader = userInput.userInput(ConstValues.STRING_ARCHIVE_NAME, archiveMenuLength) {
                        archiveList.showMenu(archiveList.archiveList)
                    }
                    archiveList.addElement(archiveHeader, archiveList.archiveList)
                    archiveList.showMenu(archiveList.archiveList)
                }
                archiveList.archiveList.size - 1 -> {
                    println(ConstValues.END)
                    UserInput().scannerClose()
                    break
                }
                else -> {
                    val noteIndex: Int = command.toInt()
                    val notesMap = archiveList.archiveList[noteIndex]
                    notesMap.showMenu(notesMap.notesMap, notesMap.name)
                    while(true) {
                        val noteMenuLength : Int = notesMap.notesMap.size
                        val noteCommand = userInput.userInput(ConstValues.INT_INPUT, noteMenuLength) {
                            notesMap.showMenu(notesMap.notesMap, notesMap.name)
                        }
                        when(noteCommand.toInt()) {
                            0 -> {
                                print("Введите название Заметки: ")
                                val noteHeader = userInput.userInput(ConstValues.STRING_NOTE_NAME, noteMenuLength) {
                                    notesMap.showMenu(notesMap.notesMap, notesMap.name)
                                }
                                print("Введите текст заметки: ")
                                val noteText = userInput.userInput(ConstValues.STRING_NOTE_TEXT, noteMenuLength) {
                                    notesMap.showMenu(notesMap.notesMap, notesMap.name)
                                }
                                notesMap.addElement(noteHeader, noteText, notesMap.notesMap)
                                notesMap.showMenu(notesMap.notesMap, notesMap.name)
                            }
                            notesMap.notesMap.size - 1 -> {
                                archiveList.showMenu(archiveList.archiveList)
                                break
                            }
                            else -> {
                                val noteHeader : String = notesMap.notesMap.entries.elementAt(noteCommand.toInt()).key
                                val noteText : String = notesMap.notesMap.entries.elementAt(noteCommand.toInt()).value
                                println("\nТекст заметки \"$noteHeader\":")
                                println("\"$noteText\"")
                                println("\n0. Назад")
                                while(true){
                                    val finalCommand = userInput.userInput(ConstValues.INT_INPUT, 1) {
                                        println("\n0. Назад")
                                    }
                                    when(finalCommand.toInt()) {
                                        0 -> {
                                            notesMap.showMenu(notesMap.notesMap, notesMap.name)
                                            break
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}