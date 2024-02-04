package com.alanvo.androiddemo

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanvo.androiddemo.adapter.FeatureModel
import com.alanvo.androiddemo.adapter.FeaturesAdapter
import com.alanvo.androiddemo.common.transition
import com.alanvo.androiddemo.databinding.ActivityMainBinding
import com.alanvo.androiddemo.features.google.signin.GoogleSignInActivity
import com.alanvo.androiddemo.practice.jodaTime.JodaTimeFragment
import com.alanvo.androiddemo.practice.transition.TransitionTargetActivity

class MainActivity : FragmentActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.fragment_container_view)
            }
        }
    }

    private fun setupViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

        }
    }

//    private fun setupRecyclerView() {
//        binding.rvMain.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//
//            setHasFixedSize(false)
//
//            val adapter = FeaturesAdapter()
//            adapter.submitList(generateFeatures())
//            setAdapter(adapter)
//        }
//    }

    private fun generateFeatures(): ArrayList<FeatureModel> {
        val list = ArrayList<FeatureModel>()

        list.add(FeatureModel("Google SignIn") {
            startActivity(Intent(this@MainActivity, GoogleSignInActivity::class.java))
        })

        return list
    }
}