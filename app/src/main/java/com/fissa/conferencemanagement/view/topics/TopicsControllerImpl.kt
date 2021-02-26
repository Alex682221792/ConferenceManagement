package com.fissa.conferencemanagement.view.topics

import android.util.Log
import com.fissa.conferencemanagement.R
import com.fissa.conferencemanagement.logic.util.ManageUtil
import com.fissa.conferencemanagement.store.TopicsStore

class TopicsControllerImpl : ITopicsController {
    private var view: ITopicsView? = null

    override fun attachView(view: ITopicsView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isAttachedView(): Boolean {
        return this.view!==null
    }

    override fun getAllTopics() {
        if (this.isAttachedView()) {
            val topics = TopicsStore(this.view!!.getActivityContext()).topicsQueue
            this.view!!.loadTopics(topics.toTypedArray())
        }
    }

    override fun addTopic(topic: String) {
        if (this.isAttachedView()) {
            val manageUtil = ManageUtil()
            if(manageUtil.validateTopic(topic)){
                val topicVO = manageUtil.generateTopicVo(topic)
                TopicsStore(this.view!!.getActivityContext()).addTaxiRequestToQueue(topicVO)
                this.getAllTopics()
                this.view!!.clearForm()
            } else {
                this.view!!.showMessage(view!!.getActivityContext().getString(R.string.format_event_error))
            }
        }
    }

    override fun removeTopic(topicId: String) {
        if (this.isAttachedView()) {
            TopicsStore(this.view!!.getActivityContext()).removeTopicInQueue(topicId)
            this.getAllTopics()
        }
    }
}