package com.fissa.conferencemanagement.view.result

import com.fissa.conferencemanagement.logic.util.ManageUtil
import com.fissa.conferencemanagement.store.TopicsStore

class ResultControllerImpl : IResultController {
    private var view: IResultView? = null

    override fun attachView(view: IResultView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isAttachedView(): Boolean {
        return this.view !== null
    }

    override fun getTracks() {
        if (this.isAttachedView()) {
            val topics = TopicsStore(this.view!!.getActivityContext()).topicsQueue
            val tracks = ManageUtil().generateTracks(topics.toTypedArray())
            this.view!!.loadTopics(tracks)
        }
    }
}