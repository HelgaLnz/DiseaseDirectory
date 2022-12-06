package com.example.diseasedirectory

import android.content.Intent
import android.graphics.BitmapFactory.Options
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Справочник заболеваний"

        instantiateToggle()

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.itemHome -> {
                    title = resources.getString(R.string.app_name)

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navHostFragment, DiseaseListFragment())
                        .addToBackStack(DiseaseListFragment::class.java.name)
                        .commit()
                }
                R.id.itemCategories -> {
                    title = resources.getString(R.string.item_categories)

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navHostFragment, CategoriesFragment())
                        .addToBackStack(CategoriesFragment::class.java.name)
                        .commit()
                }
                R.id.itemSettings -> {
                    title = resources.getString(R.string.item_settings)

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navHostFragment, OptionsFragment())
                        .addToBackStack(OptionsFragment::class.java.name)
                        .commit()
                }
                R.id.itemAbout -> Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) return true

        return super.onOptionsItemSelected(item)
    }

    private fun instantiateToggle() {
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        val index = supportFragmentManager.backStackEntryCount - 1
        val backEntry = supportFragmentManager.getBackStackEntryAt(index)

        if(backEntry.name == DiseaseListFragment::class.java.name)
            finish()
        else {
            title = resources.getString(R.string.app_name)

            supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragment, DiseaseListFragment())
                .addToBackStack(DiseaseListFragment::class.java.name)
                .commit()
        }
    }
}