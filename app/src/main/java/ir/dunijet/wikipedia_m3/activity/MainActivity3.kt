package ir.dunijet.wikipedia_m3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import ir.dunijet.wikipedia_m3.R
import ir.dunijet.wikipedia_m3.databinding.ActivityMain3Binding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Glide
            .with(this)
            .load(R.drawable.img_translatore)
            .transform(RoundedCornersTransformation(32, 8))
            .into(binding.imgTranslator)

    }
}