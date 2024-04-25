package ir.dunijet.wikipedia_m3.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import im.delight.android.webview.AdvancedWebView
import ir.dunijet.wikipedia_m3.R
import ir.dunijet.wikipedia_m3.databinding.ActivityWebViewBinding
import ir.dunijet.wikipedia_m3.ext.copyToClipboard

class WebViewActivity : AppCompatActivity() , AdvancedWebView.Listener {
    lateinit var binding :ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED
        }

        val bundle = intent.getBundleExtra("url_data")!!
        val url = bundle.getString("url")!!
        val title = bundle.getString("title")!!

        binding.webViewMain.setListener(this, this)
        binding.webViewMain.setMixedContentAllowed(false)
        binding.webViewMain.loadUrl(url)
        binding.toolbarWebview.title = title

        binding.toolbarWebview.setNavigationOnClickListener {
            finish()
        }
        binding.toolbarWebview.setOnMenuItemClickListener {

            when (it.itemId) {

                R.id.menu_copy_address -> {
                    val currentUrl = binding.webViewMain.url!!
                    copyToClipboard(currentUrl)
                }

            }


            true
        }

    }

    fun copyUrl() {

    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onPageFinished(url: String?) {
        binding.progressBar.visibility = View.GONE
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {
    }

    override fun onExternalPageRequest(url: String?) {
    }

    override fun onBackPressed() {
        if (!binding.webViewMain.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        binding.webViewMain.onResume()
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        binding.webViewMain.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.webViewMain.onDestroy()
        super.onDestroy()
    }

}