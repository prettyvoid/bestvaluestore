Best Value Store
----------------
Application will run on port 8080, binary is available at `/jar` but it needs jre 13, you can run via: `java.exe -jar bestvaluestore-0.0.1-SNAPSHOT.jar`

This application is built using Java JDK 13, but I think it might compile using JDK 9, not with 8 though because I'm using `List.of()`

To access h2 console: 

_http://localhost:8080/h2-console/_

_JDBC URL: `jdbc:h2:mem:bestvaluestoredb`_

_User Name: `sa`_

_Password is blank_

Available end-point is POST _localhost:8080/calculate_price_

Input is 3 params:

customerId (long) [available ids `1` `2` `3`]

groceryIds (long list) [csv of available ids `1` `2` `3`]

electronicDevicesIds (long list) [csv of available ids `1` `2` `3`]

![UML](https://raw.githubusercontent.com/prettyvoid/bestvaluestore/master/uml.jpg)

![REQUEST](https://raw.githubusercontent.com/prettyvoid/bestvaluestore/master/request.jpg)
