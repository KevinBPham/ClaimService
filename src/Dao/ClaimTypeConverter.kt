package org.csuf.cpsc411.Dao

import com.sun.org.apache.xpath.internal.functions.FuncFalse
import java.util.*

class ClaimTypeConverter {
    fun toUUID(uuid: String?) : UUID? {
        return UUID.fromString(uuid)
    }

    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
    
    fun toBoolean(boolInt: Int?) : Boolean? {
        return boolInt == 1
    }

    fun fromBoolean(boolSolved: Boolean?) : Int? {
        if (!boolSolved!!)
        {
           return 2
        }
        else{
            return 1
        }
    }
}