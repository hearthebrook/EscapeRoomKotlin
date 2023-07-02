import java.util.*

fun main(args: Array<String>) {
    // CREATE ROOM
    fun createBedRoom() : Room {
        var newRoom: Room = Room()
        newRoom.addItem(Bed())
        newRoom.addItem(BookShelf())
        newRoom.addItem(Chest())
        newRoom.addItem(Picture())
        newRoom.addItem(Chest())
        return newRoom
    }

    var bedroom:Room = createBedRoom()
    var choiceMenu: Choices = Choices(bedroom)
    choiceMenu.makeChoice()
}



class Choices(private val location: Room) {
    fun displayChoices(){
        println("1 - View Room Objects")
        println("2 - Search around Object")
        println("3 - View found items")
        println("4 - Use found item")
        println("5 - End the game")
        println()
    }



    fun makeChoice(){
        var choice: Int
        do {
            displayChoices()
            choice = readLine()?.toIntOrNull() ?:0

            when (choice) {
                1 -> {
                    // code for choice 1
                    location.viewRoom()
                }
                2 -> {
                    // code for choice 2
                    location.viewRoom()
                    println("Which object would you like to search around?")
                }
                3 -> {
                    // code for choice 3
                }
                4 -> {
                    // code for choice 4
                }
                5 -> {
                    // code to leave the game
                }
                else -> {
                    println("Invalid choice. Please enter a valid option.")
                }
            }
        } while (choice != 5)


    }

}




open class Item {
    protected var name: String? = null
    protected var secret: String? = null

    override fun toString(): String {
        return "${javaClass.simpleName}"
    }

    fun name(): String? {
        return name
    }

    fun secret(): String? {
        return secret
    }
}

class Key(Secret: String?): Item() {
    init {
        name = "key"
        secret = Secret
    }
}

class Code(Secret: String?): Item() {
    init {
        name = "code"
        secret = Secret
    }
}

open class lock{
    private var isLocked: Boolean = true
    private var password: String? = null

    fun unlock(code:String){
        if (code == password){
            isLocked = false
        }
    }

    fun isLocked(): Boolean {
        return isLocked
    }

    fun setPassword(newPassword: String) {
        password = newPassword
    }
}

open class RoomObject {

    // property (data member)
    private var x: Int? = null
    private var y: Int? = null
    private var length: Int? = null
    private var height: Int? = null
    private var hiddenItem: Item? = null

    // member function
    fun printSize() {
        println("Length: $length")
        println("Height: $height")
    }

    fun printLocation() {
        println("x: $x")
        println("y: $y")
    }

    fun placeObj(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    override fun toString(): String {
        return "${javaClass.simpleName}"
    }

    fun setHiddenItem(item: Item) {
        hiddenItem = item
    }

    fun search() {
        println("You have found a ${hiddenItem?.name()}")
        println("This is the secret: ${hiddenItem?.secret()}")
    }
}

class Bed: RoomObject() {
    private var length: Int = 5
    private var height: Int = 10
}

class Chest: RoomObject() {
    private var length: Int = 2
    private var height: Int = 5
    private var lock: lock = lock()

    private var lockString = ""

    override fun toString(): String {
        if (lock.isLocked()){
            lockString = "Locked"
        }
        else{
            lockString = "Unlocked"
        }
        return "${javaClass.simpleName} - $lockString"
    }
}

class BookShelf: RoomObject() {
    private var length = 1
    private var height = 4
}

class Picture: RoomObject() {
    private var length = 3
    private var height = 0
}

class Room {
    var roomItems : MutableList<RoomObject>  = mutableListOf()

    fun addItem(item:RoomObject) {
        if (roomItems.size < 6){
            roomItems.add(item)
        }
        else{
            println("The room is full")
        }
    }

    fun viewRoom() {
        var i = 1
        for (item:RoomObject in roomItems){
            println("$i - ${item.toString()}")
            i += 1
        }
    }
}

