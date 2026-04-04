enum class ProductCategory( val displayName: String ) {

    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    FOOD("Food"),
    BOOKS("Books"),

}

sealed class OrderStatus {

    data class Confirmed( val orderId: String ): OrderStatus()
    data class Shipped( val trackingNumber: String ): OrderStatus()
    data class Delivered( val feedback: String ): OrderStatus()
    object Cancelled: OrderStatus()
}

interface Discountable {

    fun applyDiscount(): Double

}

abstract class Product( val name: String, val price: Double, val category: ProductCategory ) {

    abstract fun describe(): String

    open fun display() {

        print( "Name: $name \nPrice: $price \ncategory: $category \n" )

    }

}

class PhysicalProduct( name: String, price: Double, category: ProductCategory, val weight: Double ): Product( name, price, category ), Discountable {

    override fun applyDiscount(): Double {

        return price * 0.9

    }

    override fun describe(): String {

        return "Physical Product."

    }

    override fun display() {

        super.display()
        println( "Weight: $weight" )

    }

}

class DigitalProduct( name: String, price: Double, category: ProductCategory, val fileSize: String ): Product( name, price, category ), Discountable {

    override fun applyDiscount(): Double {

        return price * 0.8

    }

    override fun describe(): String {

        return "Digital Product."

    }

    override fun display() {

        super.display()
        println( "File Size: $fileSize" )

    }
}

object Store {

    val name = "MyStore"
    var totalOrders = 0

    fun placeOrder(): Int {

        totalOrders++
        return totalOrders

    }

}

data class Order( val product: Product, val quantity: Int, val status: OrderStatus ) {

    fun totalPrice() = product.price * quantity

    fun checkOrderStatus(): OrderStatus {

        return when ( status ) {

            is OrderStatus.Confirmed -> status
            is OrderStatus.Shipped -> status
            is OrderStatus.Delivered -> status
            OrderStatus.Cancelled -> status

        }

    }

}


fun main() {

    val physicalProducts = PhysicalProduct( "Laptop", 999.99, ProductCategory.ELECTRONICS, 2.5 )
    val digitalProducts = DigitalProduct( "Kotlin course", 49.99, ProductCategory.BOOKS, "2.3GB" )

    physicalProducts.display()
    digitalProducts.display()

    println( physicalProducts.applyDiscount() )
    println( digitalProducts.applyDiscount() )

    val order1 = Order( physicalProducts, 1, OrderStatus.Confirmed( "ORD001" ) )
    val order2 = Order( digitalProducts, 2, OrderStatus.Shipped( "TRK456" ) )

    println( order1.checkOrderStatus() )
    println( order2.checkOrderStatus() )

    println( Store.name )
    println( Store.placeOrder() )

    val updateOrderStatus = order1.copy(status = OrderStatus.Cancelled )
    println( updateOrderStatus )

    val product: Product = DigitalProduct( "Kotlin course", 49.99, ProductCategory.BOOKS, "2.3GB" )
    println( product.describe() )

}
