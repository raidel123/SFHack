from urllib2 import *
import urllib
import json
import sys

MY_API_KEY="AIzaSyASqeK6EJy9rH0V13XNCpozws_ZitN12UE"

messageTitle = "Document Completed"
messageBody = "Mileage Acknowledgment Form"
documentLink = "https://demo.docusign.net/signing/emails/v2-a913528fe56c4265bb3eff8ada1429fe7716a690028147d7971b564c428aeab66fbf6934c5974b0b9b0ff15c93c7a827"

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