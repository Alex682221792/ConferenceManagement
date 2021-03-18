package com.fissa.conferencemanagement.view.result

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fissa.conferencemanagement.R
import com.fissa.conferencemanagement.logic.adapter.TopicListAdapter
import com.fissa.conferencemanagement.model.TopicVO
import com.fissa.conferencemanagement.view.topics.ITopicsController
import com.fissa.conferencemanagement.view.topics.TopicsControllerImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_topics.*

class ResultFragment : Fragment(), IResultView {

    lateinit var parentActivity: Activity
    lateinit var myListAdapter: TopicListAdapter
    private lateinit var resultController: IResultController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        this.initControllers()
        return view
    }

    override fun onResume() {
        super.onResume()
        this.getTracks()
    }

    private fun getTracks(){
        this.resultController.getTracks()
    }

    private fun initControllers() {
        this.resultController = ResultControllerImpl()
        this.resultController.attachView(this)
    }

    override fun loadTopics(topicsList: Array<TopicVO>) {
        if (topicsList.isEmpty()){
            listView.visibility = View.GONE
            return
        }
        myListAdapter = TopicListAdapter(this.parentActivity, requireView(), topicsList, null)
        listView.adapter = myListAdapter
    }

    override fun getActivityContext(): Context {
        return requireContext()
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}