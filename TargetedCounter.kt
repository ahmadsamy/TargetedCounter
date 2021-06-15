import ahmad.egypt.lensometertool.BuildConfig
import android.util.Log

class TargetedCounter(private var countThreshold: Int = 10,private var delayThreshold: Long = 500) {
    private var startTime: Long = 0
    private var counter = 0


    private fun tick(): Boolean {
        if(BuildConfig.DEBUG) Log.d("Count", "" + counter)
        if (startTime == 0L) {
            startTime = System.currentTimeMillis()
            counter++
            return false
        }
        if (System.currentTimeMillis() - startTime <= delayThreshold) {
            counter++
            startTime = System.currentTimeMillis()
            if (counter >= countThreshold) {
                /*startTime = 0
                counter = 0*/
                return true
            }
        } else {
            startTime = 0
            counter = 0
        }
        return false
    }

    fun getCounter(): Int {
        return if (isFinished()) countThreshold else counter
    }

    fun isFinished():Boolean{
        return if (tick()){
            startTime = 0
            counter = 0
            true
        }else false
    }
}
