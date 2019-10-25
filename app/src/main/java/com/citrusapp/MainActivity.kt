package com.citrusapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.citrusapp.util.BottomNavController
import com.citrusapp.util.setUpNavigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavController.NavGraphProvider {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        Navigation.findNavController(this, R.id.navHost)
    }

    private val bottomNavController by lazy(LazyThreadSafetyMode.NONE) {
        BottomNavController(this, R.id.navHost, R.id.navigation_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavController.setNavGraphProvider(this)
        bottomNavigationView.setUpNavigation(bottomNavController)
        if (savedInstanceState == null) bottomNavController.onNavigationItemSelected()
    }

    override fun getNavGraphId(itemId: Int): Int {
        return when (itemId) {
            R.id.navigation_main -> R.navigation.navigation_main
            R.id.navigation_catalog -> R.navigation.navigation_catalog
            R.id.navigation_basket -> R.navigation.navigation_basket
            R.id.navigation_account -> R.navigation.navigation_account
            R.id.navigation_more -> R.navigation.navigation_more
            else -> R.navigation.navigation_main
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        bottomNavController.onBackPressed()
    }
}
