package com.nikechallenge.applemusic

import com.nikechallenge.applemusic.model.MostPlayed

/**
 * A local cache to hold app states when alive in memory. This is not permanent and simply a static
 * utility alive as long as app is alive in the background/foreground.
 */
object LocalCache {
   var songDetail: MostPlayed? = null
}