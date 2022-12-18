package animationutils

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView

class AnimationPlayer {
    companion object{
        fun appear(image: SubsamplingScaleImageView, appearTime: Long){
            image.animate().apply {}.withEndAction{
                image.animate().apply {
                    duration = appearTime
                    alpha(1f)
                }
            }.start()
        }
    }
}