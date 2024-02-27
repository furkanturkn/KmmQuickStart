package core.domain.util.preference

import core.domain.util.KmmContext


expect fun KmmContext.putInt(key: String, value: Int)

expect fun KmmContext.getInt(key: String, default: Int): Int

expect fun KmmContext.putString(key: String, value: String)

expect fun KmmContext.getString(key: String) : String?

expect fun KmmContext.putBool(key: String, value: Boolean)

expect fun KmmContext.getBool(key: String, default: Boolean): Boolean