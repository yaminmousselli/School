import base64

data = ""
with open("bobattachment.txt", "rb") as f:
  data = f.read()

print base64.b64decode(data)
