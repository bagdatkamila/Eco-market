package com.example.ecomarket.network

// Запечатанный класс для представления результата сетевого запроса
sealed class NetworkResult<T>(
    val data:T?=null,
    val message:String?=null,
    val networkError:Boolean=false
){
    // Класс для успешного результата
    class Success<T>(data: T?):NetworkResult<T>(data)
    // Класс для ошибки
    class Error<T>(networkError: Boolean,message: String?):NetworkResult<T>(null,message=message,networkError=networkError)

    // Класс для состояния загрузки
    class Loading<T> : NetworkResult<T>()

    // Утилитный метод для проверки успешного выполнения
    fun isSuccess() = this is Success<T>

    // Утилитный метод для проверки наличия ошибки
    fun isError() = this is Error<T>
}
