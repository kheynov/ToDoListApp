package ru.kheynov.todolistapp.feature_todo.domain.util

sealed class TodoOrder(val orderType: OrderType){
    class Title(orderType: OrderType): TodoOrder(orderType)
    class Date(orderType: OrderType): TodoOrder(orderType)
}