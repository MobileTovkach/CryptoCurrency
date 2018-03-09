package com.example.air.cryptocurrency.data.api

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class ApiError internal constructor(private val errorCode: Int , @field:Expose
@field:SerializedName("code")
private val statusCode: String? , @field:Expose
                                    @field:SerializedName("message")
                                    var message: String?) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other!!.equals(null) || javaClass.equals(other.javaClass)) return false

        val apiError = other as ApiError?

        if (!errorCode.equals(apiError!!.errorCode)) {
            return false
        }
        if (if (!statusCode.isNullOrEmpty())
                    statusCode != apiError.statusCode
                else
                    apiError.statusCode != null)
            return false
        return if (!message.isNullOrEmpty()) message == apiError.message else apiError.message == null
    }

    override fun hashCode(): Int {
        var result: Int = errorCode
        result = 31 * result + (statusCode?.hashCode() ?: 0)
        result = 31 * result + if (!message.isNullOrEmpty()) message!!.hashCode() else 0
        return result
    }

}