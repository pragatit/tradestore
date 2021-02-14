#Assumptions:

* Assuming a system which receive stream of events.

* Input specification:

    * Contract

               {
                    tradeId : string,
                    version: int,
                    counterPartyIdL: string,
                    bookId: string,
                    maturityDate: string (mm-dd-yyyy),
               }
 
    * createdDate and expiredFlag is not part of incoming message.
    * Version should always be greater than 0
 
 * Authentication and Authorization is not in scope.
 
 
 
 
