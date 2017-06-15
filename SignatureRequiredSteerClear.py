from urllib2 import *
import urllib
import json
import sys

MY_API_KEY="AIzaSyASqeK6EJy9rH0V13XNCpozws_ZitN12UE"

messageTitle = "Signature Required"
messageBody = "Steer Clear"
documentLink = "https://demo.docusign.net/Signing/?ti=ebf153c9274c4708bb5fcc84955af3c1"

data={
    "to" : "/topics/my_little_topic",
    "data" : {
        "message" : messageBody,
        "title" : messageTitle,
        "link" : documentLink,
        "icon" : "push_notify"
    },
    "notification" : {
        "body" : messageBody,
        "title" : messageTitle,
        "link" : documentLink,
        "icon" : "push_notify"
    }
}

dataAsJSON = json.dumps(data)

request = Request(
    "https://gcm-http.googleapis.com/gcm/send",
    dataAsJSON,
    { "Authorization" : "key="+MY_API_KEY,
      "Content-type" : "application/json"
    }
)

print urlopen(request).read()
