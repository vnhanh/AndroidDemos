package com.alanvo.androiddemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.commit
import androidx.transition.TransitionInflater
import com.alanvo.androiddemo.databinding.FragmentMainBinding
import com.alanvo.androiddemo.practice.jodaTime.JodaTimeFragment
import open

class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.shared_image)
        exitTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.explode)
    }

    private fun setupViews() {
        binding?.apply {
            button.setOnClickListener {
                println("Alan - clicked button")
                val changeTransform = TransitionInflater.from(context).inflateTransition(R.transition.shared_image)
                val explodeTransform = TransitionInflater.from(context).inflateTransition(android.R.transition.explode)
                val destFragment = JodaTimeFragment()
                parentFragmentManager.open {
//                    destFragment.sharedElementEnterTransition = changeTransform
//                    destFragment.enterTransition = explodeTransform
                    addSharedElement(imageView, "test")
//                    replace(R.id.fragment_container_view, destFragment)
                    addToBackStack("jodata_time")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Alan - fragment onCreateView()")
        binding = FragmentMainBinding.inflate(inflater)
        setupViews()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding?.apply {
//            ViewCompat.setTransitionName(imageView, "test_shared")
//        }
    }
}