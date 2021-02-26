package com.fissa.conferencemanagement.view.topics

import android.content.Context
import com.fissa.conferencemanagement.model.TopicVO

interface ITopicsView {
    fun loadTopics(topicsList : Array<TopicVO>)
    fun clearForm()
    fun showMessage(message: String)
    fun getActivityContext(): Context
}