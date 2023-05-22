package com.alanvo.androiddemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanvo.androiddemo.adapter.FeatureModel
import com.alanvo.androiddemo.adapter.FeaturesAdapter
import com.alanvo.androiddemo.databinding.ActivityMainBinding
import com.alanvo.androiddemo.features.google.signin.GoogleSignInActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            setHasFixedSize(false)

            val adapter = FeaturesAdapter()
            adapter.submitList(generateFeatures())
            setAdapter(adapter)
        }
    }

    private fun generateFeatures(): ArrayList<FeatureModel> {
        val list = ArrayList<FeatureModel>()

        list.add(FeatureModel("Google SignIn") {
            startActivity(Intent(this@MainActivity, GoogleSignInActivity::class.java))
        })

        return list
    }
}