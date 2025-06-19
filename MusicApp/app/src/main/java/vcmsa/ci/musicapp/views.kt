package vcmsa.ci.musicapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class views : AppCompatActivity() {

    /*lateinit var tvDis:TextView
    lateinit var btnBack:Button
    lateinit var btnCalc:Button
    lateinit var btnList:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        /* ============= Retrieve lists from Intent ============= */
        songs    = intent.getStringArrayListExtra("songs")    ?: emptyList()
        artists  = intent.getStringArrayListExtra("artists")  ?: emptyList()
        ratings  = intent.getIntegerArrayListExtra("ratings") ?: emptyList()
        comments = intent.getStringArrayListExtra("comments") ?: emptyList()

        /* ============= Buttons ============= */
        findViewById<Button>(R.id.btnList).setOnClickListener { showSongs() }
        findViewById<Button>(R.id.btnCalc).setOnClickListener { showAverage() }
        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }
    }

    /* ---- Show songs with details (using loop) ---- */
    private fun showSongs() {
        if (songs.isEmpty()) {
            Toast.makeText(this, "Playlist empty.", Toast.LENGTH_SHORT).show()
            return
        }

        val builder = StringBuilder()
        for (i in songs.indices) {
            builder.append("${i + 1}. ${songs[i]} — ${artists[i]}\n")
            builder.append("   ★${ratings[i]}  ${comments[i]}\n\n")
        }

        AlertDialog.Builder(this)
            .setTitle("Songs & Details")
            .setMessage(builder.toString())
            .setPositiveButton("OK", null)
            .show()
    }

    /* ---- Calculate average rating (loop) ---- */
    private fun showAverage() {
        if (ratings.isEmpty()) {
            Toast.makeText(this, "No ratings yet.", Toast.LENGTH_SHORT).show()
            return
        }

        var sum = 0                 // simple loop as requested
        for (r in ratings) sum += r
        val avg = sum.toDouble() / ratings.size

        Toast.makeText(this, "Average rating: %.2f".format(avg), Toast.LENGTH_LONG).show()
    }
}
     fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_views)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }*/ private lateinit var tvDis: TextView
    private lateinit var btnBack: Button
    private lateinit var btnCalc: Button
    private lateinit var btnList: Button


    private lateinit var songs: List<String>
    private lateinit var artists: List<String>
    private lateinit var ratings: List<Int>
    private lateinit var comments: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_views)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // views
        tvDis = findViewById(R.id.tvDis)
        btnBack = findViewById(R.id.btnBack)
        btnCalc = findViewById(R.id.btnCalc)
        btnList = findViewById(R.id.btnList)

        // get data
        songs = intent.getStringArrayListExtra("songs") ?: emptyList()
        artists = intent.getStringArrayListExtra("artists") ?: emptyList()
        ratings = intent.getIntegerArrayListExtra("ratings") ?: emptyList()
        comments = intent.getStringArrayListExtra("comments") ?: emptyList()

        // listers
        btnList.setOnClickListener { showSongs() }
        btnCalc.setOnClickListener { showAverage() }
        btnBack.setOnClickListener { finish() }
    }

    private fun showSongs() {
        if (songs.isEmpty()) {
            Toast.makeText(this, "Playlist is empty.", Toast.LENGTH_SHORT).show()
            return
        }

        val builder = StringBuilder()
        for (i in songs.indices) {
            builder.append("${i + 1}. ${songs[i]} — ${artists[i]}\n")
            builder.append("   ★${ratings[i]}  ${comments[i]}\n\n")
        }

        AlertDialog.Builder(this)
            .setTitle("Songs & Details")
            .setMessage(builder.toString())
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showAverage() {
        if (ratings.isEmpty()) {
            Toast.makeText(this, "No ratings yet.", Toast.LENGTH_SHORT).show()
            return
        }

        var total = 0
        for (r in ratings) total += r
        val avg = total.toDouble() / ratings.size

        Toast.makeText(this, "Average rating: %.2f".format(avg), Toast.LENGTH_LONG).show()
    }
}