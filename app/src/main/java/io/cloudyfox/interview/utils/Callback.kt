package io.cloudyfox.interview.utils

interface Callback {

    interface GeneralCallback{
        fun onSuccess()
        fun noInterneConnection()
        fun onFail()
    }
}