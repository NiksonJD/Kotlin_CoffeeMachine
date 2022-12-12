class CoffeeMachine {
    //water, milk, coffee beans, disposable cups, money
    private val base = mutableListOf(400, 540, 120, 9, 550)
    private val map =
        mapOf("1" to listOf(250, 0, 16, 1, -4), "2" to listOf(350, 75, 20, 1, -7), "3" to listOf(200, 100, 12, 1, -6))
    private val label = listOf("ml of water", "ml of milk", "grams of coffee beans", "disposable cups", "of money")

    fun remaining() =
        println("\nThe coffee machine has:").also { (0..4).forEach { println("${base[it]} ${label[it]}") } }

    fun makeCoffee() {
        println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        val name = readln()
        if ("123".indexOf(name) == -1) return
        val list = map[name]!!

        if (base.component1() < list.component1()) {
            println("Sorry, not enough water!")
        } else if (base.component2() < list.component2()) {
            println("Sorry, not enough milk!")
        } else if (base.component3() < list.component3()) {
            println("Sorry, not enough coffee beans!")
        } else if (base.component4() <= 0) {
            println("Sorry, not enough disposable cups!")
        } else {
            println("I have enough resources, making you a coffee!\n")
            list.withIndex().forEach { base[it.index] -= it.value }
        }
    }

    fun fillCoffeeMachine() {
        var correct = false
        while (!correct) {
            try {
                (0..3).forEach {
                    base[it] += println("Write how many ${label[it]} do you want to add:").let { readln() }.toInt()
                }
                correct = true
            } catch (e: Exception) {
                correct = false
            }
        }
    }

    fun takeMoney() = println("I gave you ${'$'}${base[4]}").also { base[4] = 0 }
}

fun main(args: Array<String>): Unit {
    var answer = " "
    val coffeeMachine = CoffeeMachine()
    while (answer == " ") {
        println("Write action (buy, fill, take, remaining, exit):")
        when (readln()) {
            "buy" -> coffeeMachine.makeCoffee()
            "fill" -> coffeeMachine.fillCoffeeMachine()
            "take" -> coffeeMachine.takeMoney()
            "remaining" -> coffeeMachine.remaining()
            "exit" -> break
            else -> answer = " "
        }
    }
}