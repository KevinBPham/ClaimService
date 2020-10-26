package org.csuf.cpsc411

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import com.almworks.sqlite4java.SQLiteConnection
import com.google.gson.Gson
import io.ktor.http.*
import org.csuf.cpsc411.Dao.ClaimTypeConverter
import org.csuf.cpsc411.Dao.claim.Claim
import org.csuf.cpsc411.Dao.claim.ClaimDao
import java.util.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing{
        get("/ClaimService/getAll"){
           val cList = ClaimDao().getAll()
            val respJsonStr = Gson().toJson(cList)
            call.respondText(respJsonStr, status = HttpStatusCode.OK,
            contentType = ContentType.Application.Json)
        }
        post("/ClaimService/add"){

            val title = call.request.queryParameters["Title"]
            val date = call.request.queryParameters["Date"]
            val uuid = UUID.randomUUID()
            val isSolved = false
            val response = String.format("Title %s and Date %", title, date)
            val cObj = Claim(uuid,title, date,isSolved)
            var jsonStr = Gson().toJson(cObj)
            val dao = ClaimDao().addClaim(cObj)
            call.respondText(response, status= HttpStatusCode.OK, contentType= ContentType.Text.Plain)
        }
    }
}

