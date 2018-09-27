## LocalStudyApi

### Overview
It's a project consisting of multiple services (now it's 2-3) which are considered to be used in local study projects.
The main idea is: having the project with multiple services specifications, which can be used by local study projects. 
Local study project chooses the implementor of specified service and sends request to localStudyApi project. 

Implementors are called "providers" in this project. The initial provider is WikiLocalStudyImpl. It implements services logic 
using wiki api.

Only two services are supported now: 

1) Get the information about the geographical object;
2) Get all geographical objects near specified coordinates;

And logic related to manipulating this providers via SOAP.

It is considered that application will have many of services.
