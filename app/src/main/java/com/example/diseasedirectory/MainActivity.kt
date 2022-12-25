package com.example.diseasedirectory

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.diseasedirectory.adapter.DiseaseAdapter
import com.example.diseasedirectory.disease.Singleton
import com.example.diseasedirectory.enum.FontFamilyName
import com.example.diseasedirectory.textsizeutils.FontSize
import com.example.diseasedirectory.typefaceutils.Typeface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_disease_list.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        val typeface = Typeface.initialSharedPreferences(this).getString(Typeface.KEY_FONT_FAMILY_STRING, "NoFontFamily")
        Typeface.setFontFamilyApp(this, FontFamilyName.getFontFamily(typeface!!))

        val textSize = FontSize.initialSharedPreferences(this).getString(FontSize.KEY_FONT_SIZE_STRING, "Default")
        FontSize.setFontSizeApp(this, FontSize.getTextSize(textSize!!))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Справочник заболеваний"

        instantiateToggle()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> setFragment(
                    DiseaseListFragment(),
                    resources.getString(R.string.app_name)
                )
                R.id.itemCategories -> setFragment(
                    CategoriesFragment(),
                    resources.getString(R.string.item_categories)
                )
                R.id.itemSettings -> setFragment(
                    OptionsFragment(),
                    resources.getString(R.string.item_settings)
                )
                R.id.itemAbout -> setFragment(
                    AboutAppFragment(),
                    resources.getString(R.string.about)
                )
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment, title: String) {
        searchView.isVisible = fragment is DiseaseListFragment

        this.title = title

        supportFragmentManager.beginTransaction()
            .replace(R.id.navHostFragment, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()
    }

    private fun filterRecycler(filterer: String) {
        var diseases = (navHostFragment.diseaseList.adapter as DiseaseAdapter).getItems()

        diseases = ArrayList(diseases.filter { it.title.contains(filterer, ignoreCase = true) })

        navHostFragment.diseaseList.adapter = DiseaseAdapter(
            ArrayList(diseases),
            navHostFragment.getFragment()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val search = menu?.findItem(R.id.itemSearch)
        searchView = search?.actionView as SearchView
        searchView.queryHint = getString(R.string.find)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty())
                    navHostFragment.diseaseList.adapter = DiseaseAdapter(
                        ArrayList(
                            Singleton
                                .getDiseases(this@MainActivity)
                        ),
                        navHostFragment.getFragment()
                    )
                else filterRecycler(newText)

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true

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

        if (backEntry.name == DiseaseListFragment::class.java.name) finish()
        else setFragment(DiseaseListFragment(), resources.getString(R.string.app_name))
    }
}