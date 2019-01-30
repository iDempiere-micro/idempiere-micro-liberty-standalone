package company.bigger.idempiere.config

import com.natpryce.konfig.*
import java.io.File

private val defaults = User::class.java.classLoader.getResource("defaults.properties")

val config =
    EnvironmentVariables() overriding
            ConfigurationProperties.fromFile(File(defaults.file))

object User : PropertyGroup() {
    private val password_hash by booleanType // User.User-password-hash
    val passwordHash = config[password_hash]
}

object Locking : PropertyGroup() {
    private val max_account_lock_minutes by intType // Locking.max-account-lock-minutes
    val maxAccountLockMinutes = config[max_account_lock_minutes]

    private val max_inactive_period_day by intType // Locking.max-inactive-period-day
    val maxInactivePeriodDays = config[max_inactive_period_day]

    private val max_password_age_day by intType // Locking.max-password-age-day

    private val max_login_attempt by intType// Locking.max-login-attempt
    val maxLoggingAttempts = config[max_login_attempt]
}

object Jwt : PropertyGroup() {
    private val secret by stringType
    val Secret = config[secret]
    private val issuer by stringType
    val Issuer = config[issuer]
}