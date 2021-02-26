package com.fissa.conferencemanagement.view.topics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.fissa.conferencemanagement.R
import com.fissa.conferencemanagement.logic.adapter.TopicListAdapter
import com.fissa.conferencemanagement.model.TopicVO
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_topics.*

class TopicsFragment() : Fragment(), ITopicsView {

    lateinit var parentActivity: Activity
    lateinit var myListAdapter: TopicListAdapter
    private lateinit var topicController: ITopicsController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_topics, container, false)
        this.initControllers()
        return view
    }

    override fun onResume() {
        super.onResume()
        this.getAllTopics()
        this.prepareEvent()
    }

    private fun initControllers() {
        this.topicController = TopicsControllerImpl()
        this.topicController.attachView(this)
    }

    private fun prepareEvent() {
        (view?.findViewById<ImageView>(R.id.imgVw_addTopic))!!.setOnClickListener { v: View? -> addTopic() }
    }

    private fun getAllTopics() {
        this.topicController.getAllTopics()
    }

    private fun addTopic() {
        val textTopic = (view?.findViewById<TextInputEditText>(R.id.txtFld_newTopic))!!.text.toString()
        if (textTopic.isNotEmpty()) {
            this.topicController.addTopic(textTopic.toString())
        }
    }

    override fun loadTopics(topicsList: Array<TopicVO>) {
        myListAdapter = TopicListAdapter(this.parentActivity, topicsList, topicController)
        listView.adapter = myListAdapter
    }

    override fun clearForm() {
        (view?.findViewById<TextInputEditText>(R.id.txtFld_newTopic))!!.text!!.clear()
    }

    override fun showMessage(message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
    }

    override fun getActivityContext(): Context {
        return this.context!!
    }

    companion object {
        @JvmStatic
        fun newInstance() = TopicsFragment()
    }
}