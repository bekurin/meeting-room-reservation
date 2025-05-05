package meeting.room.reservation.exception

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus

open class ServerException(message: String) : RuntimeException(message)

@ResponseStatus(BAD_REQUEST)
class BadRequestException(message: String) : ServerException(message)

@ResponseStatus(NOT_FOUND)
class ResourceNotFoundException(message: String) : ServerException(message)

@ResponseStatus(INTERNAL_SERVER_ERROR)
class InternalServerError(message: String) : ServerException(message)
