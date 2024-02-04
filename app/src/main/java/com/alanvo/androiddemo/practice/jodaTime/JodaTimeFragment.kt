package com.alanvo.androiddemo.practice.jodaTime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.transition.TransitionInflater
import com.alanvo.androiddemo.R
import com.alanvo.androiddemo.databinding.FragmentJodaTimeBinding

class JodaTimeFragment : Fragment() {
    private var binding: FragmentJodaTimeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_image)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJodaTimeBinding.inflate(inflater)
        println("Alan - joda time fragment - onCreateView")

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("Alan - joda time fragment - onViewCreated")
//        binding?.apply {
//            ViewCompat.setTransitionName(imageView, "test_shared")
//        }
    }
}