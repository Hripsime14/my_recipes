package com.example.myrecipes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myrecipes.R
import com.example.myrecipes.databinding.ActivityMainBinding
import com.example.myrecipes.ui.common.BaseActivity

class MainActivity : BaseActivity() {
    private var navController: NavController? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { setContentView(it.root) }
        setNavController()
    }

//    override fun onSupportNavigateUp(): Boolean = if (menu?.findItem(R.id.deleteId)?.isVisible == true) {
//        recipesAdapter?.resetSelectedItems()
//        true
//    } else {
//        findNavController().navigateUp()
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() == true || super.onSupportNavigateUp()
    }

    private fun setNavController() {
        binding?.apply {
            val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navController = navHostFragment.findNavController()
            setSupportActionBar(toolbar)

            val appBarConfiguration = AppBarConfiguration(setOf(R.id.recipesFragment))
            navController?.let {
                //makes fragments as top level destinations
                setupActionBarWithNavController(it, appBarConfiguration)
            }
        }
    }
}