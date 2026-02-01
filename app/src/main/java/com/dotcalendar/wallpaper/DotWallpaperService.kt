
package com.dotcalendar.wallpaper

import android.graphics.*
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import java.util.*

class DotWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine = DotEngine()

    inner class DotEngine : Engine() {
        private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        override fun onDraw(canvas: Canvas) {
            canvas.drawColor(Color.BLACK)
            val cal = Calendar.getInstance()
            val today = cal.get(Calendar.DAY_OF_YEAR)

            val cols = 14
            val rows = 26
            val size = canvas.width / (cols * 2f)

            val start = Calendar.getInstance().apply { set(Calendar.DAY_OF_YEAR, 1) }
            var day = 1

            for (r in 0 until rows) {
                for (c in 0 until cols) {
                    if (day > 366) break
                    val temp = start.clone() as Calendar
                    temp.add(Calendar.DAY_OF_YEAR, day - 1)
                    paint.color = when {
                        day < today -> Color.WHITE
                        temp.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY -> Color.YELLOW
                        temp.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY -> Color.RED
                        else -> Color.DKGRAY
                    }
                    canvas.drawCircle(
                        c * 2 * size + size,
                        r * 2 * size + size,
                        size / 2,
                        paint
                    )
                    day++
                }
            }
        }
    }
}
