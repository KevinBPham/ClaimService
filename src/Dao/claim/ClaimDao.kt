package org.csuf.cpsc411.Dao.claim

import org.csuf.cpsc411.Dao.ClaimTypeConverter
import org.csuf.cpsc411.Dao.Dao
import org.csuf.cpsc411.Dao.Database

class ClaimDao : Dao() {
    fun addClaim(cObj : Claim){
        val conn = Database.getInstance()?.getDbConnection()
        var convertedID = ClaimTypeConverter().fromUUID(cObj.id)
        var convertedSolved = ClaimTypeConverter().fromBoolean(cObj.isSolved)
        sqlStmt = "insert into person (id, title, date, isSolved) values ('${convertedID}', '${cObj.title}', '${cObj.date}', '${convertedSolved}'"
        conn?.exec(sqlStmt)
    }

    fun getAll() : List<Claim> {
        val conn = Database.getInstance()?.getDbConnection()
        sqlStmt = "select id, title, date, isSolved from claim"
        var claimList : MutableList<Claim> = mutableListOf()
        val st = conn?.prepare(sqlStmt)
        while (st?.step()!!) {
            val id = st.columnString(0)
            val convertedID = ClaimTypeConverter().toUUID(id)
            val title = st.columnString(1)
            val date = st.columnString(2)
            val isSolved = st.columnInt(3)
            val convertedSolved = ClaimTypeConverter().toBoolean(isSolved)

            claimList.add(Claim(convertedID,title,date,convertedSolved))
        }
        return claimList
    }
}