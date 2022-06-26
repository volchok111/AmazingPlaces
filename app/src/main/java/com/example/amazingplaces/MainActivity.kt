package com.example.amazingplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.amazingplaces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    /**
     * It sets up the action bar with the navigation controller.
     *
     * @param savedInstanceState The Bundle that contains the data it most recently supplied in
     * onSaveInstanceState(Bundle) if activity was reinitialized after being previously shut down.
     * Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavController(findNavController(R.id.mainNavHost))
    }

    /**
     * If the user presses the back button, the app will navigate to the previous fragment
     *
     * @return The return value is a boolean.
     */
    override fun onSupportNavigateUp(): Boolean {
        val bind = findNavController(R.id.mainNavHost)
        return super.onSupportNavigateUp() || bind.navigateUp()
    }
}