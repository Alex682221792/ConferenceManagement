package com.fissa.conferencemanagement.view.result

interface IResultController {
    fun attachView(view: IResultView)
    fun detachView()
    fun isAttachedView() : Boolean
    fun getTracks()
}