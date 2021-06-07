package com.bisipaul.currencyconverter.core

/**
 *  Created by paulbisioc on 06.06.2021
 */

open class RequestResponse<T>

open class ErrorResponse<T> : RequestResponse<T>()
class NoInternetError<T> : ErrorResponse<T>()
class UnknownError<T> : ErrorResponse<T>()

class SuccessResponse<T>(val data: T) : RequestResponse<T>()

