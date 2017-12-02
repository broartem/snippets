// Taken from https://stackoverflow.com/questions/38243530
case class CustomException(private val message: String = "", 
                          private val cause: Throwable = None.orNull)
extends Exception(message, cause) 
