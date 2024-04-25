package ir.dunijet.wikipedia_m3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ir.dunijet.wikipedia_m3.R
import ir.dunijet.wikipedia_m3.databinding.ActivityMainBinding
import ir.dunijet.wikipedia_m3.ext.Constant
import ir.dunijet.wikipedia_m3.ext.Keys
import ir.dunijet.wikipedia_m3.ext.readPref
import ir.dunijet.wikipedia_m3.ext.setNavigationColor
import ir.dunijet.wikipedia_m3.ext.showDialog
import ir.dunijet.wikipedia_m3.ext.showSnackbar
import ir.dunijet.wikipedia_m3.ext.writePref
import ir.dunijet.wikipedia_m3.fragments.FragmentExplore
import ir.dunijet.wikipedia_m3.fragments.FragmentProfile
import ir.dunijet.wikipedia_m3.fragments.FragmentTrend
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var changeThemeButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initialize()

        changeThemeButton.setOnClickListener {
            toggleTheme()
        }
        binding.navigationViewMain.setNavigationItemSelectedListener {

            when (it.itemId) {

//                R.id.temenu_writer -> {
//                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
//
//                    val dialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
//                    dialog.titleText = "Alert!"
//                    dialog.confirmText = "Confirm"
//                    dialog.cancelText = "Cancel"
//                    dialog.contentText = "Wanna be a Writer?"
//                    dialog.setCancelClickListener {
//                        dialog.dismiss()
//                    }
//                    dialog.setConfirmClickListener {
//                        dialog.dismiss()
//                        Toast.makeText(this, "you can be a writer just work :)", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                    dialog.show()
//
//                }
//
//                R.id.menu_photograph -> {
//
//                    // load fragment =>
//                    val transaction = supportFragmentManager.beginTransaction()
//                    transaction.add(R.id.frame_main_container, FragmentPhotographer())
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//
//                    // check menu item =>
//                    binding.navigationViewMain.menu.getItem(1).isChecked = true
//
//                    // close drawer =>
//                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
//
//                }
//
//                R.id.menu_vieo_maker -> {
//                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
//
//                    Snackbar
//                        .make(binding.root, "No Internet!", Snackbar.LENGTH_LONG)
//                        .setAction("Retry") {
//                            Toast.makeText(this, "checking network", Toast.LENGTH_SHORT).show()
//                        }
////                        .setActionTextColor(ContextCompat.getColor(this, R.color.white))
////                        .setBackgroundTint(ContextCompat.getColor(this, R.color.blue))
//                        .show()
//
//                }

                R.id.menu_writer -> {
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)

                    val intent = Intent(this, MainActivity3::class.java)
                    startActivity(intent)

                }

                // ---------------------------------

                R.id.menu_open_wikipedia -> {
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    openWebsite("https://www.wikipedia.org/")
                }

                R.id.openWikimedia -> {
                    binding.drawerLayoutMain.closeDrawer(GravityCompat.START)
                    openWebsite("https://www.wikimedia.org/")
                }
            }

            true
        }
        binding.toolbarMain.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.menu_info -> {
                    showDialog(
                        "About us",
                        "Wikipedia is a free, web-based, collaborative, multilingual encyclopedia project launched in 2001 supported by the non-profit Wikimedia Foundation. \n\nIt is one of the largest and most popular general reference works on the internet. Wikipedia's articles are written collaboratively by volunteers around the world, and almost all of its articles can be edited by anyone with internet access."
                    )
                }

                R.id.menu_writer -> {
                    openWebsite("https://en.wikipedia.org/wiki/Wikipedia:How_to_create_a_page")
                }
            }
            true
        }

        binding.bottomNavigationMain.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.menu_explore -> {
                    replaceFragment(FragmentExplore())
                }

                R.id.menu_trend -> {
                    replaceFragment(FragmentTrend())
                }

                R.id.menu_profile -> {
                    replaceFragment(FragmentProfile())
                }
            }

            true
        }
        binding.bottomNavigationMain.setOnItemReselectedListener {}

    }

    private fun openWebsite(url: String) {
        val intent = Intent(this, WebViewActivity::class.java)

        val bundle = Bundle()
        bundle.putString("title", "be a writer")
        bundle.putString("url", url)

        intent.putExtra("url_data", bundle)
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main_container, fragment)
        transaction.commit()
    }

    fun initTheme() {
        val theme = runBlocking {
            readPref(Keys.appTheme)
        }
        if (theme == null) {
            runBlocking {
                writePref(Keys.appTheme, Constant.light)
            }
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            if (theme == Constant.light) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun initialize() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED
        }
        setNavigationColor()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayoutMain,
            binding.toolbarMain,
            R.string.openDrawer,
            R.string.closeDrawer
        )
        binding.drawerLayoutMain.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        replaceFragment(FragmentExplore())
        binding.bottomNavigationMain.selectedItemId = R.id.menu_explore

        changeThemeButton =
            binding.navigationViewMain.getHeaderView(0).findViewById(R.id.btnChangeTheme)
    }

    private fun toggleTheme() {
        lifecycleScope.launch {
            val currentTheme = readPref(Keys.appTheme)
            if (currentTheme == Constant.light) {
                writePref(Keys.appTheme, Constant.dark)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                writePref(Keys.appTheme, Constant.light)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}