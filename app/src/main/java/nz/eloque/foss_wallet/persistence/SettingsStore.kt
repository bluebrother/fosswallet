package nz.eloque.foss_wallet.persistence

import android.content.SharedPreferences
import androidx.core.content.edit
import jakarta.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private const val SYNC_INTERVAL = "syncInterval"
private const val SYNC_ENABLED = "syncEnabled"

class SettingsStore @Inject constructor(
    private val prefs: SharedPreferences,
) {
    fun isSyncEnabled(): Boolean = prefs.getBoolean(SYNC_ENABLED, false)

    fun enableSync(enabled: Boolean) = prefs.edit { putBoolean(SYNC_ENABLED, enabled) }

    fun syncInterval(): Duration {
        val amount = prefs.getLong(SYNC_INTERVAL, 60)
        return amount.toDuration(DurationUnit.MINUTES)
    }

    fun setSyncInterval(duration: Duration) = prefs.edit {
        putLong(SYNC_INTERVAL, duration.toLong(DurationUnit.MINUTES))
    }
}