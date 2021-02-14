#Assumptions:

* Assuming a system which receive an stream of events and call this microservice.

* Input specification:

    * Contract

               {
                    tradeId,
                    version,
                    counterPartyId,
                    bookId,
                    maturityDate,
               }
 
    * createdDate and expiredFlag is not part of incoming message.
    * Version should always be greater than 0
 
 * Authentication and Authorization is not in scope.
 
 
 