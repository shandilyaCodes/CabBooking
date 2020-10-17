## Dev Flow

### Primary

PreparePOM {
* Download blank Project from Spring.io, keep it ready
* Import the POM
}

Model {
* Booking
* Cab
* CabAvailability
* Location
* Rider
* Trip
}

DTO {
* CabDTO
* RiderDTO
* TripDTO
}

Repositories {
* CabRepository
* RiderRepository
* TripRepository
}

Utils {
* CommonUtils
}

Constants {
* TripConstants
}

Strategy {
* ICabMatchingStrategy
* DefaultCabMatchingStrategy
* IPricingStrategy
* DefaultPricingStrategy
}

Exceptions {
* CabNotFoundException
* NoCabsAvailableException
* NoCabsFoundInRange
* RiderAlreadyInTripException
* RiderNotFoundException
* TripNotFoundException
  centralised {
  * ExceptionHandler
  * ExceptionResponse
  }
}

Service {
* CabService
* RiderService
* TripService
}

Controller {
* CabController
* RiderController
}

Less Priority : Swagger {
* SwaggerConfiguration
}