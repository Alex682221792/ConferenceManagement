package com.fissa.conferencemanagement.view.info_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fissa.conferencemanagement.R


/**
 * A simple [Fragment] subclass.
 * Use the [InfoAppFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoAppFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info_app, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            InfoAppFragment()
    }
}