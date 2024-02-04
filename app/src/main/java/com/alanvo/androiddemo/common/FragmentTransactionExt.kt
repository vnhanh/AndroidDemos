import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.alanvo.androiddemo.R

fun FragmentManager.open(transitionBlock: FragmentTransaction.() -> Unit) {
    this.commit {
        setReorderingAllowed(true)
        setCustomAnimations(
            /* enter = */ R.anim.slide_in,
            /* exit = */ R.anim.fade_out,
            /* popEnter = */ R.anim.fade_in,
            /* popExit = */ R.anim.slide_out
        )
        transitionBlock()
    }
}