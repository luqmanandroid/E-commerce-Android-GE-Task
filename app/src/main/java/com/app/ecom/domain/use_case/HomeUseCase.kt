package com.app.ecom.domain.use_case

import com.app.ecom.common.Resource
import com.app.ecom.domain.model.ProductsListResponse
import com.app.ecom.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: HomeRepository) {
    fun getProuctsList(): Flow<Resource<List<ProductsListResponse>>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getProductList()

            emit(Resource.Success(data = response))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown error"))

        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check your internet connection"))

        } catch (e: Exception) {
            emit(Resource.Error(message = "An unexpected error occurred"))
        }
    }

    private fun parseErrorMessage(errorBody: String?): String {
        return try {
            val jsonObject = JSONObject(errorBody)
            val messages = jsonObject.getJSONObject("messages")
            val message = messages.keys().asSequence().firstOrNull()?.let {
                messages.getString(it)
            }
            message ?: "An error occurred"
        } catch (e: JSONException) {
            "An error occurred"
        }
    }

}
