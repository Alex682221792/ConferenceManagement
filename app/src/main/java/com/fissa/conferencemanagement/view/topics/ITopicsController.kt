package com.fissa.conferencemanagement.view.topics

interface ITopicsController {
    fun attachView(view: ITopicsView)
    fun detachView()
    fun isAttachedView() : Boolean
    fun addTopic(topic: String)
    fun removeTopic(topicId: String)
    fun getAllTopics()
}