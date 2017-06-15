from urllib2 import *
import urllib
import json
import sys

MY_API_KEY="AIzaSyASqeK6EJy9rH0V13XNCpozws_ZitN12UE"

messageTitle = "Signature Required"
messageBody = "Good Student"
documentLink = "https://demo.docusign.net/Signing/?ti=525d0608233e41f9902e31a137f05571"

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
