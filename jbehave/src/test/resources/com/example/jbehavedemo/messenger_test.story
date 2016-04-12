Given a messenger

When testing connection with Invalid Server
Then tstConn should return 1

When testing connection with Valid Server
Then tstConn should return 0

When sending valid message
Then sndMsg should return 1 or 0

When sending invalid message
Then sndMsg should return -1 or 2