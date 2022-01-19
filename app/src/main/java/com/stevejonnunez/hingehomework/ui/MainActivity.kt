package com.stevejonnunez.hingehomework.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.stevejonnunez.hingehomework.adapters.HingeProfileAdapter
import com.stevejonnunez.hingehomework.viewModels.HingeProfileViewModel
import com.stevejonnunez.hingehomework.databinding.ActivityMainBinding
import com.stevejonnunez.hingehomework.databinding.ContentMainBinding
import com.stevejonnunez.hingehomework.model.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<HingeProfileViewModel>()
    private lateinit var hingeProfileAdapter: HingeProfileAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var contentMainBinding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contentMainBinding = binding.contentMain

        setupAdapter()
        subscribeUi()
        setSupportActionBar(binding.toolbar)
        setupFloatingActionButton()
    }

    private fun setupAdapter() {
        hingeProfileAdapter = HingeProfileAdapter()
        contentMainBinding.viewPagerUsers.adapter = hingeProfileAdapter
    }

    private fun setupFloatingActionButton() {
        binding.floatingNextProfileButton.setOnClickListener { view ->
            val viewPagerUsers = contentMainBinding.viewPagerUsers
            if (viewPagerUsers.currentItem < hingeProfileAdapter.itemCount - 1)
                viewPagerUsers.currentItem += 1
            else
                Snackbar.make(view, "No more profiles", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun subscribeUi() {

        viewModel.getAllData().observe(this) { userAndConfigPair ->

            val userResult = userAndConfigPair.first
            val configResult = userAndConfigPair.second
            if (userResult.status == Status.SUCCESS && configResult.status == Status.SUCCESS) {
                configResult.data?.let { configData ->
                    userResult.data?.let { userData ->
                        hingeProfileAdapter.submitUserAndConfig(
                            userData.users, configData.profile
                        )
                    }
                }
                contentMainBinding.loading.visibility = View.GONE
                binding.floatingNextProfileButton.visibility = View.VISIBLE
            } else if (userResult.status == Status.ERROR) {
                userResult.message?.let { showError() }
                contentMainBinding.loading.visibility = View.GONE
                binding.floatingNextProfileButton.visibility = View.GONE
            } else if (configResult.status == Status.ERROR) {
                configResult.message?.let { showError() }
                contentMainBinding.loading.visibility = View.GONE
                binding.floatingNextProfileButton.visibility = View.GONE
            } else { //LOADING
                contentMainBinding.loading.visibility = View.VISIBLE
                binding.floatingNextProfileButton.visibility = View.GONE
            }
        }
    }

    private fun showError() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("There was an error retrieving profiles.")
            .setPositiveButton("Try again") { dialog, which ->
                subscribeUi()
                dialog.dismiss()
                dialog.cancel()
            }
            .setNegativeButton("Exit") { dialog, which ->
                finishAndRemoveTask()
            }
            .show()
    }
}