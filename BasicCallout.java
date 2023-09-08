Http h = new Http();
HttpRequest req = new HttpRequest();
req.setEndpoint('https://postman-echo.com/get?foo1=bar1&foo2=bar2');
req.setMethod('GET');
HttpResponse res = h.send(req);
system.debug('Response ===>  ' + res);
    /**  Result 
	
	USER_DEBUG [6]|DEBUG|Response ===>  System.HttpResponse[Status=OK, StatusCode=200]
	
	**/
	