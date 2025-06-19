package vcmsa.ci.musicapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //STUDENT NO: ST10446882


    lateinit var btnADD:Button
    lateinit var btnEXIT:Button
    lateinit var btnNx:Button





     val songs    = mutableListOf<String>()
     val artists  = mutableListOf<String>()
     val ratings  = mutableListOf<Int>()
     val comments = mutableListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         // buttons once each
        findViewById<Button>(R.id.btnADD).setOnClickListener { showAddDialog() }
        findViewById<Button>(R.id.btnEXIT).setOnClickListener { displayPlaylist() }
        findViewById<Button>(R.id.btnNx).setOnClickListener { displaySongs() }
        findViewById<Button>(R.id.btnEXIT).setOnClickListener {



        btnNx.setOnClickListener {
            val intent = Intent(this, views::class.java)
            startActivity(intent)
        }
            val intent = Intent(this, Details::class.java).apply {
                putStringArrayListExtra("songs",    ArrayList(songs))
                putStringArrayListExtra("artists",  ArrayList(artists))
                putIntegerArrayListExtra("ratings", ArrayList(ratings))
                putStringArrayListExtra("comments", ArrayList(comments))
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnNx).setOnClickListener { val intent = Intent(this, views::class.java)
            startActivity(intent) }
        findViewById<Button>(R.id.btnEXIT).setOnClickListener { finishAffinity() }
    }


    // Add song d
    private fun showAddDialog() {
        val ctx = this                       // shorter alias

        val container = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 16, 32, 0)
        }

        val titleEt   = EditText(ctx).apply { hint = "Song Title" }
        val artistEt  = EditText(ctx).apply { hint = "Artist Name" }
        val ratingEt  = EditText(ctx).apply { hint = "Rating (1‑5)"
            inputType = InputType.TYPE_CLASS_NUMBER
        }
        val commentEt = EditText(ctx).apply { hint = "Comment" }

        container.addView(titleEt)
        container.addView(artistEt)
        container.addView(ratingEt)
        container.addView(commentEt)

        AlertDialog.Builder(ctx)
            .setTitle("Add Song to Playlist")
            .setView(container)
            .setPositiveButton("Add") { _, _ ->
                val title  = titleEt.text.toString().trim()
                val artist = artistEt.text.toString().trim()
                val rating = ratingEt.text.toString().toIntOrNull()
                val comment= commentEt.text.toString().trim()

                when {
                    title.isBlank() || artist.isBlank() -> {
                        Toast.makeText(ctx, "Name required.", Toast.LENGTH_SHORT).show()
                    }
                    rating !in 1..5 -> {
                        Toast.makeText(ctx, "Rate 1‑5.", Toast.LENGTH_SHORT).show()
                    }
                    songs.size >= 4 -> {
                        Toast.makeText(ctx, "4 songs limit.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        songs   += title
                        artists += artist
                        ratings += rating!!
                        comments+= comment
                        Toast.makeText(ctx, "Song added!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }



    private fun displayPlaylist() {
        if (songs.isEmpty()) {
            Toast.makeText(this, "Playlist is empty.", Toast.LENGTH_SHORT).show()
            return
        }

        val body = songs.indices.joinToString("\n") { i ->
            "${i + 1}. ${songs[i]} by ${artists[i]} | ★${ratings[i]} | ${comments[i]}"
        }


        AlertDialog.Builder(this)
            .setTitle("Playlist")
            .setMessage(body)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun displaySongs() {
        val body = songs.indices.joinToString("\n") { i ->
            "${i + 1}. ${songs[i]} — ${artists[i]} (★${ratings[i]})"
        }.ifEmpty { "No songs added yet." }

        AlertDialog.Builder(this)
            .setTitle("All Songs")
            .setMessage(body)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun calculateAverageRating() {
        if (ratings.isEmpty()) {
            Toast.makeText(this, "No ratings yet.", Toast.LENGTH_SHORT).show()
            return
        }
        val avg = ratings.sum().toDouble() / ratings.size
        Toast.makeText(this, "Average rating: %.2f".format(avg), Toast.LENGTH_LONG).show()
    }
}


