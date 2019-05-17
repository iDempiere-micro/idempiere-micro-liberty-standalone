package company.bigger.idempiere.config

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.PropertyGroup
import com.natpryce.konfig.overriding
import com.natpryce.konfig.booleanType
import com.natpryce.konfig.intType
import com.natpryce.konfig.stringType
import com.natpryce.konfig.getValue
import java.io.File

// this is a hack see https://github.com/npryce/konfig/issues/36
private val defaults = User::class.java.classLoader.getResource("defaults.properties")

/**
 * Global configuration read from:
 * - environment variables OR
 * - /etc/idempiere-micro.properties
 * - or defaults file in the resources/defaults.properties
 */
val config =
    EnvironmentVariables() overriding
    ConfigurationProperties.fromOptionalFile(File("/etc/idempiere-micro.properties")) overriding
    // must be optional as the file is not found when running from the standalone JAR
    ConfigurationProperties.fromOptionalFile(File(defaults.file))

/**
 * User related configuration
 */
object User : PropertyGroup() {
    private val password_hash by booleanType // User.User-password-hash
    val passwordHash = config[password_hash]
}

/**
 * User Locking related configuration
 */
object Locking : PropertyGroup() {
    private val max_account_lock_minutes by intType // Locking.max-account-lock-minutes
    val maxAccountLockMinutes = config[max_account_lock_minutes]

    private val max_inactive_period_day by intType // Locking.max-inactive-period-day
    val maxInactivePeriodDays = config[max_inactive_period_day]

    private val max_password_age_day by intType // Locking.max-password-age-day

    private val max_login_attempt by intType // Locking.max-login-attempt
    val maxLoggingAttempts = config[max_login_attempt]
}

/**
 * JWT related configuration
 */
object Jwt : PropertyGroup() {
    private val secret by stringType
    val Secret = config[secret]
    private val issuer by stringType
    val Issuer = config[issuer]
}

/**
 * Database related configuration
 */
object Database : PropertyGroup() {
    private val url by stringType
    val Url = config[url]
    private val username by stringType
    val Username = config[username]
    private val password by stringType
    val Password = config[password]
}