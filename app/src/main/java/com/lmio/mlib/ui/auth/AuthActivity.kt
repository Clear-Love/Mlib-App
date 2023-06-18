package com.lmio.mlib.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.lmio.mlib.MainActivity
import com.lmio.mlib.R
import com.lmio.mlib.databinding.ActivityAuthBinding
import com.lmio.mlib.utils.Requests

class AuthActivity : AppCompatActivity(), FragmentInteractionListener {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        Requests.Init()
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_auth) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun updateTitle(title: String) {
        binding.authTitle.text = title
    }

    override fun toMain() {
        // 跳转MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}