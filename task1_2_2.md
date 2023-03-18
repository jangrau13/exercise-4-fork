# Query
comunica-sparql-link-traversal  \
"PREFIX foaf: <http://xmlns.com/foaf/0.1/> \
PREFIX : <https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card#>\
select distinct ?anotherPerson where {\
  :me (foaf:knows)+ ?anotherPerson.\
}" --l debug

# Results
[2023-03-18T19:51:27.873Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch (Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:51:28.115Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
[2023-03-18T19:51:28.129Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/danai/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch (Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:51:28.131Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/philipp/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch (Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/danai/profile/card#me"},
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/philipp/profile/card#me"}[2023-03-18T19:51:28.306Z]  INFO: Identified as file source: 
https://solid.interactions.ics.unisg.ch/philipp/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
,
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card#me"},
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/atilla/profile/card#me"}[2023-03-18T19:51:28.320Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/atilla/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch 
(Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:51:28.321Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/florence/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch (Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
,
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/florence/profile/card#me"}[2023-03-18T19:51:28.336Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/danai/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
,
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/andrei/profile/card#me"}[2023-03-18T19:51:28.341Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/andrei/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch 
(Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:51:28.429Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/atilla/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
,
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/fabiohsg/profile/card#me"},
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/valentinhsg/profile/card#me"}[2023-03-18T19:51:28.434Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/fabiohsg/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch (Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:51:28.435Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/valentinhsg/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch (Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:51:28.458Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/andrei/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
[2023-03-18T19:51:28.484Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/florence/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
,
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/damian/profile/card#me"}[2023-03-18T19:51:28.487Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/damian/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch 
(Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:51:28.569Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/valentinhsg/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
[2023-03-18T19:51:28.582Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/fabiohsg/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
Expected entity but got eof on line 14.


# Interpetation
Comunica does the same thing as before. Now it checks every result for more information as well though. So for every result there is a new http request to check, whether there is more information. If there is, a new request will be executed as well. This is done as long as the chain is valid and/or no more new results are popping up.