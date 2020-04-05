package gov.mm.covid19statsbago.util.services

interface ProgressInterface {
    fun update(
        bytesRead: Long,
        contentLength: Long,
        done: Boolean
    )
}
