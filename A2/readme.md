## Compiling/Running program

SERVER:
ssh in-csci-rrpc01.cs.iupui.edu
rmiregistry 2324 &
make run-server

CLIENT:
ssh in-csci-rrpc02.cs.iupui.edu
make run-client

## Details

there is a system in place to purchase items, but there isn't a way to track money on an account. It just assumes the purchase is successful and updates the database in the process.

a state machine was created to handle text input.

the pdf did not mention that I was required to use a database like sql. I just created a database class that handles all the data, but none of it is saved when the server is terminated.

## PROCESS
when a user logs in, a controller is created by the server and sent back to client. Client then uses this controller to add/remove items and more.
