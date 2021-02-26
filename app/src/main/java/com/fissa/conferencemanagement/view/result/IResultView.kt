package com.fissa.conferencemanagement.view.result

import android.content.Context
import com.fissa.conferencemanagement.model.TopicVO

interface IResultView {
    fun loadTopics(topicsList : Array<TopicVO>)
    fun getActivityContext(): Context
}