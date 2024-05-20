interface ArchiveNoteFunctions {

    //Интерфейсы для архивов
    fun createArchiveList(mutableList: MutableList<Archive>) {
        mutableList.add(Archive("Создать архив"))
        mutableList.add(Archive("Завершить программу"))
    }

    fun addElement(header: String, mutableList: MutableList<Archive>) {
        mutableList.removeAt(mutableList.size - 1)
        mutableList.removeLast()
        mutableList.add(Archive(header))
        mutableList[mutableList.size - 1].notesMap["Создать заметку"] = ""
        mutableList[mutableList.size - 1].notesMap["Назад"] = ""
        mutableList.add(Archive("Завершить программу"))
        println("\nАрхив \"$header\" успешно добавлен")
    }

    fun showMenu(mutableList: MutableList<Archive>) {
        println("\nСписок архивов:")
        for (i in 0 until mutableList.size) {
            println("$i. ${mutableList[i].name}")
        }
    }

    //Интерфесы для заметок
    fun addElement(noteHeader: String, noteText: String, mutableMap: MutableMap<String, String>) {
        mutableMap.remove("Назад")
        mutableMap[noteHeader] = noteText
        mutableMap["Назад"] = ""
        println("\nЗаметка \"$noteHeader\" успешно добавлена")
    }

    fun showMenu(mutableMap: MutableMap<String, String>, archiveHeader : String) {
        println("\nСписок заметок архива \"${archiveHeader}\":")
        for ((i, key) in mutableMap.keys.withIndex()) {
            println("$i. $key")
        }
    }

}