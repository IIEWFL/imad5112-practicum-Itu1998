package vcmsa.ci.musicapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import vcmsa.ci.musicapp.databinding.ActivityDet2Binding

class det2 : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDet2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment_content_det2)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
}