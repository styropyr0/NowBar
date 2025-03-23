package com.matrix.nowbar.extensions

fun <T> MutableList<T>.sortWithTopValueAscending(value: T): MutableList<T> {
    val listOfItems: MutableList<T> = mutableListOf()
    if (value in this) {
        val position: Int = this.indexOf(value)
        for (i in position downTo 0)
            listOfItems.add(this[i])
        for (i in this.size - 1 until position)
            listOfItems.add(this[i])
    }
    this.clear()
    this.addAll(listOfItems)
    return this
}

fun <T> MutableList<T>.sortWithTopValueAscending(position: Int): MutableList<T> {
    val listOfItems: MutableList<T> = mutableListOf()
    if (position < this.size) {
        for (i in position downTo 0)
            listOfItems.add(this[i])
        for (i in this.size - 1 until position)
            listOfItems.add(this[i])
    }
    this.clear()
    this.addAll(listOfItems)
    return this
}
