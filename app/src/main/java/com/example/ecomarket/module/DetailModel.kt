package com.example.ecomarket.module

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailModel(
    val id : Int,
    val title : String,
    val description :String,
    val category : Int,
    val image : String,
    val quantity : Int,
    val price : Double
): Parcelable {

}

fun DetailModel.toProduct(): Product {
    return Product(
        id = this.id.toLong(),
        name = this.title,
        image = this.image,
        count = this.quantity,
        price = this.price
    )
}
