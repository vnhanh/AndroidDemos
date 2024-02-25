package com.vnhanh.common.compose.modifier.singleClick

import com.vnhanh.common.compose.ComposeConstant.THRESHOLD_TIME_DEBOUNCE_CLICK_MILLISECOND


interface SingleClickEventListener {
    fun processEvent(event: () -> Unit)

    companion object
}

fun SingleClickEventListener.Companion.get(thresholdTime: Long = THRESHOLD_TIME_DEBOUNCE_CLICK_MILLISECOND): SingleClickEventListener =
    SingleClickEventListenerImpl(thresholdTime)

private class SingleClickEventListenerImpl(val thresholdTime: Long) : SingleClickEventListener {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= thresholdTime) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}
