# Query

comunica-sparql https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card#me \
                                                                     "PREFIX foaf: <http://xmlns.com/foaf/0.1/> \
                                                                 PREFIX : <https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card#>\    
                                                                 select distinct ?anotherPerson where {\
                                                                   :me (foaf:knows)+ ?anotherPerson.\
                                                                 }" -l debug

# Results
[2023-03-18T19:53:12.614Z]  INFO: Requesting https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card { headers: { accept: 'application/n-quads,application/trig;q=0.95,application/ld+json;q=0.9,application/n-triples;q=0.8,text/turtle;q=0.6,application/rdf+xml;q=0.5,application/json;q=0.45,text/n3;q=0.35,application/xml;q=0.3,image/svg+xml;q=0.3,text/xml;q=0.3,text/html;q=0.2,application/xhtml+xml;q=0.18,text/shaclc;q=0.1,text/shaclc-ext;q=0.05', 'user-agent': 'Comunica/actor-http-fetch (Node.js v19.8.1; linux)' }, method: 'GET', actor: 'urn:comunica:default:http/actors#fetch' }
[2023-03-18T19:53:12.843Z]  INFO: Identified as file source: https://solid.interactions.ics.unisg.ch/AnnaLuese/profile/card { actor: 'urn:comunica:default:rdf-resolve-hypermedia/actors#none' }
[
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/danai/profile/card#me"},
{"anotherPerson":"https://solid.interactions.ics.unisg.ch/philipp/profile/card#me"}
]

# Interpetation
The program only performs one http request to the endpoint indicated. Comunica queries the url to get the turtle data and then runs the sparql query on them

