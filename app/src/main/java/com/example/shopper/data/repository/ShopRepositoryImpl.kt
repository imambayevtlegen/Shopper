package com.example.shopper.data.repository


import androidx.lifecycle.LiveData
import com.example.shopper.data.model.*
import com.example.shopper.data.repository.datasource.ShopLocalDataSource
import com.example.shopper.data.repository.datasource.ShopRemoteDataSource
import com.example.shopper.data.util.Outcome
import com.example.shopper.domain.repository.ShopRepository
import javax.inject.Inject
import retrofit2.Response

class ShopRepositoryImpl @Inject constructor(

    private val remoteDataSource: ShopRemoteDataSource,
    private val localDataSource: ShopLocalDataSource
): ShopRepository {


    override suspend fun getAllProducts(): Outcome<Shop> {
        return responseToShopResult(remoteDataSource.getAllProducts())
    }

    override suspend fun getProduct(itemId: Int): Outcome<ShopItem> {
        return responseToShopItemResult(remoteDataSource.getProduct(itemId))
    }

    override suspend fun getAllCategories(): Outcome<Category> {
        return responseToCategoryResult(remoteDataSource.getAllCategories())
    }

    override suspend fun getCategoryProducts(category: String): Outcome<Shop> {
        return responseToShopResult(remoteDataSource.getCategoryProducts(category))
    }

    override suspend fun uploadProduct(shopItem: ShopItem): Outcome<ShopItem>{
        return responseToShopItemResult(remoteDataSource.uploadProduct(shopItem))
    }

    override suspend fun updateProduct(id: Int, shopItem: ShopItem): Outcome<ShopItem>{
        return responseToShopItemResult(remoteDataSource.updateProduct(id, shopItem))
    }

    override suspend fun deleteProduct(id: Int): Outcome<ShopItem>{
        return responseToShopItemResult(remoteDataSource.deleteProduct(id))
    }

    override suspend fun getCart(id: Int): Outcome<Cart>{
        return responseToCartResult(remoteDataSource.getCart(id))
    }

    override suspend fun getCartProducts(id: Int): Outcome<List<Product>>{
        return responseToCartProducts(remoteDataSource.getCartProducts(id))
    }

    override suspend fun addToCart(cartItem: CartItem): Outcome<CartItem>{
        return responseToCartItemResult(remoteDataSource.addToCart(cartItem))
    }

    override suspend fun updateCart(id: Int, cartItem: CartItem): Outcome<CartItem>{
        return responseToCartItemResult(remoteDataSource.updateCart(id, cartItem))
    }

    override suspend fun deleteCart(id: Int): Outcome<CartItem>{
        return responseToCartItemResult(remoteDataSource.deleteCart(id))
    }

    override suspend fun getUser(id: Int): Outcome<User>{
        return responseToUserResult(remoteDataSource.getUser(id))
    }

    override suspend fun updateUser(id: Int, user: User): Outcome<User>{
        return responseToUserResult(remoteDataSource.updateUser(id, user))
    }

    override suspend fun loginUser(login: Login): Outcome<LoginResponse>{
        return responseToString(remoteDataSource.loginUser(login))
    }

    override suspend fun registerUser(user: User): Outcome<User>{
        return responseToUserResult(remoteDataSource.registerUser(user))
    }

    override suspend fun addToCartItems(cartItem2: CartItem2){
        return localDataSource.addToCart(cartItem2)
    }

    override fun getCartItems(): LiveData<List<CartItem2>>{
        return localDataSource.getCartItems()
    }

    override suspend fun updateCartItems(cartItem2: CartItem2){
        return localDataSource.updateCartItems(cartItem2)
    }

    override suspend fun deleteCartItems(cartItem2: CartItem2){
        return localDataSource.deleteCartItems(cartItem2)
    }

    override suspend fun clearCart(){
        return localDataSource.clearCart()
    }

    override suspend fun addToFavorites(shopItem: ShopItem){
        return localDataSource.addToFavorites(shopItem)
    }

    override fun getFavoriteItems(): LiveData<List<ShopItem>>{
        return localDataSource.getFavoriteItems()
    }

    override suspend fun deleteFavoriteItem(shopItem: ShopItem){
        return localDataSource.deleteFavoriteItem(shopItem)
    }

    override suspend fun clearFavorites(){
        return localDataSource.clearFavorite()
    }



    private fun responseToShopResult(result: Result<Shop>): Outcome<Shop> {
        return result.fold(
            onSuccess = { shop ->
                Outcome.Success(shop)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }

    private fun responseToShopItemResult(result: Result<ShopItem>) : Outcome<ShopItem>{
        return result.fold(
            onSuccess = { shopItem ->
                Outcome.Success(shopItem)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }

    private fun responseToString(result: Result<LoginResponse>) : Outcome<LoginResponse>{
        return result.fold(
            onSuccess = { loginResponse ->
                Outcome.Success(loginResponse)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }

    private fun responseToCartProducts(result: Result<CartItem>) : Outcome<List<Product>>{
        return result.fold(
            onSuccess = { cartItem ->
                Outcome.Success(cartItem.products)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }

    private fun responseToCartResult(result: Result<Cart>) : Outcome<Cart>{
        return result.fold(
            onSuccess = { cart ->
                Outcome.Success(cart)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }

    private fun responseToCartItemResult(result: Result<CartItem>) : Outcome<CartItem>{
        return result.fold(
            onSuccess = { cartItem ->
                Outcome.Success(cartItem)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }

    private fun responseToCategoryResult(result: Result<Category>) : Outcome<Category>{
        return result.fold(
            onSuccess = { category ->
                Outcome.Success(category)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }

    private fun responseToUserResult(result: Result<User>) : Outcome<User>{
        return result.fold(
            onSuccess = { user ->
                Outcome.Success(user)
            },
            onFailure = { throwable ->
                Outcome.Error(message = throwable.message ?: "Unknown error")
            }
        )
    }
}